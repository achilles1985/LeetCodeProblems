package design;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PrimitiveIterator;
import java.util.Queue;
import java.util.stream.IntStream;

/**M
 * Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and
 * hasNext.
 *
 * Example:
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 * iterator.next(); // return 1
 * iterator.next(); // return 2
 * iterator.next(); // return 3
 * iterator.hasNext(); // return true
 * iterator.hasNext(); // return true
 * iterator.next(); // return 4
 * iterator.hasNext(); // return false
 *
 * Notes:
 *     Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted
 *     across multiple test cases. Please see here for more details.
 *     You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d
 *     vector when next() is called.
 *
 * Follow up:
 * As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */
public class Flatten_2DVector_251 {

    private Queue<Integer> queue;

    public Flatten_2DVector_251(int[][] v) {
        Iterator<int[]> outerIter = Arrays.stream(v).iterator();
        Iterator<Integer> innerIter = IntStream.of(v[0]).boxed().iterator();

        queue = new LinkedList<>();
        for (int i = 0; i < v.length; i++) {
            if (v[i] != null && v[i].length != 0) {
                for (int j = 0; j < v[i].length; j++) {
                    queue.add(v[i][j]);
                }
            }
        }
    }

    // O(1) - time, space
    public int next() {
        return queue.poll();
    }

    // O(1) - time, space
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public static void main(String[] args) {
        //Flatten_2DVector_251 s = new Flatten_2DVector_251(new int[][]{{1,2},{3},{4,5}});
        //Flatten_2DVector_251 s = new Flatten_2DVector_251(new int[][]{{},null,{}});
        Flatten_2DVector_251 s = new Flatten_2DVector_251(new int[][]{{1}});
        System.out.println(s.next()); //1
        System.out.println(s.next()); //2
        System.out.println(s.next()); //3
        System.out.println(s.hasNext()); //true
        System.out.println(s.hasNext()); //true
        System.out.println(s.next()); //4
        System.out.println(s.next()); //5
        System.out.println(s.hasNext()); //false
    }
}
