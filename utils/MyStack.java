package utils;

import java.util.Arrays;

public class MyStack {

    private static int CAPACITY = 3;

    private int[] arr = new int[CAPACITY];
    private int size;

    public void push(int i) {
        ensureCapacity();
        arr[size++] = i;
    }

    private void ensureCapacity() {
        if (size == CAPACITY) {
            CAPACITY *= 2;
            arr = Arrays.copyOf(arr, CAPACITY);
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return arr[--size];
    }

    private boolean isEmpty() {
        return size == 0 ? true : false;
    }

    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.pop();
        s.pop();
        s.push(14);
        s.push(15);
    }

}
