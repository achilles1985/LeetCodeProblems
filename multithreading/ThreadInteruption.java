package multithreading;

import java.util.Collections;

public class ThreadInteruption {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 1; i < 10000000; i++) {
                double res = Math.pow(2, i);
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Result=" + res);
                    break;
                }
            }
        });
        t.start();

        try {
            Thread.currentThread().sleep(100);
            t.interrupt();
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Task completed");
    }
}
