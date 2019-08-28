package multithreading.CountDownLatchExample;

import java.util.concurrent.CountDownLatch;

public class Task implements Runnable {

    private final CountDownLatch latch;

    public Task(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println(Thread.currentThread().getId() + "thread started");
            Thread.currentThread().sleep(3000);
            System.out.println(Thread.currentThread().getId() + "thread finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }
}
