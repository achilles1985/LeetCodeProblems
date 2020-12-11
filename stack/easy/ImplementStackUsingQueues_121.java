package stack.easy;

import java.util.LinkedList;
import java.util.Queue;

/** E [marked]
 Implement the following operations of a stack using queues.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 empty() -- Return whether the stack is empty.

 Example:
 MyStack stack = new MyStack();
 stack.push(1);
 stack.push(2);
 stack.top();   // returns 2
 stack.pop();   // returns 2
 stack.empty(); // returns false

 Notes:
 You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
 You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 */

/*
In push operation, the new element is always enqueued to q1. In pop() operation, if q2 is empty then all the elements except the last, are moved to q2.
Finally the last element is dequeued from q1 and returned. (https://www.geeksforgeeks.org/implement-stack-using-queue/)
 */
public class ImplementStackUsingQueues_121 {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int last;

    /** Initialize your data structure here. */
    public ImplementStackUsingQueues_121() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    // O(1) - time
    public void push(int x) {
        q1.offer(x);
        last = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    // O(n) - time
    public int pop() {
        while (!q1.isEmpty()) {
            last = q1.poll();
            q2.add(last);
        }

        int res = q1.poll();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return res;
    }

    /** Get the top element. */
    // O(1) - time
    public int top() {
        return last;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }

    public static void main(String[] args) {
        ImplementStackUsingQueues_121 stack = new ImplementStackUsingQueues_121();

        stack.push(1);
        stack.push(2);
        System.out.println(stack.top());   // returns 2
        System.out.println(stack.pop());   // returns 2
        System.out.println(stack.empty()); // returns false
    }
}

/** We can use only one queue if while pushing remove elements and add them to the queue except the new pushed.
 * public void push(int x) {
    q1.add(x);
    int sz = q1.size();
    while (sz > 1) {
      q1.add(q1.remove());
      sz--;
   }
 }
 */

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
