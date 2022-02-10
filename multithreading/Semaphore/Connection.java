package multithreading.Semaphore;

import java.util.concurrent.Semaphore;

public enum Connection {
    INSTANCE;

    private int connections;
    private Semaphore semaphore = new Semaphore(10); // limits number of threads working with the resource simultaniously

    public void connect() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    public void doConnect() {
        synchronized (this) {
            connections++;
            System.out.println("Current connections=" + connections);
        }

        try {
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
    }
}
