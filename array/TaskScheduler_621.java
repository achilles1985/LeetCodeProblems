package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** M [marked]
 Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task.
 Tasks could be done in any order. Each task is done in one unit of time. For each unit of time,
 the CPU could complete either one task or just be idle.
 However, there is a non-negative integer n that represents the cooldown period between two same tasks
 (the same letter in the array), that is that there must be at least n units of time between any two same tasks.
 Return the least number of units of times that the CPU will take to finish all the given tasks.

 Example 1:
 Input: tasks = ["A","A","A","B","B","B"], n = 2
 Output: 8
 Explanation:
 A -> B -> idle -> A -> B -> idle -> A -> B
 There is at least 2 units of time between any two same tasks.

 Example 2:
 Input: tasks = ["A","A","A","B","B","B"], n = 0
 Output: 6
 Explanation: On this case any permutation of size 6 would work since n = 0.
 ["A","A","A","B","B","B"]
 ["A","B","A","B","A","B"]
 ["B","B","B","A","A","A"]
 ...
 And so on.

 Example 3:
 Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 Output: 16
 Explanation:
 One possible solution is
 A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

 Constraints:
 1 <= task.length <= 104
 tasks[i] is upper-case English letter.
 The integer n is in the range [0, 100].
 */
/*
Questions:
    1. Only upper case letters? max string length?
    2. aaabbb,n!=0; aaabbb,n=0; aaabbbcccdddeee,n=2 -> abcdeabcdeabcde - in that case no idle and no collisions
 */
public class TaskScheduler_621 {

    public static void main(String[] args) {
        TaskScheduler_621 s = new TaskScheduler_621();

        System.out.println(s.leastInterval2(new char[]{'A','A','A','B','B','B','C','C','C','D','D','E'},2)); //12
        System.out.println(s.leastInterval2(new char[]{'A','A','A','B','B','B'},0)); //6
        System.out.println(s.leastInterval2(new char[]{'A','A','A','A','A','B','B','C'},2)); //13
        System.out.println(s.leastInterval2(new char[]{'A','A','A','B','B','B'},2)); //8

        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','C'},2)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','C'},10)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','B'},2)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','B'},50)); //104
    }

    // O(n) - time, O(1) - space
    public int leastInterval2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0 || n < 0) {
            return 0;
        }
        Map<Character, Integer> freq = new HashMap<>();
        for (char task: tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        List<Integer> values = new ArrayList<>(freq.values());
        Collections.sort(values, Comparator.comparingInt(Integer::intValue).reversed());
        int maxFreq = values.get(0)-1;
        int idle = maxFreq*n;
        if (idle == 0) {
            return tasks.length;
        }
        for (int num = 1; num < values.size() && idle > 0; num++) {
            idle -= Math.min(maxFreq, values.get(num)); // take min, because of aaabbb,n=2
        }
        idle = Math.max(0, idle);

        return tasks.length + idle;
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
