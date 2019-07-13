package graph.CourseScheduleII_210;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** M
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
public class Solution {

    // O(n) - time, O(n) - space, n - number of courses
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new LinkedList<>();
        Set<Integer> dfs = new HashSet<>();

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge: prerequisites) {
            int v1 = edge[0];
            int v2 = edge[1];

            List<Integer> adjacents = graph.getOrDefault(v1, new ArrayList<>());
            adjacents.add(v2);
            graph.put(v1, adjacents);
        }

        for (int i = 0; i < numCourses; i++) {
            if (visited.contains(i)) {
                continue;
            }

            if (hasCycle(visited, stack, i, graph, dfs)) {
                return new int[0];
            }
        }

        int res[] = new int[numCourses];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pollLast();
        }

        return res;
    }

    private boolean hasCycle(Set<Integer> visited, Deque<Integer> stack, int course, Map<Integer, List<Integer>> graph, Set<Integer> dfs) {
        if (dfs.contains(course)) {
            return true;
        }
        if (visited.contains(course)) {
            return false;
        }
        visited.add(course);
        dfs.add(course);
        for (int child: graph.getOrDefault(course, new ArrayList<>())) {
            if (hasCycle(visited, stack, child, graph, dfs)) {
                return true;
            }
        }
        dfs.remove(course);
        stack.push(course);

        return false;
    }

}
