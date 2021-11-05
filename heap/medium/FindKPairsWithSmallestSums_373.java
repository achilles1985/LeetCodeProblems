package heap.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
 You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

 Example 1:
 Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 Output: [[1,2],[1,4],[1,6]]
 Explanation: The first 3 pairs are returned from the sequence:
 [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]

 Example 2:
 Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 Output: [1,1],[1,1]
 Explanation: The first 2 pairs are returned from the sequence:
 [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]

 Example 3:
 Input: nums1 = [1,2], nums2 = [3], k = 3
 Output: [1,3],[2,3]
 Explanation: All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class FindKPairsWithSmallestSums_373 {

    public static void main(String[] args) {
        FindKPairsWithSmallestSums_373 s = new FindKPairsWithSmallestSums_373();
        System.out.println(s.kSmallestPairs(new int[] {1,7,11}, new int[] {2,4,6}, 3)); // [[1,2],[1,4],[1,6]]
        System.out.println(s.kSmallestPairs(new int[] {1,1,2}, new int[] {1,2,3}, 2)); // [1,1],[1,1]
        System.out.println(s.kSmallestPairs(new int[] {1,2}, new int[] {3}, 3)); // [1,3],[2,3]
    }

    // O(n*m*log(k)) - time
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new ArrayList<>();
        }
        Queue<Pair> heap = new PriorityQueue<>((p1, p2) -> p2.sum-p1.sum);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                heap.add(new Pair(nums1[i], nums2[j]));
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        while (!heap.isEmpty()) {
            Pair pair = heap.poll();
            res.add(Arrays.asList(pair.left, pair.right));
        }

        return res;
    }

    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
    // O(k*log(k)) - time, O(k) - space. Merge k-sorted lists
    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        Queue<int[]> minHeap = new PriorityQueue<>((a,b)->a[0]+a[1]-b[0]-b[1]);
        List<int[]> result = new ArrayList<>();
        if(nums1.length==0 || nums2.length==0 || k==0) {
            return result;
        }
        for(int i=0; i<nums1.length && i<k; i++) {
            minHeap.offer(new int[]{nums1[i], nums2[0], 0});
        }
        while (k-- > 0 && !minHeap.isEmpty()){
            int[] cur = minHeap.poll();
            result.add(new int[]{cur[0], cur[1]});
            if(cur[2] == nums2.length-1) continue;
            minHeap.offer(new int[]{cur[0],nums2[cur[2]+1], cur[2]+1});
        }
        return result;
    }

    private static class Pair {
        private int left;
        private int right;
        private int sum;

        public Pair(int left, int right) {
            this.left = left;
            this.right = right;
            this.sum = left + right;
        }
    }
}
