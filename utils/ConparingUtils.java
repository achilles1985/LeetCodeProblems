package utils;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ConparingUtils {

    public static void main(String[] args) {
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e[2]));
    }
}
