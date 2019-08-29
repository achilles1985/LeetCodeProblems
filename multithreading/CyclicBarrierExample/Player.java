package multithreading.CyclicBarrierExample;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable {

    private final CyclicBarrier barrier;
    private String name;

    public Player(CyclicBarrier barrier, String name) {
        this.barrier = barrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println("Player_" + name + " started preparing for the game");
            Thread.currentThread().sleep(new Random().nextInt(1000));
            barrier.await();
            System.out.println("Player_" + name + " started running");
            Thread.currentThread().sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("Player_" + name + " finished");
    }
}
