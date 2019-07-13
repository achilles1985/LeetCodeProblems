package heap.TopKFrequentElements_347;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 Given a non-empty array of integers, return the k most frequent elements.
 Example 1:
 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]

 Example 2:
 Input: nums = [1], k = 1
 Output: [1]

 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class Solution {

    // O(n (build map) + n*log(k)) or just O(n*log(k)) - time, O(n) - space
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int number: nums) {
            map.put(number, map.getOrDefault(number, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1).compareTo(map.get(n2)));
        for (Integer key: map.keySet()) {
            heap.add(key);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> list = new LinkedList<>();
        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }

        return list;
    }
}
