package multithreading;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class ProducerConsumerLowLevel {
    private static final int INITIAL_CAPACITY = 10;

    private Queue<Integer> queue = new PriorityQueue<>(INITIAL_CAPACITY);
    private Object lock = new Object();

    public void producer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            synchronized (lock) {
                while (queue.size() == INITIAL_CAPACITY) {
                    lock.wait();
                }
                queue.add(r.nextInt(100));
                lock.notify();
            }
        }
    }

    public void consumer() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (queue.isEmpty()) {
                    lock.wait();
                }
                System.out.println("Polled=" + queue.poll() + ", size=" + queue.size());
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerLowLevel a = new ProducerConsumerLowLevel();
        Thread t1 = new Thread(() -> {
            try {
                a.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                a.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
    }
}
