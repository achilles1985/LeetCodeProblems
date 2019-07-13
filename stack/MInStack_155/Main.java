package stack.MInStack_155;

public class Main {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Min=" + minStack.getMin());   // --> Returns -3.
        minStack.pop();
        System.out.println("Top=" + minStack.top());      // --> Returns 0.
        System.out.println("Min=" + minStack.getMin());   // --> Returns -2.
    }
}
