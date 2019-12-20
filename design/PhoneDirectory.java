package design;

import java.util.LinkedList;
import java.util.Queue;

/**M
Design a Phone Directory which supports the following operations:
    get: Provide a number which is not assigned to anyone.
    check: Check if a number is available or not.
    release: Recycle or release a number.
 */
/*
Mistakes: Clearly understand the task, then proceed with the solution.
 */
public class PhoneDirectory {

    private Queue<Integer> queue;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        queue = new LinkedList<>();
        for (int i = 0; i <= maxNumbers; i++) {
            queue.add(i);
        }
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        return queue.peek() == null ? -1 : queue.poll();
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        for (Integer item: queue) {
            if (number == item) {
                return true;
            }
        }
        return false;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        queue.offer(number);
    }

    public static void main(String[] args) {
        PhoneDirectory directory = new PhoneDirectory(3);
        // It can return any available phone number. Here we assume it returns 0.
        System.out.println(directory.get());
        // Assume it returns 1.
        System.out.println(directory.get());
        // The number 2 is available, so return true.
        System.out.println(directory.check(2)); //true
        // It returns 2, the only number that is left.
        System.out.println(directory.get());
        // The number 2 is no longer available, so return false.
        System.out.println(directory.check(2));

        // Release number 2 back to the pool.
        directory.release(2);
        // Number 2 is available again, return true.
        System.out.println(directory.check(2));
    }
}
