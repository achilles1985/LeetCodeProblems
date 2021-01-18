package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConparingUtils {

    public static void main(String[] args) {
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));

        final List<WrapperInfo> list = Arrays.asList(new WrapperInfo(10, "John"), new WrapperInfo(9, "Marta"), null);
        list.sort(Comparator.nullsFirst(Comparator.comparingInt(WrapperInfo::getAge).thenComparing(WrapperInfo::getName)));

        System.out.println(list);
    }

    private static class WrapperInfo {
        int age;
        String name;

        WrapperInfo(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }
}
