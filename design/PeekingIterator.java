package design;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**M
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that
 * support the peek() operation -- it essentially peek() at the element that will be returned by the next call to
 * next().
 *
 * Example:
 * Assume that the iterator is initialized to the beginning of the list: [1,2,3].
 *
 * Call next() gets you 1, the first element in the list.
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 * You call next() the final time and it returns 3, the last element.
 * Calling hasNext() after that should return false.
 *
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
public class PeekingIterator<T> implements Iterator<T> {

    private Iterator<T> iterator;
    private T next;

    public PeekingIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    // O(1) - time, space
    public T peek() {
        if (next == null && iterator.hasNext()) {
            next = iterator.next();
        }
        return next;
    }

    // O(1) - time, space
    @Override
    public T next() {
        if (next != null) {
            T toReturn = next;
            next = null;
            return toReturn;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return next != null || iterator.hasNext();
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1","2","3","4");
        PeekingIterator<String> iter = new PeekingIterator(list.iterator());
        System.out.println(iter.hasNext()); //true
        System.out.println(iter.peek()); //1
        System.out.println(iter.peek()); //1
        System.out.println(iter.next()); //1
        System.out.println(iter.next()); //2
        System.out.println(iter.peek()); //3
        System.out.println(iter.peek()); //3
        System.out.println(iter.next()); //3
        System.out.println(iter.hasNext()); //true
        System.out.println(iter.peek()); //4
        System.out.println(iter.hasNext()); //true
    }
}
