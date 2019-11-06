package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**M
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is
 * expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 *
 * Example 1:
 * Input: 2, [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 *
 * Note:
 *     The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how
 *     a graph is represented.
 *     You may assume that there are no duplicate edges in the input prerequisites.
 */
/*
Test:
    empty graph, zero numCourse
 */
public class CourseScheduleI_207 {

    public static void main(String[] args) {
        CourseScheduleI_207 s = new CourseScheduleI_207();
        System.out.println(s.canFinish(2, new int[][]{{1,0}})); // true
        System.out.println(s.canFinish(2, new int[][]{{1,0},{0,1}})); // false
        System.out.println(s.canFinish(4, new int[][]{{1,2},{2,3},{3,4}})); // true
        System.out.println(s.canFinish(4, new int[][]{{1,2},{2,3},{3,4},{4,1}})); // false
    }

    // O(V+E) - time, O(V) - space (Cycle detection)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || numCourses == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            graph.computeIfAbsent(prerequisites[i][0], key -> graph.getOrDefault(key, new ArrayList<>())).add(prerequisites[i][1]);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        for (Integer node: graph.keySet()) {
            if (visited.contains(node)) {
                continue;
            }
            if (!canFinishHelper(graph, visited, stack, node)) {
                return false;
            }
        }

        return true;
    }

    private boolean canFinishHelper(Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> stack, Integer node) {
        if (stack.contains(node)) {
            return false;
        }
        stack.add(node);
        visited.add(node);
        for (Integer adjacent: graph.getOrDefault(node, Collections.emptyList())) {
            if (!canFinishHelper(graph, visited, stack, adjacent)) {
                return false;
            }
        }
        stack.remove(node);
        return true;
    }
}
