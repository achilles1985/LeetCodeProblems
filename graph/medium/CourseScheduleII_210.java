package graph.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import utils.SolutionUtils;

/**M
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:
Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .

Example 2:
Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:
    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
 */
/*
Test:
    DAG, DCG, empty prerequisites, zero numCourses
Mistakes:
    Pay attention to description (numCourses must not be equal to prerequisite.length)
Related to cycle detection
 */
public class CourseScheduleII_210 {

    public static void main(String[] args) {
        CourseScheduleII_210 s = new CourseScheduleII_210();
        SolutionUtils.print(s.findOrder(4, new int[][]{{1,2},{2,3},{3,4},{4,1}})); // []
        SolutionUtils.print(s.findOrder(3, new int[][]{})); // [0,1,2]
        SolutionUtils.print(s.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})); //[0,1,2,3] or [0,2,1,3]
        SolutionUtils.print(s.findOrder(2, new int[][]{{1,0}})); //[0,1]
        SolutionUtils.print(s.findOrder(3, new int[][]{{1,0}})); // [2,0,1]
    }

    // O(V+E) - time, O(V) - space, V - number of courses (Topological sort), E - number of dependents
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null) {
            return new int[]{0};
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            graph.computeIfAbsent(prerequisites[i][0], key -> graph.getOrDefault(key, new ArrayList<>())).add(prerequisites[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> recursion = new HashSet<>();
        Queue<Integer> stack = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (visited.contains(i)) {
                continue;
            }
            if (hasCycle(graph, visited, recursion, stack, i)) {
                return new int[]{};
            }
        }
        int size = stack.size();
        int[] result = new int[size];
        int i = 0;
        while (!stack.isEmpty()) {
            result[i++] = stack.poll();
        }
        return result;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> recursion, Queue<Integer> stack, Integer node) {
        if (recursion.contains(node)) {
            return true;
        }
        if (visited.contains(node)) {
            return false;
        }
        recursion.add(node);
        visited.add(node);
        for (Integer child: graph.getOrDefault(node, Collections.emptyList())) {
            if (hasCycle(graph, visited, recursion, stack, child)) {
                return true;
            }
        }
        recursion.remove(node);
        stack.add(node);

        return false;
    }
}
