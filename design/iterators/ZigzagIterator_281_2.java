package design.iterators;

import java.util.*;

/**M
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 *
 * Example:
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should
 * be: [1,3,2,4,5,6].
 *
 * Follow up:
 * What if you are given k 1d vectors? How well can your code be extended to such cases?
 *
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to
 * you, replace "Zigzag" with "Cyclic". For example:
 *
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 *
 * Output: [1,4,8,2,5,9,3,6,7].
 */
public class ZigzagIterator_281_2 {

    private Queue<Iterator<Integer>> queue;

    // O(K) - space, k - number of lists
    public ZigzagIterator_281_2(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if (!v1.isEmpty()) {
            queue.add(v1.iterator());
        }
        if (!v2.isEmpty()) {
            queue.add(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> iter = queue.poll();
        int val = iter.next();
        if (iter.hasNext()) {
            queue.add(iter);
        }
        return val;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public static void main(String[] args) {
        ZigzagIterator_281_2 s = new ZigzagIterator_281_2(Arrays.asList(1,2), Arrays.asList(3,4,5,6));
        while (s.hasNext()) {
            System.out.println(s.next());
        }
    }
}
