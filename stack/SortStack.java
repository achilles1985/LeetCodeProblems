package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack,
 but you may not copy the elements into any other data structure (such as an array).
 The stack supports the following operations: push, pop, peek, and isEmpty.
 */
public class SortStack {

    public static void main(String[] args) {
        SortStack s = new SortStack();
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(2);
        stack.push(4);
        stack.push(3);
        stack.push(1);
        Stack<Integer> sorted = s.sort(stack);

        print(sorted);
    }

    // O(n^2) - time, O(n) - space
    public Stack<Integer> sort(Stack<Integer> unsorted) {
        if (unsorted == null || unsorted.isEmpty()) {
            return null;
        }

        Stack<Integer> sorted = new Stack<>();
        sorted.push(unsorted.pop());
        while (!unsorted.isEmpty()) {
            Integer popped = unsorted.pop();
            int size = 0;
            while (!sorted.isEmpty() && popped > sorted.peek()) {
                unsorted.push(sorted.pop());
                size++;
            }
            sorted.push(popped);
            while (size > 0) {
                sorted.push(unsorted.pop());
                size--;
            }
        }

        return sorted;
    }

    private static void print(Stack<Integer> stack) {
        List<Integer> l = new ArrayList<>(stack);
        System.out.println(l);
    }
}
