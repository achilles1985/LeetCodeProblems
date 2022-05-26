package practice;

import java.util.HashMap;
import java.util.Map;

public class MemoryLeak {

    public static void main(String[] args) throws InterruptedException {
        MemoryLeak m = new MemoryLeak();
        m.provideLeak1();
    }

    public void provideLeak1() throws InterruptedException {
        Map<Person, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000000000; i++) {
            map.put( new Person("John Smith", 35), i);
            Thread.sleep(50);
            System.out.println("added");
        }
    }

    private static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
