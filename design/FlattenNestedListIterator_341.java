package design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**M
 * Given a nested list of integers, implement an iterator to flatten it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 *
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,1,2,1,1].
 *
 * Example 2:
 * Input: [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false,
 *              the order of elements returned by next should be: [1,4,6].
 */
public class FlattenNestedListIterator_341 implements Iterator<Integer> {
    private List<Integer> list;
    private Iterator<Integer> iter;

    public FlattenNestedListIterator_341(List<NestedInteger> nestedList) {
        list = new LinkedList<>();
        flatten(nestedList);
        iter = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Integer next() {
        return iter.next();
    }

    // O(n) - time, n - total number of elements
    private void flatten(List<NestedInteger> nestedList) {
        for (NestedInteger item: nestedList) {
            if (item.isInteger()) {
                list.add(item.getInteger());
            } else {
                flatten(item.getList());
            }
        }
    }

     public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        List<NestedInteger> getList();
  }

    public static void main(String[] args) {
        //FlattenNestedListIterator_341 s = new FlattenNestedListIterator_341();
    }
}
