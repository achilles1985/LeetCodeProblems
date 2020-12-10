package graph.medium;

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
    Cycle detection
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
        if (numCourses == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: prerequisites) {
            int vertex = edge[0];
            int child = edge[1];
            graph.computeIfAbsent(vertex, (v) -> new ArrayList<>()).add(child);
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        for (int course = 0; course < numCourses; course++) {
            if (visited.contains(course)) {
                continue;
            }
            if (hasCycle(graph, visited, stack, course)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> graph, Set<Integer> visited, Set<Integer> stack, int node) {
        if (stack.contains(node)) {
            return true;
        }

        stack.add(node);
        visited.add(node);
        for (int child: graph.getOrDefault(node, Collections.emptyList())) {
            if (hasCycle(graph, visited, stack, child)) {
                return true;
            }
        }
        stack.remove(node);

        return false;
    }
}
