package multithreading.Violatile;

import java.util.Scanner;

public class Before {

    private boolean running = true;

    public void task() {
        while(running) {
            System.out.println("Do something");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shoutDown() {
        running = false;
    }

    public static void main(String[] args) {
        Before s = new Before();
        Thread t1 = new Thread(s::task);
        Thread t2 = new Thread(s::shoutDown);
        t1.start();
        System.out.println("Press return to stop");

        Scanner scanner = new Scanner(System.in);
        scanner.next();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
