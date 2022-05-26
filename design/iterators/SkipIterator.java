package design.iterators;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**M [marked]
 * Design a SkipIterator that supports a method skip(int val).
 * When it is called the next element equals val in iterator sequence should be skipped
 */
/*
    Q:
        1) What to return if number of elements is 1 and we skip it, like it.skip(1); it.next()?
        2) What hasNext() should return if there is only one element left and it's skipped?
 */
public class SkipIterator {

    private Map<Integer, Integer> countDistinct;
    private Iterator<Integer> it;

    public SkipIterator(Iterator<Integer> it) {
        countDistinct = new HashMap<>();
        this.it = it;
    }

    public boolean hasNext() {
        return it.hasNext();
    }

    // O(1) - time
    public Integer next() {
        int val = it.next();
        if (countDistinct.containsKey(val) && countDistinct.get(val) > 0) {
            countDistinct.put(val, countDistinct.get(val) - 1);
            return it.next();
        }
        return val;
    }

    /**
     * The input parameter is an int, indicating that the next element equals 'val' needs to be skipped.
     * This method can be called multiple times in a row. skip(5), skip(5) means that the next two 5s should be skipped.
     */
    public void skip(int val) {
        countDistinct.put(val, countDistinct.getOrDefault(val, 0) + 1);
    }

    public static void main(String[] args) {
        SkipIterator s = new SkipIterator(Arrays.asList(2, 3, 5, 6, 5, 7, 5, -1, 5, 10).iterator());
        System.out.println(s.hasNext()); // true
        System.out.println(s.next()); // returns 2
        s.skip(5);
        System.out.println(s.next()); // returns 3
        System.out.println(s.next()); // returns 6 because 5 should be skipped
        System.out.println(s.next()); // returns 5
        s.skip(5);
        s.skip(5);
        System.out.println(s.next()); // returns 7
        System.out.println(s.next()); // returns -1
        System.out.println(s.next()); // returns 10
        System.out.println(s.hasNext()); // false
        //System.out.println(s.next()); // error

        SkipIterator s2 = new SkipIterator(Arrays.asList(1,2,3).iterator());
        System.out.println(s2.hasNext()); // true
        s2.skip(2);
        s2.skip(1);
        s2.skip(3);
        System.out.println(s2.hasNext()); // false
    }
}
