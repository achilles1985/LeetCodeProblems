package heap;

// https://leetcode.com/problems/find-median-from-data-stream/

import java.util.PriorityQueue;
import java.util.Queue;

/** H
 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 For example,
 [2,3,4], the median is 3
 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:
 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.

 Example:
 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2

 Follow up:
 If all integer numbers from the stream are between 0 and 100, how would you optimize it?
 If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
 */
public class MedianFinder {

    private Queue<Integer> maxHeap;
    private Queue<Integer> minHeap;

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a,b) -> b-a);
        minHeap = new PriorityQueue<>();
    }

    // O(log(n)) - time
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
        } else if (maxHeap.size() == minHeap.size()) {
            if (num > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }
        } else {
            if (num < maxHeap.peek()) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
        }
    }

    // O(1) - time
    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return ((double) minHeap.peek() + (double)maxHeap.peek())/2;
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder s = new MedianFinder();

        s.addNum(2);
        s.addNum(3);
        s.addNum(5);
        s.addNum(4);
        System.out.println(s.findMedian());
    }
}
