package graph.AllPathsFromSourceToTarget_797;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class Solution {

    // Time complexity: O()
    // Space: O(V)
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();

        int target = graph.length-1;
        allPathsSourceTargetUtil(0, target, graph, visited, result);

        return result;
    }

    private void allPathsSourceTargetUtil(int node, int target, int[][] graph, Set<Integer> visited, List<List<Integer>> result) {
        if (visited.contains(node)) {
            return;
        }

        if (node == target) {
            List<Integer> path = new ArrayList<>();
            for (int n: visited) {
                path.add(n);
            }
            path.add(target);
            result.add(path);
        }
        visited.add(node);

        for (int child: graph[node]) {
            allPathsSourceTargetUtil(child, target, graph, visited, result);
        }
        visited.remove(node);
    }
}
