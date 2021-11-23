package design.iterators;

import java.util.*;

// O(D) - space, D - max depth of stack frames
public class FlattenNestedListIterator_341_2 implements Iterator<Integer> {
    private Deque<ListIterator<NestedInteger>> stackOfIterators = new ArrayDeque<>();
    private Integer peeked = null;

    public FlattenNestedListIterator_341_2(List<NestedInteger> nestedList) {
        stackOfIterators.push(nestedList.listIterator());
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        // hasNext() called setPeeked(), which ensures peeked has the next integer
        // in it. We need to clear the peeked field so that the element is returned
        // again.
        Integer result = peeked;
        peeked = null;
        return result;
    }

    @Override
    public boolean hasNext() {
        // Try to set the peeked field. If any integers are remaining, it will
        // contain the next one to be returned after this call.
        setPeeked();
        return peeked != null;
    }

    private void setPeeked() {
        if (peeked != null) {
            return;
        }
        while (!stackOfIterators.isEmpty()) {
            if (!stackOfIterators.peekFirst().hasNext()) {
                stackOfIterators.pop();
                continue;
            }
            NestedInteger next = stackOfIterators.peekFirst().next();
            if (next.isInteger()) {
                peeked = next.getInteger();
                return;
            }
            stackOfIterators.push(next.getList().listIterator());
        }
    }

    private interface NestedInteger {
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
