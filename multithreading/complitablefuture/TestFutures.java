package multithreading.complitablefuture;

import java.util.concurrent.*;

public class TestFutures {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        System.out.println("Started program");
        for (int i = 0; i < 5; i++) {
            System.out.println("Started processing id=" + i);
            Future<Integer> future = executorService.submit(new MyTask(i));
            Integer result = future.get() + 10;
            //int result = -1;
            System.out.println("Finished processing id=" + i + ", result=" + result);
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.HOURS);
        //Thread.currentThread().join(); no correct
        System.out.println("Finished program, taken=" + (System.currentTimeMillis() - start));
    }

    private static class MyTask implements Callable<Integer> {

        private final int id;

        public MyTask(int id) {
            this.id = id;
        }

        @Override
        public Integer call() throws Exception {
            firstOperation(id);
            secondOperation(id);
            thirdOperation(id);
            fourthOperation(id);

            return id + 10;
        }

        private void firstOperation(int id) {
            System.out.println(String.format("Started processing employee with id=%s. Thread=%s", id, Thread.currentThread().getName()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void secondOperation(int id) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void thirdOperation(int id) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void fourthOperation(int id) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("Finished processing employee with id=%s. Thread=%s", id, Thread.currentThread().getName()));
        }

    }
}
