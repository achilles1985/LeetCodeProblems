package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**M
 * In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a
 * node that is terminal (that is, it has no outgoing directed edges), we stop.
 * Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More
 * specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a
 * terminal node in less than K steps.
 *
 * Which nodes are eventually safe?  Return them as an array in sorted order.
 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in
 * the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.
 *
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Here is a diagram of the above graph.
 *
 * Illustration of graph
 * Note:
 *     graph will have length at most 10000.
 *     The number of edges in the graph will not exceed 32000.
 *     Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].
 */
/*
 Cycle detection, find nodes that do not produce cycle
 */
public class FindEventualSafeStates_802 {

    public static void main(String[] args) {
        FindEventualSafeStates_802 s = new FindEventualSafeStates_802();
        System.out.println(s.eventualSafeNodes(new int[][]{{},{0,2,3,4},{3},{4},{}})); //[0,1,2,3,4]
        System.out.println(s.eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}})); //[2,4,5,6]
    }

    // O(V+E) - time , O(V) - space
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int N = graph.length;
        int[] color = new int[N];
        List<Integer> ans = new ArrayList();

        for (int i = 0; i < N; ++i)
            if (dfs(i, color, graph)) {
                ans.add(i);
            }
        return ans;
    }

    // colors: WHITE 0, GRAY 1, BLACK 2;
    public boolean dfs(int node, int[] color, int[][] graph) {
        if (color[node] > 0) {
            return color[node] == 2;
        }

        color[node] = 1;
        for (int nei: graph[node]) {
            if (color[node] == 2) continue;
            if (color[nei] == 1 || !dfs(nei, color, graph)) {
                return false;
            }
        }

        color[node] = 2;
        return true;
    }
}
