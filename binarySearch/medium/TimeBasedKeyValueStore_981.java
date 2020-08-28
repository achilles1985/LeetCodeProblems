package binarySearch.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/** M
 * Create a timebased key-value store class TimeMap, that supports two operations.
 * 1. set(string key, string value, int timestamp)
 *     Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *     Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 *     If there are multiple such values, it returns the one with the largest timestamp_prev.
 *     If there are no values, it returns the empty string ("").
 *
 * Example 1:
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 *
 * Example 2:
 * Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
 * Output: [null,null,null,"","high","high","low","low"]
 *
 * Note:
 *     All key/value strings are lowercase.
 *     All key/value strings have length in the range [1, 100]
 *     The timestamps for all TimeMap.set operations are strictly increasing.
 *     1 <= timestamp <= 10^7
 *     TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.
 */
public class TimeBasedKeyValueStore_981 {
    private Map<String, NavigableMap<Integer, String>> map;

    // O(n) - space
    /** Initialize your data structure here. */
    public TimeBasedKeyValueStore_981() {
        map = new HashMap<>();
    }

    // O(log(n)) - time
    public void set(String key, String value, int timestamp) {
        NavigableMap<Integer, String> timeToValue = map.get(key);
        if (timeToValue == null) {
            NavigableMap<Integer, String> timeToValueNew = new TreeMap<>();
            timeToValueNew.put(timestamp, value);
            map.put(key, timeToValueNew);
        } else {
            timeToValue.put(timestamp, value);
        }
    }

    // O(log(n)) - time
    public String get(String key, int timestamp) {
        NavigableMap<Integer, String> timeToValue = map.get(key);
        if (timeToValue == null) {
            return "";
        }
        Map.Entry<Integer, String> entry = timeToValue.floorEntry(timestamp);

        return entry == null ? "" : entry.getValue();
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStore_981 s = new TimeBasedKeyValueStore_981();
        s.set("key1", "value11", 5);
        s.set("key1", "value12", 20);
        s.set("key1", "value13", 35);
        s.set("key1", "value14", 55);

        s.set("key2", "value21", 20);
        s.set("key2", "value22", 30);

        System.out.println(s.get("key1", 4)); //""
        System.out.println(s.get("key1", 25)); //value12

        System.out.println(s.get("key2", 25)); //value21
        System.out.println(s.get("key2", 30)); //value22
        System.out.println(s.get("key2", 35)); //value22
    }
}
