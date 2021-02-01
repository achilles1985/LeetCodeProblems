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
 Input: tasks = ['A','A','A','B','B','B'], n = 2
 Output: 8
 Explanation:
 A -> B -> idle -> A -> B -> idle -> A -> B
 There is at least 2 units of time between any two same tasks.

 Example 2:
 Input: tasks = ['A','A','A','B','B','B'], n = 0
 Output: 6
 Explanation: On this case any permutation of size 6 would work since n = 0.
 ['A','A','A','B','B','B']
 ['A','B','A','B','A','B']
 ['B','B','B','A','A','A']
 ...
 And so on.

 Example 3:
 Input: tasks = ['A','A','A','A','A','A','B','C','D','E','F','G'], n = 2
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
        System.out.println(s.leastInterval3(new char[]{'G','C','A','H','A','G','G','F','G','J','H','C','A','G','E','A','H','E','F','D','B','D','H','H','E','G','F','B','C','G','F','H','J','F','A','C','G','D','I','J','A','G','D','F','B','F','H','I','G','J','G','H','F','E','H','J','C','E','H','F','C','E','F','H','H','I','G','A','G','D','C','B','I','D','B','C','J','I','B','G','C','H','D','I','A','B','A','J','C','E','B','F','B','J','J','D','D','H','I','I','B','A','E','H','J','J','A','J','E','H','G','B','F','C','H','C','B','J','B','A','H','B','D','I','F','A','E','J','H','C','E','G','F','G','B','G','C','G','A','H','E','F','H','F','C','G','B','I','E','B','J','D','B','B','G','C','A','J','B','J','J','F','J','C','A','G','J','E','G','J','C','D','D','A','I','A','J','F','H','J','D','D','D','C','E','D','D','F','B','A','J','D','I','H','B','A','F','E','B','J','A','H','D','E','I','B','H','C','C','C','G','C','B','E','A','G','H','H','A','I','A','B','A','D','A','I','E','C','C','D','A','B','H','D','E','C','A','H','B','I','A','B','E','H','C','B','A','D','H','E','J','B','J','A','B','G','J','J','F','F','H','I','A','H','F','C','H','D','H','C','C','E','I','G','J','H','D','E','I','J','C','C','H','J','C','G','I','E','D','E','H','J','A','H','D','A','B','F','I','F','J','J','H','D','I','C','G','J','C','C','D','B','E','B','E','B','G','B','A','C','F','E','H','B','D','C','H','F','A','I','A','E','J','F','A','E','B','I','G','H','D','B','F','D','B','I','B','E','D','I','D','F','A','E','H','B','I','G','F','D','E','B','E','C','C','C','J','J','C','H','I','B','H','F','H','F','D','J','D','D','H','H','C','D','A','J','D','F','D','G','B','I','F','J','J','C','C','I','F','G','F','C','E','G','E','F','D','A','I','I','H','G','H','H','A','J','D','J','G','F','G','E','E','A','H','B','G','A','J','J','E','I','H','A','G','E','C','D','I','B','E','A','G','A','C','E','B','J','C','B','A','D','J','E','J','I','F','F','C','B','I','H','C','F','B','C','G','D','A','A','B','F','C','D','B','I','I','H','H','J','A','F','J','F','J','F','H','G','F','D','J','G','I','E','B','C','G','I','F','F','J','H','H','G','A','A','J','C','G','F','B','A','A','E','E','A','E','I','G','F','D','B','I','F','A','B','J','F','F','J','B','F','J','F','J','F','I','E','J','H','D','G','G','D','F','G','B','J','F','J','A','J','E','G','H','I','E','G','D','I','B','D','J','A','A','G','A','I','I','A','A','I','I','H','E','C','A','G','I','F','F','C','D','J','J','I','A','A','F','C','J','G','C','C','H','E','A','H','F','B','J','G','I','A','A','H','G','B','E','G','D','I','C','G','J','C','C','I','H','B','D','J','H','B','J','H','B','F','J','E','J','A','G','H','B','E','H','B','F','F','H','E','B','E','G','H','J','G','J','B','H','C','H','A','A','B','E','I','H','B','I','D','J','J','C','D','G','I','J','G','J','D','F','J','E','F','D','E','B','D','B','C','B','B','C','C','I','F','D','E','I','G','G','I','B','H','G','J','A','A','H','I','I','H','A','I','F','C','D','A','C','G','E','G','E','E','H','D','C','G','D','I','A','G','G','D','A','H','H','I','F','E','I','A','D','H','B','B','G','I','C','G','B','I','I','D','F','F','C','C','A','I','E','A','E','J','A','H','C','D','A','C','B','G','H','G','J','G','I','H','B','A','C','H','I','D','D','C','F','G','B','H','E','B','B','H','C','B','G','G','C','F','B','E','J','B','B','I','D','H','D','I','I','A','A','H','G','F','B','J','F','D','E','G','F','A','G','G','D','A','B','B','B','J','A','F','H','H','D','C','J','I','A','H','G','C','J','I','F','J','C','A','E','C','H','J','H','H','F','G','E','A','C','F','J','H','D','G','G','D','D','C','B','H','B','C','E','F','B','D','J','H','J','J','J','A','F','F','D','E','F','C','I','B','H','H','D','E','A','I','A','B','F','G','F','F','I','E','E','G','A','I','D','F','C','H','E','C','G','H','F','F','H','J','H','G','A','E','H','B','G','G','D','D','D','F','I','A','F','F','D','E','H','J','E','D','D','A','J','F','E','E','E','F','I','D','A','F','F','J','E','I','J','D','D','G','A','C','G','G','I','E','G','E','H','E','D','E','J','B','G','I','J','C','H','C','C','A','A','B','C','G','B','D','I','D','E','H','J','J','B','F','E','J','H','H','I','G','B','D'},1)); //1000
        
        System.out.println(s.leastInterval3(new char[]{'A','A','A','B','B','B'},2)); //8
        System.out.println(s.leastInterval3(new char[]{'A','A','A','B','B','B','C','C','C','D','D','E'},2)); //12

        System.out.println(s.leastInterval2(new char[]{'A','A','A','B','B','B'},0)); //6
        System.out.println(s.leastInterval2(new char[]{'A','A','A','A','A','B','B','C'},2)); //13
        System.out.println(s.leastInterval2(new char[]{'A','A','A','B','B','B'},2)); //8

        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','C'},2)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','C'},10)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','B'},2)); //8
        System.out.println(s.leastInterval(new char[]{'A','A','A','B','B','B'},50)); //104
    }

    public int leastInterval3(char[] tasks, int n) {
        int[] frequency = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            frequency[tasks[i] - 'A']++;
        }
        Arrays.sort(frequency);
        int maxFrequent = frequency[25];
        int idle = (maxFrequent-1)*n;
        if (idle == 0) {
            return tasks.length;
        }
        for (int i = frequency.length-2; i >= 0 && idle > 0; i--) {
            idle -= Math.min(frequency[i], maxFrequent-1); // in case aaabbb
        }
        idle = Math.max(0, idle); // idle can go negative

        return tasks.length + idle;
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
