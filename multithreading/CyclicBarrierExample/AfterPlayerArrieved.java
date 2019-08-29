package multithreading.CyclicBarrierExample;

public class AfterPlayerArrieved implements Runnable {

    @Override
    public void run() {
        System.out.println("All players arrived. Game is starting!");
    }
}
