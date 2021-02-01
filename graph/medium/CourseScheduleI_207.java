package graph.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**M [marked]
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

    // BF: check cycle for each node (without visited map) O(E+V^2) - time, O(V) - space

    // O(V+E) - time, O(V) - space (Cycle detection)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) {
            return false;
        }
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graph = buildGraph(prerequisites); // E
        Map<Integer, Integer> visited = new HashMap<>();
        for (int course = 0; course < numCourses; course++) { // V
            if (!visited.containsKey(course)) {
                if (hasCycle(graph, course, visited)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: edges) {
            graph.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(edge[1]);
        }
        return graph;
    }

    private boolean hasCycle(Map<Integer, List<Integer>> graph, int node, Map<Integer, Integer> visited) {
        if (visited.containsKey(node) && visited.get(node) == -1) {
            return true;
        }
        visited.put(node, -1);
        for (Integer child: graph.getOrDefault(node, Collections.emptyList())) {
            if (hasCycle(graph, child, visited)) {
                return true;
            }
        }
        visited.put(node, 1);

        return false;
    }
}
