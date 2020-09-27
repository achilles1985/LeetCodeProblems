package graph.medium;

import javafx.util.Pair;

import java.util.*;

/**
 * M
 * You are given equations in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating-point number). Given some queries, return the answers. If the answer does not exist, return -1.0.
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 * <p>
 * Example 1:
 * Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 * <p>
 * Example 2:
 * Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 * Example 3:
 * <p>
 * Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * <p>
 * Constraints:
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= equations[i][0], equations[i][1] <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= queries[i][0], queries[i][1] <= 5
 * equations[i][0], equations[i][1], queries[i][0], queries[i][1] consist of lower case English letters and digits.
 */
public class EvaluateDivision_399 {

    public static void main(String[] args) {
        EvaluateDivision_399 s = new EvaluateDivision_399();
        double[] res = (s.calcEquation(
                Arrays.asList(Arrays.asList("a", "b"), Arrays.asList("b", "c")),
                new double[]{2.0, 3.0},
                Arrays.asList(Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"), Arrays.asList("a", "a"), Arrays.asList("x", "x"))));
        int f = 0;
    }

    // O(n*m) - time, where n - number of equations, m - queries. O(n) - space
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Pair<String, Double>>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            String from = equation.get(0);
            String to = equation.get(1);
            graph.computeIfAbsent(from, k -> graph.getOrDefault(k, new ArrayList<>())).add(new Pair<>(to, values[i]));
            graph.computeIfAbsent(to, k -> graph.getOrDefault(k, new ArrayList<>())).add(new Pair<>(from, 1 / values[i]));
        }

        double[] results = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            String from = query.get(0);
            String to = query.get(1);
            results[i] = dfs(from, to, new HashSet<>(), graph);
        }

        return results;
    }

    private double dfs(String node, String target, Set<String> visited, Map<String, List<Pair<String, Double>>> graph) {
        if (!(graph.containsKey(node) && graph.containsKey(target))) {
            return -1.0;
        }
        if (node.equals(target)) {
            return 1.0;
        }

        visited.add(node);
        for (Pair<String, Double> ng : graph.get(node)) {
            if (!visited.contains(ng.getKey())) {
                double ans = dfs(ng.getKey(), target, visited, graph);
                if (ans != -1.0) {
                    return ans * ng.getValue();
                }
            }
        }

        return -1.0;
    }
}
