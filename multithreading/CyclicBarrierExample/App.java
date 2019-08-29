package multithreading.CyclicBarrierExample;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    private static final int PARTIES = 3;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Program started!");
        CyclicBarrier barrier = new CyclicBarrier(PARTIES, new AfterPlayerArrieved());
        ExecutorService exec = Executors.newFixedThreadPool(PARTIES);
        for (int i = 0; i < PARTIES; i++) {
            exec.submit(new Player(barrier, i + "_player"));
        }
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("Program finished!");
    }
}
