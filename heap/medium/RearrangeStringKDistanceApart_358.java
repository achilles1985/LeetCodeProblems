package heap.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** H
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 *
 * Example 2:
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 *
 * Example 3:
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 */
public class RearrangeStringKDistanceApart_358 {

    public static void main(String[] args) {
        RearrangeStringKDistanceApart_358 s = new RearrangeStringKDistanceApart_358();
        System.out.println(s.rearrangeString("aaabc", 3)); //""
        System.out.println(s.rearrangeString("aabbccdd", 4)); //abcdabcd
        System.out.println(s.rearrangeString("a", 0)); //""
        System.out.println(s.rearrangeString("aabbcc", 3)); //abcabc
        System.out.println(s.rearrangeString("aaadbbcc", 2)); //abacabcd
    }

    // O(n) - time, O(1) - space
    public String rearrangeString(String s, int k) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        if (k == 0) {
            return s;
        }
        Map<Character, Integer> freq = new HashMap<>();
        for (char c: s.toCharArray()) { //n
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>((e1, e2) -> e2.getValue() == e1.getValue()
                ? e1.getKey().compareTo(e2.getKey())
                : e2.getValue() - e1.getValue());
        heap.addAll(freq.entrySet());
        StringBuilder sb = new StringBuilder();
        while (!heap.isEmpty()) { //26
            int count = 0;
            List<Map.Entry<Character, Integer>> temp = new ArrayList<>();
            while (count < k && !heap.isEmpty()) {
                Map.Entry<Character, Integer> entry = heap.poll();
                sb.append(entry.getKey());
                entry.setValue(entry.getValue()-1);
                if (entry.getValue() > 0) {
                    temp.add(entry);
                }
                count++;
            }
            if (count != k && !temp.isEmpty()) { // aaaabc, k=2
                return "";
            }
            for (Map.Entry<Character, Integer> e: temp) {
                heap.add(e);
            }
        }

        return sb.toString();
    }
}
