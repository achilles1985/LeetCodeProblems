package heap;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/** E
 * Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream.
 For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

 Example:
 int k = 3;
 int[] arr = [4,5,8,2];
 KthLargest kthLargest = new KthLargest(3, arr);
 kthLargest.add(3);   // returns 4
 kthLargest.add(5);   // returns 5
 kthLargest.add(10);  // returns 5
 kthLargest.add(9);   // returns 8
 kthLargest.add(4);   // returns 8

 Note:
 You may assume that nums' length ≥ k-1 and k ≥ 1.
 */
public class KthLargestElementInStream_703 {
    private Queue<Integer> queue = new PriorityQueue<>();

    private int k;

    public static void main(String[] args) {
        KthLargestElementInStream_703 s= new KthLargestElementInStream_703(3, new int[] {4,5,8,2});
        System.out.println(s.add(3)); // 4
        System.out.println(s.add(5)); // 5
        System.out.println(s.add(10)); // 5
        System.out.println(s.add(9)); // 8
        System.out.println(s.add(4)); // 8
    }

    public KthLargestElementInStream_703(int k, int[] nums) {
        this.k = k;
        for (int num: nums) {
            queue.add(num);
            if (queue.size() > k) {
                queue.poll();
            }
        }
    }

    // O(n*log(k)) - time, O(1) - space, no additional space but queue itself
    public int add(int val) {
        queue.add(val);
        if (queue.size() > k) {
            queue.poll();
        }

        return queue.peek();
    }
}
