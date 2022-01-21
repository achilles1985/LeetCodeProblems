package unionfind;

/**M [marked]
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a
 * function to check whether these edges make up a valid tree.
 *
 * Example 1:
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 *
 * Example 2:
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 *
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the
 * same as [1,0] and thus will not appear together in edges.
 */
/*
    Tree is valid if there is no cycles in a graph and it is connected.
 */
public class GraphValidTree_261 {

    public static void main(String[] args) {
        GraphValidTree_261 s = new GraphValidTree_261();
        System.out.println(s.validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}})); // true
        System.out.println(s.validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3},{1,4}})); // false
        System.out.println(s.validTree(4, new int[][]{{0, 1}, {2, 3}})); // false
        System.out.println(s.validTree(4, new int[][]{})); // false
    }

    // O(E + V) - time, O(V) - space (https://leetcode.com/problems/graph-valid-tree/solution/) - analysis
    public boolean validTree(int n, int[][] edges) {
        if (n <= 1) {
            return true;
        }
        if (edges == null || edges.length == 0) {
            return false;
        }

        DisjointSet ds = new DisjointSet(n);
        for (int[] edge: edges) { //E
            if (ds.find(edge[0]) == ds.find(edge[1])) {
                return false;
            }
            ds.union(edge[0], edge[1]);
        }

        return ds.count == 1;
    }

    private static class DisjointSet {
        int[] parent;
        int count;

        DisjointSet(int size) {
            count = size;
            parent = new int[size];
            for (int i = 0; i < size; i++) { //V
                parent[i] = i;
            }
        }

        int find(int node) { // O(1)
            if (node != parent[node]) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        void union(int n1, int n2) { // O(log(n)) - since not using union-by-rank, O(1) - with union-by-rank
            int p1 = find(n1);
            int p2 = find(n2);
            parent[p2] = p1;
            count--;
        }
    }
}
