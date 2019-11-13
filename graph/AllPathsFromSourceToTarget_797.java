package graph;

import java.util.ArrayList;
import java.util.List;

/** M
 * Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.
 The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

 Example:
 Input: [[1,2], [3], [3], []]
 Output: [[0,1,3],[0,2,3]]
 Explanation: The graph looks like this:
 0--->1
 |    |
 v    v
 2--->3
 There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.

 Note:
 The number of nodes in the graph will be in the range [2, 15].
 You can findAllPaths different paths in any order, but you should keep the order of nodes inside one path.
 */
/*
 * Test case
 * Source or destination vertex does not exist in the graph
 * There is no path between src to dest vertex
 */
public class AllPathsFromSourceToTarget_797 {

    public static void main(String[] args) {
        AllPathsFromSourceToTarget_797 s = new AllPathsFromSourceToTarget_797();
        System.out.println(s.allPathsSourceTarget(new int[][]{{1, 2}, {3}, {3}, {}})); // [[0,1,3],[0,2,3]]
        System.out.println(s.allPathsSourceTarget(new int[][]{{1}, {}})); // [[0,1]]
    }

    // O(n) - time,n - number of vertices, O(n) - space (depth of the stack)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null || graph.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        dfs(0, graph.length-1, graph, new ArrayList<>(), result);

        return result;
    }

    private void dfs(int node, int target, int[][] graph, List<Integer> path, List<List<Integer>> result) {
        path.add(node);
        if (node == target) {
            result.add(new ArrayList<>(path));
        }
        for (int children: graph[node]) {
            dfs(children, target, graph, path, result);
        }
        path.remove(path.size()-1);
    }

}
