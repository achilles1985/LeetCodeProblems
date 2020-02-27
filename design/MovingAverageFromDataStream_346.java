package design;

import java.util.LinkedList;
import java.util.Queue;

/**E
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Example:
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageFromDataStream_346 {

    private int size;
    private int sum;
    private Queue<Integer> queue;

    public MovingAverageFromDataStream_346(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    // O(1) - time, O(size) - space
    public double next(int val) {
        sum += val;
        queue.add(val);
        if (queue.size() > size) {
            sum -= queue.poll();
        }
        return (double) sum / queue.size();
    }

    public static void main(String[] args) {
        MovingAverageFromDataStream_346 s = new MovingAverageFromDataStream_346(3);
        System.out.println(s.next(1)); //1.0
        System.out.println(s.next(10));//5.5
        System.out.println(s.next(3));//4.66667
        System.out.println(s.next(5));//6.0
    }
}
