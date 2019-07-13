package stack.MInStack_155;

/**
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
public class MinStack {

    private static final int CAPACITY = 32;

    private int[] stack = new int[CAPACITY];
    private int size;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if (size >= CAPACITY) {
            throw new RuntimeException("Stack is full");
        }
        stack[size++] = x;
    }

    public void pop() {
        if (size <= 0) {
            throw new RuntimeException("Stack is empty");
        }
        size--;
    }

    public int top() {
        if (size <= 0) {
            throw new RuntimeException("Stack is empty");
        }
        return stack[--size];
    }

    // O(n) - time
    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            if (min > stack[i]) {
                min = stack[i];
            }
        }

        return min;
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
}
