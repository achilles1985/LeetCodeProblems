package stack.easy;

import java.util.Stack;

/**E
 *  Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.

 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.
 */
/*
    Store (value, min) in stack
 */
public class MinStack_155 {

    private Stack<Pair> stack;

    /** initialize your data structure here. */
    public MinStack_155() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.add(new Pair(x,x));
        } else {
            if (x > stack.peek().min) {
                stack.push(new Pair(x, stack.peek().min));
            } else {
                stack.push(new Pair(x, x));
            }
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    // O(1) - time
    public int getMin() {
        return stack.peek().min;
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */

    private static class Pair {
        int value;
        int min;

        public Pair(int value, int min) {
            this.value = value;
            this.min = min;
        }
    }

    public static void main(String[] args) {
        MinStack_155 s = new MinStack_155();
        s.push(-2);
        s.push(0);
        s.push(-3);
        System.out.println(s.getMin()); //-3
        s.pop();
        System.out.println(s.top()); //0
        System.out.println(s.getMin()); //-2
    }
}
