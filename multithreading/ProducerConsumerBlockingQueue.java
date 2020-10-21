package multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerBlockingQueue {
    private static final int CAPACITY = 10;

    private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);

    public static void main(String[] args) {
        ProducerConsumerBlockingQueue s = new ProducerConsumerBlockingQueue();
        Thread t1 = new Thread(() -> {
            try {
                s.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                s.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }

    private void producer() throws InterruptedException {
        Random r = new Random();
        while (true) {
            queue.put(r.nextInt(100));
        }
    }

    private void consumer() throws InterruptedException {
        while (true) {
            System.out.println("Polled=" + queue.take() + ", size=" + queue.size());
        }
    }
}
