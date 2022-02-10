package multithreading.Synchronized;

public class Before {

    private int count;

    public void increment() {
        count++;
    }

    public static void main(String[] args) {
        Before s = new Before();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                s.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                s.increment();
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count: " + s.count);
    }
}
