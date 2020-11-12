package design.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class FlattenNestedListIterator2 {

    public class NestedIterator implements Iterator<Integer> {

        // In Java, the Stack class is considered deprecated. Best practice is to use
        // a Deque instead. We'll use addFirst() for push, and removeFirst() for pop.
        private Deque<NestedInteger> stack;

        // O(n+l), where n - number of integers, l - number of lists
        public NestedIterator(List<NestedInteger> nestedList) {
            // The constructor puts them on in the order we require. No need to reverse.
            stack = new ArrayDeque(nestedList);
        }

        @Override
        public Integer next() {
            // As per java specs, throw an exception if there's no elements left.
            if (!hasNext()) throw new NoSuchElementException();
            // hasNext ensures the stack top is now an integer. Pop and return
            // this integer.
            return stack.removeFirst().getInteger();
        }


        @Override
        public boolean hasNext() {
            // Check if there are integers left by getting one onto the top of stack.
            makeStackTopAnInteger();
            // If there are any integers remaining, one will be on the top of the stack,
            // and therefore the stack can't possibly be empty.
            return !stack.isEmpty();
        }


        private void makeStackTopAnInteger() {
            // While there are items remaining on the stack and the front of
            // stack is a list (i.e. not integer), keep unpacking.
            while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
                // Put the NestedIntegers onto the stack in reverse order.
                List<NestedInteger> nestedList = stack.removeFirst().getList();
                for (int i = nestedList.size() - 1; i >= 0; i--) {
                    stack.addFirst(nestedList.get(i));
                }
            }
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
