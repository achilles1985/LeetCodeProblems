package utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class DequeTest {

    public static void main(String[] args) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(1);
        deque.add(2);
        deque.add(3);
        System.out.println(deque.peekFirst()); //1
        System.out.println(deque.peekLast()); //3

        System.out.println(deque.getFirst()); //1
        System.out.println(deque.getLast()); //3
    }
}
