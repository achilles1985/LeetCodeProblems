package heap.medium;

import utils.SolutionUtils;

import java.util.*;

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

        System.out.println(s.topKFrequent(new int[] {1,1,1,2,2,2,3,3,3,4,4,5,5,6,7}, 4)); // [1,2,3,4,5]
        System.out.println(s.topKFrequent(new int[] {5,3,1,1,1,3,73,1}, 2)); // [1,3]
        System.out.println(s.topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2));
        System.out.println(s.topKFrequent(new int[] {1}, 1)); // [1]

        System.out.println(s.topKFrequent2(new int[] {1,1,1,2,2,3}, 2)); // [1,2]
        System.out.println(s.topKFrequent2(new int[] {1}, 1)); // [1]

        SolutionUtils.print(s.topKFrequent3(new int[] {4,1,-1,2,-1,2,3}, 2)); // [2,-1]
        SolutionUtils.print(s.topKFrequent3(new int[] {1,1,1,2,2,3}, 2)); // [1,2]
        SolutionUtils.print(s.topKFrequent3(new int[] {1}, 1)); // [1]
    }

    // O(n*log(k)) - time, O(n+k) - space
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

    // O(n) - time, space, n - array size. Bucket sort
    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> numberToFrequency = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numberToFrequency.put(nums[i], numberToFrequency.getOrDefault(nums[i], 0) + 1);
        }
        List[] buckets = new List[nums.length + 1];
        for (Integer number: numberToFrequency.keySet()) {
            int frequency = numberToFrequency.get(number);
            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList();
            }
            buckets[frequency].add(number);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = buckets.length - 1; i >= 0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                result.addAll(buckets[i]);
            }
        }
        return result;
    }

    // O(n) - time, space (bucket sort)
    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            frequency.put(nums[i], frequency.getOrDefault(nums[i], 0) + 1);
        }
        int max = Collections.max(frequency.values());
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            buckets.add(new ArrayList<>());
        }
        for (Map.Entry<Integer,Integer> e: frequency.entrySet()) {
            buckets.get(e.getValue()).add(e.getKey());
        }

        int[] result = new int[k];
        int m = 0;
        for (int i = buckets.size()-1; i>= 0; i--) {
            List<Integer> list = buckets.get(i);
            for (int j = 0; j < list.size(); j++) {
                result[m++] = list.get(j);
                if (m == k) {
                    return result;
                }
            }
        }
        return new int[]{};
    }

    // O(n) - time, space (bucket sort)
    public int[] topKFrequent4(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Map.Entry<Integer, Integer> maxEntry = Collections.max(map.entrySet(), (e1,e2) -> e1.getValue() - e2.getValue());
        int max = maxEntry.getValue();
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            buckets.add(new ArrayList<>());
        }
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            buckets.get(entry.getValue()).add(entry.getKey());
        }
        int[] result = new int[k];
        int j = 0;
        while (j < k) {
            List<Integer> bucket = buckets.get(max--);
            for (int i = 0; i < bucket.size(); i++) {
                result[j++] = bucket.get(i);
                if (j == k) {
                    return result;
                }
            }
        }
        return result;
    }
}
