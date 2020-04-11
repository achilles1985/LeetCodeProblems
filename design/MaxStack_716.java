package design;

import java.util.Stack;

public class MaxStack_716 {

    private Stack<Integer> stack;
    private Stack<Integer> maxStack;

    /** initialize your data structure here. */
    public MaxStack_716() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        int max = maxStack.isEmpty() ? x : maxStack.peek();
        stack.push(x);
        maxStack.push(Math.max(max, x));
    }

    public int pop() {
        if (maxStack.isEmpty()) {
            return 0;
        }
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        if (stack.isEmpty()) {
            return 0;
        }
        return stack.peek();
    }

    public int peekMax() {
        if (maxStack.isEmpty()) {
            return 0;
        }
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Stack<Integer> buffer = new Stack();
        while (top() != max) {
            buffer.push(pop());
        }
        pop();
        while (!buffer.isEmpty()) {
            push(buffer.pop());
        }
        return max;
    }

    public static void main(String[] args) {
        MaxStack_716 stack = new MaxStack_716();
        stack.push(5);
        stack.push(1);
        System.out.println(stack.popMax()); //5
        System.out.println(stack.peekMax()); //1
    }

}
