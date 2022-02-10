package assessments.microsoft;

import java.util.*;

/**
 * You are given an undirected graph consisting of N vertices, numbered from 1 to N, and M edges.
 * The graph described by two arrays, A and B, both of length M. A pair (
 * A[K], B[K]),for K from 0 to M-1, describes an edge between vertex A[K]and B[K].
 *
 * Your task is to assign all values from range [1...N] to the vertices of the graph, gi
 * given one number to each of the vertices. Do it in such a way that the sum over all edges of the values at the edges' endpoints is maximum.
 *
 * For example, given N=5, A=[2,2,1,2], B=[1,3,4,4], the graph has four edges: (
 * 2,3),(1,4),(2,4). In order to obtain the maximum sum of weights, you can assign the following values to the verteces: 3,5,2,4,1.
 *
 * 5,val=1
 *         8         7
 * 1,val=3 - 2,val=5 - 3,val=2
  7|       9|
 * 4,val=4
 *
 * This way we obtain the sum of values at all edges' endpoints eauls tp  7+8+7+9=31
 * Given a positive integer N and two arrays A,B of M, returns the maximum possible sum of values of the edges's endpoints.
 *
 * Example 1
 * Input: N=3, A=[1], B=[3]
 * Output: 5
 *              2,val=1
 *         5
 * 1,val=2 --- 3,val=3
 *
 * Example 2
 * Input: N=4, A=[1,3], B=[2,4]
 * Output: 10
 *                      3
 *              3,val=3 -- 4,val=1
 *
 *             7
 *    1,val=4 -- 2,val=3
 *
 *  Constrains:
 *  N [2...100_000]
 *  M [1...100_000]
 *  All elements of arrays A and B [1..N]
 *  no self-loop in a graph
 *  no multiple edges between the same vertices
 *  N*M is smaller than or equal to 1000_000_000
 */
public class AssignWeightToMaximizeSumOfGraphEdges {

    public static void main(String[] args) {
        AssignWeightToMaximizeSumOfGraphEdges s = new AssignWeightToMaximizeSumOfGraphEdges();
        System.out.println(s.solution(5, new int[]{2,2,1,2}, new int[]{1,3,4,4})); //31
    }

    public int solution(int N, int[] A, int[] B) {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            edges.add(new int[]{A[i],B[i]});
        }
        Map<Integer,List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
            indegree.put(from, indegree.getOrDefault(to, 0) + 1);
        }
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1]-a[1]);
        for (int key: indegree.keySet()) {
            maxHeap.add(new int[]{key, indegree.get(key)});
        }

        Map<Integer,Integer> nodeToValue = new HashMap<>();
        while (!maxHeap.isEmpty()) {
            int[] pop = maxHeap.poll();
            nodeToValue.put(pop[0], N--);
        }
        int sum = 0;
        for (int[] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            sum += nodeToValue.getOrDefault(from,0);
            sum += nodeToValue.getOrDefault(to,0);
        }

        return sum;
    }
}
