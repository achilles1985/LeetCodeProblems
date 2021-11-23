package design;

import java.util.*;

/**M [marked]
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
public class Flatten_2DVector_251_2 {

    private int outer;
    private int inner;
    private int[][] origin;

    // O(1)
    public Flatten_2DVector_251_2(int[][] v) {
        origin = v;
    }

    // O(1) - time, space
    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return origin[outer][inner++];
    }

    // O(1) - time, space
    public boolean hasNext() {
        advanceIterator();
        return outer < origin.length;
    }

    // If the current outer and inner point to an integer, this method does nothing.
    // Otherwise, inner and outer are advanced until they point to an integer.
    // If there are no more integers, then outer will be equal to vector.length
    // when this method terminates.
    // O(rows) - time
    private void advanceIterator() {
        while (outer < origin.length && inner == origin[outer].length) { // 'while not if' because there can be empty arrays
            inner = 0;
            outer++;
        }
    }

    public static void main(String[] args) {
        Flatten_2DVector_251 s = new Flatten_2DVector_251(new int[][]{{1,2},{3},{4,5}});
        //Flatten_2DVector_251_2 s = new Flatten_2DVector_251_2(new int[][]{{},null,{}});
        //Flatten_2DVector_251_2 s = new Flatten_2DVector_251_2(new int[][]{{1}});
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
