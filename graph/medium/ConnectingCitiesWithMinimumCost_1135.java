package graph.medium;

import java.util.Arrays;

/**M [MARKED]
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
        System.out.println(s.minimumCost(5, new int[][]{{2,1,50459},{3,2,47477},{4,2,52585},{5,3,16477}})); //166998
    }

    // O(E*log(E)) - time, O(V) - space, where E - number of connections, V - number of cities
    public int minimumCost(int N, int[][] connections) {
        Arrays.sort(connections, (e1,e2) -> e1[2] - e2[2]);
        DisjointSet ds = new DisjointSet(N);
        int cost = 0;
        for (int[] edge: connections) {
            if (ds.find(edge[0]) != ds.find(edge[1])) {
                ds.union(edge[0], edge[1]);
                cost += edge[2];
            }
        }

        return ds.size == 1 ? cost : -1;
    }

    private static class DisjointSet {
        int[] parent;
        int size;

        public DisjointSet(int size) {
            this.size = size;
            parent = new int[size+1];
            for (int i = 0; i <= size; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int x, int y) {
            int p1 = find(x);
            int p2 = find(y);
            parent[p2] = p1;
            size--;
        }
    }
}
