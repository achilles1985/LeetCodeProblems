package heap.KthLargestElementInStream_703;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

 Example:
 int k = 3;
 int[] arr = [4,5,8,2];
 KthLargest kthLargest = new KthLargest(3, arr);
 kthLargest.add(3);   // returns 4
 kthLargest.add(5);   // returns 5
 kthLargest.add(10);  // returns 5
 kthLargest.add(9);   // returns 8
 kthLargest.add(4);   // returns 8
 */
public class KthLargest {

    private int k;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(16, Comparator.reverseOrder());

    public KthLargest(int k, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        this.k = k;
    }

    public int add(int val) {
        queue.add(val);
        int[] temp = new int[k];
        for (int i = 0; i < k-1; i++) {
            temp[i] = queue.poll();
        }
        Integer res = queue.peek();
        for (int i = 0; i < temp.length; i++) {
            queue.add(temp[i]);
        }

        return res;
    }

    // https://leetcode.com/problems/kth-largest-element-in-a-stream/discuss/275203/Easy-Java-solution-with-PriorityQueue
    public KthLargest(int k, int[] nums, String test) {
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
    }

    public int add2(int val) {
        queue.add(val);
        if (queue.size() > k) {
            queue.poll();
        }
        return queue.peek();
    }
}
