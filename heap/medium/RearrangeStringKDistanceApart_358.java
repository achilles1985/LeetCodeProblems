package heap.medium;

import java.util.*;

/**
 * H
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 * <p>
 * Example 1:
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 * <p>
 * Example 2:
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * <p>
 * Example 3:
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 */
// https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83192/Java-7-version-of-PriorityQueue-O(nlogn)-with-comments-and-explanations
public class RearrangeStringKDistanceApart_358 {

    public static void main(String[] args) {
        RearrangeStringKDistanceApart_358 s = new RearrangeStringKDistanceApart_358();
        System.out.println(s.rearrangeString("aabbccdd", 4)); //abcdabcd
        System.out.println(s.rearrangeString("aaabc", 3)); //""
        System.out.println(s.rearrangeString("a", 0)); //""
        System.out.println(s.rearrangeString("aabbcc", 3)); //abcabc
        System.out.println(s.rearrangeString("aaadbbcc", 2)); //abacabcd
    }

    // O(n*log(26)) - time, O(1) - space
    public String rearrangeString(String s, int k) {
        StringBuilder rearranged = new StringBuilder();
        //count frequency of each char
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) { //n
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        //construct a max heap using self-defined comparator, which holds all Map entries, Java is quite verbose
        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((entry1, entry2) -> entry2.getValue() - entry1.getValue());
        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());
        while (!maxHeap.isEmpty()) { //n*log(n)
            Map.Entry<Character, Integer> current = maxHeap.poll();
            rearranged.append(current.getKey());
            current.setValue(current.getValue() - 1);
            waitQueue.offer(current);
            if (waitQueue.size() < k) { // intial k-1 chars, waitQueue not full yet
                continue;
            }
            // release from waitQueue if char is already k apart
            Map.Entry<Character, Integer> front = waitQueue.poll();
            //note that char with 0 count still needs to be placed in waitQueue as a place holder
            if (front.getValue() > 0) {
                maxHeap.offer(front);
            }
        }

        return rearranged.length() == s.length() ? rearranged.toString() : "";
    }
}

