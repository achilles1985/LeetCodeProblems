package design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**H
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following
 * operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
 * it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem,
 * when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be
 * evicted.
 *
 * Note that the number of times an item is used is the number of calls to the get and put functions for that item
 * since it was inserted. This number is set to zero when the item is removed.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 * LFUCache cache = new LFUCache
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);       // returns 1
        cache.put(3,3);    // evicts key 2
        cache.get(2);       // returns -1 (not found)
        cache.get(3);       // returns 3.
        cache.put(4,4);    // evicts key 1.
        cache.get(1);       // returns -1 (not found)
        cache.get(3);       // returns 3
        cache.get(4);       // returns 4
*/
/*

Mistakes: Handle 0 capacity, LinkedHashSet has O(1) - remove time.
 */
public class LFUCache {

    private Map<Integer, CachItem> keyToCacheItem = new HashMap<>(); // to store (key, value, freq) and have O(1) - get, put
    private Map<Integer, LinkedHashSet<Integer>> frequencyToKeys = new HashMap<>(); // to store frequency -> {key1, key2, ..., keyN} and have O(1) - remove LFU
    private int leastFrequency = 1;
    private int maxCapacity;
    private int capacity = 0;

    public LFUCache(int capacity) {
        this.maxCapacity = capacity;
    }

    public int get(int key) {
        if (!keyToCacheItem.containsKey(key)) {
            return -1;
        }
        CachItem oldItem = keyToCacheItem.get(key);
        frequencyToKeys.get(oldItem.frequency).remove(oldItem.key);
        if (frequencyToKeys.get(oldItem.frequency).isEmpty()) {
            frequencyToKeys.remove(oldItem.frequency);
            leastFrequency++;
        }
        oldItem.frequency++;
        keyToCacheItem.put(key, oldItem);
        frequencyToKeys.computeIfAbsent(oldItem.frequency, k -> new LinkedHashSet<>()).add(key);

        return oldItem.value;
    }

    // O(capacity) - time
    public void put(int key, int value) {
        if (maxCapacity == 0) {
            return;
        }
        if (!keyToCacheItem.containsKey(key)) {
            if (capacity == maxCapacity) {
                // remove LFU item from both maps
                Integer keyToRemove = frequencyToKeys.get(leastFrequency).iterator().next();
                frequencyToKeys.get(leastFrequency).remove(keyToRemove);
                keyToCacheItem.remove(keyToRemove);
                if (frequencyToKeys.get(leastFrequency).isEmpty()) {
                    frequencyToKeys.remove(leastFrequency);
                }

                // add a new item
                CachItem item = new CachItem(key, value, 1);
                keyToCacheItem.put(key, item);
                frequencyToKeys.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
            } else {
                add(key, value, 1);
                capacity++;
            }
        } else {
            CachItem oldItem = keyToCacheItem.get(key);
            frequencyToKeys.get(oldItem.frequency).remove(key);
            keyToCacheItem.remove(oldItem.key);
            if (frequencyToKeys.get(oldItem.frequency).isEmpty()) {
                frequencyToKeys.remove(oldItem.frequency);
            }

            // new item
            add(key, value, oldItem.frequency++);
        }
        leastFrequency = 1;
    }

    private void add(int key, int value, int frequency) {
        CachItem item = new CachItem(key, value, frequency);
        keyToCacheItem.put(key, item);
        frequencyToKeys.computeIfAbsent(item.frequency, k -> new LinkedHashSet<>()).add(key);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3,3);                         // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3.
        cache.put(4,4);                         // evicts key 1.
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }

    private static class CachItem {
        int key;
        int value;
        int frequency;

        public CachItem(int key, int value, int frequency) {
            this.key = key;
            this.value = value;
            this.frequency = frequency;
        }
    }
}
