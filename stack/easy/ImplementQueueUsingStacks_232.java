package stack.easy;

import java.util.Stack;

/** E [MARKED]
 * Implement the following operations of a queue using stacks.
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * <p>
 * Example:
 * MyQueue queue = new MyQueue();
 * <p>
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // returns 1
 * queue.pop();   // returns 1
 * queue.empty(); // returns false
 * <p>
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue),
 * as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
public class ImplementQueueUsingStacks_232 {

    private Stack<Integer> pushStack;
    private Stack<Integer> popStack;

    private int front;

    /** Initialize your data structure here. */
    public ImplementQueueUsingStacks_232() {
        pushStack = new Stack<>();
        popStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    // O(1) - time, O(1) - space
    public void push(int x) {
        if (pushStack.isEmpty()) {
            front = x;
        }
        pushStack.push(x);

    }

    /** Removes the element from in front of queue and returns that element. */
    // O(n) - worst time, O(1) - average time
    public int pop() {
        ensurePopStackIsNotEmpty();
        Integer popped = popStack.pop();
        if (!popStack.isEmpty()) {
            front  = popStack.peek();
        }
        return popped;
    }

    /** Get the front element. */
    // O(1) - time, O(1) - space
    public int peek() {
        return front;
    }

    private void ensurePopStackIsNotEmpty() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return pushStack.isEmpty() && popStack.isEmpty();
    }

    public static void main(String[] args) {
        ImplementQueueUsingStacks_232 queue = new ImplementQueueUsingStacks_232();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // returns 1
        System.out.println(queue.pop());   // returns 1
        System.out.println(queue.empty()); // returns false
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
