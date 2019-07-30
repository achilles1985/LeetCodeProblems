package heap;

// https://leetcode.com/problems/kth-largest-element-in-an-array/

import java.util.PriorityQueue;
import java.util.Queue;

/** M
 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 Example 1:
 Input: [3,2,1,5,6,4] and k = 2
 Output: 5

 Example 2:
 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4

 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElementInArray {

    public static void main(String[] args) {
        KthLargestElementInArray s = new KthLargestElementInArray();
        System.out.println(s.findKthLargest(new int[] {3,2,1,5,6,4}, 2)); //5
        System.out.println(s.findKthLargest(new int[] {3,2,3,1,2,4,5,5,6}, 4)); //4
    }

    // O(n*log(k)) - time
    public int findKthLargest(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num: nums) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.peek();
    }
}
