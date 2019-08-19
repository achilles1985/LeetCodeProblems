package heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
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
public class TopKFrequentElements_347 {

    public static void main(String[] args) {
        TopKFrequentElements_347 s = new TopKFrequentElements_347();
        System.out.println(s.topKFrequent(new int[] {1,1,1,2,2,3}, 2)); // [1,2]
        System.out.println(s.topKFrequent(new int[] {1}, 1)); // [1]
    }

    // O(n*log(k)) - time
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for (Integer key: map.keySet()) {
            heap.add(key);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            res.add(heap.poll());
        }

        return res;
    }
}