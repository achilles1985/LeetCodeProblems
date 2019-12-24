package design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FlattenNestedListIterator_341 implements Iterator<Integer> {
    private List<Integer> list;
    private Iterator<Integer> iter;

    public FlattenNestedListIterator_341(List<NestedInteger> nestedList) {
        list = new LinkedList<>();
        flatten(nestedList);
        iter = list.iterator();
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

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Integer next() {
        return iter.next();
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
}
