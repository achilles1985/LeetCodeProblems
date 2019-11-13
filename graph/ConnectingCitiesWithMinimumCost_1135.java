package graph;

import java.util.Arrays;

import graph.mustknow.DisjointSet;

/**M
 * There are N cities numbered from 1 to N.
 * You are given connections, where each connections[i] = [city1, city2, cost] represents the cost to connect city1
 * and city2 together.  (A connection is bidirectional: connecting city1 and city2 is the same as connecting city2
 * and city1.)
 * Return the minimum cost so that for every pair of cities, there exists a path of connections (possibly of length
 * 1) that connects those two cities together.  The cost is the sum of the connection costs used. If the task is
 * impossible, return -1.
 *
 * Example 1:
 * Input: N = 3, connections = [[1,2,5],[1,3,6],[2,3,1]]
 * Output: 6
 * Explanation:
 * Choosing any 2 edges will connect all cities so we choose the minimum 2.
 *
 * Example 2:
 * Input: N = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 * Explanation:
 * There is no way to connect all cities even if all edges are used.
 *
 * Note:
 * 1 <= N <= 10000
 * 1 <= connections.length <= 10000
 * 1 <= connections[i][0], connections[i][1] <= N
 * 0 <= connections[i][2] <= 10^5
 * connections[i][0] != connections[i][1]
 */
/*
Minimum spanning tree, we can use UnionFind
 */
public class ConnectingCitiesWithMinimumCost_1135 {

    public static void main(String[] args) {
        ConnectingCitiesWithMinimumCost_1135 s = new ConnectingCitiesWithMinimumCost_1135();
        System.out.println(s.minimumCost(3, new int[][]{{1,2,5},{1,3,6},{2,3,1}})); //6
        System.out.println(s.minimumCost(4, new int[][]{{1,2,5},{3,4,4}})); //-1
    }

    // O(n*log(n)) - time, O(n) - space
    public int minimumCost(int N, int[][] connections) {
        int count = 0;
        //sort edges in ascending order
        Arrays.sort(connections, (e1, e2) -> e1[2]-e2[2]);
        DisjointSet ds = new DisjointSet();
        for (int[] edge: connections) {
            ds.makeSet(edge[0]);;
            ds.makeSet(edge[1]);;
        }
        for (int[] edge: connections) {
            int val1 = edge[0];
            int val2 = edge[1];
            int p1 = ds.findSet(val1);
            int p2 = ds.findSet(val2);
            if (p1 != p2) {
               ds.union(val1, val2);
               count += edge[2];
            }
        }
        // if any of sets has different parent, graph is disconnected
        int parent = ds.findSet(1);
        for (int i = 2; i <= N; i++) {
            if (parent != ds.findSet(i)) {
                return -1;
            }
        }
        return count;
    }
}
