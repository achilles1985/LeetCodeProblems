package design;

import java.math.BigDecimal;
import java.util.*;

/**H
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 * Examples:
 *
 * [2,3,4] , the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the
 * very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position
 * . Your job is to output the median array for each window in the original array.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *
 * Window position                Median
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 *  1 [3  -1  -3] 5  3  6  7       -1
 *  1  3 [-1  -3  5] 3  6  7       -1
 *  1  3  -1 [-3  5  3] 6  7       3
 *  1  3  -1  -3 [5  3  6] 7       5
 *  1  3  -1  -3  5 [3  6  7]      6
 *
 * Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 *
 * Note:
 * You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
 * Answers within 10^-5 of the actual value will be accepted as correct.
 */
/*
Questions:
    1. If k > arr.length or 0?
    2. max element in an array?
 */
/*
    To be improved
 */
public class SlidingWindowMedian_480 {

    public static void main(String[] args) {
        SlidingWindowMedian_480 s = new SlidingWindowMedian_480();
        System.out.println(s.medianSlidingWindow(new int[]{2147483647, 2147483647}, 2));
        System.out.println(s.medianSlidingWindow(new int[]{1,4,2,3}, 4));
        System.out.println(s.medianSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3));
    }

    // O((n-k)*(k*log(k)+k)) - time, O(1) - space
    public double[] medianSlidingWindowBF(int[] nums, int k) {
        int m = 0;
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            int [] arr = new int[k];
            for (int j = 0; j < k; j++) {
                arr[j] = nums[i+j];
            }
            Arrays.sort(arr);
            if (k%2 == 0) {
                BigDecimal res = BigDecimal.valueOf(arr[k/2]).add(BigDecimal.valueOf(arr[k/2 - 1])).divide(BigDecimal.valueOf(2.0));
                result[m++] = res.doubleValue();
            } else {
                double v = arr[k/2];
                result[m++] = v;
            }
        }
        return result;
    }

    // ?
    public double[] medianSlidingWindow(int[] nums, int k) {
        Queue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Long> minHeap = new PriorityQueue<>();
        double[] res = new double[nums.length - k + 1];
        for(int i = 0; i < k; i++){
            insert(nums[i], minHeap, maxHeap);
        }
        for(int i = 0; i < res.length; i++){
            if(maxHeap.size() == minHeap.size()){
                res[i] = maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
            } else{
                res[i] = maxHeap.peek();
            }
            if(i < res.length - 1){
                delete((long)nums[i], minHeap, maxHeap);
                insert((long)nums[i + k], minHeap, maxHeap);
            }
        }
        return res;
    }

    public void insert(long num, Queue<Long> minHeap, Queue<Long> maxHeap){
        if(maxHeap.isEmpty() || num <= maxHeap.peek()){
            maxHeap.offer(num);
        } else{
            minHeap.offer(num);
        }
        rebalance(minHeap, maxHeap);
    }

    public void delete(long num, Queue<Long> minHeap, Queue<Long> maxHeap){
        if(maxHeap.contains(num)){
            maxHeap.remove(num);
        } else{
            minHeap.remove(num);
        }
        rebalance(minHeap, maxHeap);
    }

    public void rebalance(Queue<Long> minHeap, Queue<Long> maxHeap){
        if(maxHeap.size() > minHeap.size() + 1){
            minHeap.offer(maxHeap.poll());
        }else if(maxHeap.size() < minHeap.size()){
            maxHeap.offer(minHeap.poll());
        }
    }
}
