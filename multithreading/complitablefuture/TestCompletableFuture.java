package multithreading.complitablefuture;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class TestCompletableFuture {

    private static final Map<Integer, Employee> employees = new HashMap<>();
    static {
        employees.put(1, new Employee(1, "John"));
        employees.put(2, new Employee(2, "Tom"));
        employees.put(3, new Employee(3, "Adam"));
        employees.put(4, new Employee(4, "Monika"));
        employees.put(5, new Employee(5, "Kathy"));
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(String.format("Number of cores=%s. Thread=%s", Runtime.getRuntime().availableProcessors(), Thread.currentThread().getName()));
        for (Integer id : Arrays.asList(1, 2, 3, 4, 5,6,7,8,9,10)) {
            System.out.println("Started processing id=" + id);
            CompletableFuture.supplyAsync(() -> firstOperation(id))
                    .thenApply((employee) -> secondOperation(employee))
                    .thenApply((employee) -> thirdOperation(employee))
                    .thenAccept((employee) -> fourthOperation(employee));
            System.out.println("Finished processing id=" + id);
        }
        Thread.sleep(5000);

        System.out.println("Finished program");
    }

    private static void fourthOperation(Employee employee) {
        System.out.println(String.format("Finished processing employee with id=%s. Thread=%s", employee.id, Thread.currentThread().getName()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

    private static Employee thirdOperation(Employee employee) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return employee;
    }

    private static Employee secondOperation(Employee employee) {
        employee.age = new Random().nextInt(50);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employee;
    }

    private static Employee firstOperation(Integer id) {
        System.out.println(String.format("Started processing employee with id=%s. Thread=%s", id, Thread.currentThread().getName()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return employees.getOrDefault(id, new Employee(-1, "default"));
    }


    private static class Employee {
        int id;
        String name;
        int age;

        public Employee(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
