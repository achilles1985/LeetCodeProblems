package array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
 Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
 Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
 You need to return the least number of intervals the CPU will take to finish all the given tasks.

 Example:
 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.

 Note:
 The number of tasks is in the range [1, 10000].
 The integer n is in the range [0, 100].
 */
public class TaskScheduler_621 {

    public static void main(String[] args) {
        TaskScheduler_621 s = new TaskScheduler_621();

        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','C'},10)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','B'},2)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','B'},50)); //104
    }

    // https://leetcode.com/problems/task-scheduler/solution/
    // O(n) - time, O(1) - space (heap and temp size will not exceed O(26))
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char t : tasks) {
            counts.put(t, counts.getOrDefault(t, 0) + 1);
        }

        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.addAll(counts.values());
        int cycles = 0;
        while (!maxHeap.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                if (!maxHeap.isEmpty()) {
                    tmp.add(maxHeap.poll());
                }
            }
            for (int cnt : tmp) {
                if (--cnt > 0) {
                    maxHeap.offer(cnt);
                }
            }
            cycles += maxHeap.isEmpty() ? tmp.size() : n+1;
        }

        return cycles;
    }
}
