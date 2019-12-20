package design;

import java.util.LinkedList;
import java.util.Queue;

/**M
 * Design a hit counter which counts the number of hits received in the past 5 minutes.
 * Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made
 * to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the
 * earliest timestamp starts at 1.
 *
 * It is possible that several hits arrive roughly at the same time.
 *
 * Follow up:
 * What if the number of hits per second could be very large? Does your design scale?
 */
public class HitCounter {

    private Queue<Integer> queue;

    /** Initialize your data structure here. */
    public HitCounter() {
        queue = new LinkedList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    // O(1) - time
    public void hit(int timestamp) {
        queue.add(timestamp);
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    // O(n) - time
    public int getHits(int timestamp) {
        while (!queue.isEmpty() && queue.peek() <= timestamp - 300) {
            queue.poll();
        }
        return queue.size();
    }

    public static void main(String[] args) {
        HitCounter s = new HitCounter();
        s.hit(1);
        s.hit(2);
        s.hit(3);
        System.out.println(s.getHits(4)); //3
        s.hit(300);
        System.out.println(s.getHits(300)); //4
        System.out.println(s.getHits(301)); //3
    }
}
