package unionfind;

/**M
 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a
 * function to find the number of connected components in an undirected graph.
 *
 * Example 1:
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]
 *
 *      0          3
 *      |          |
 *      1 --- 2    4
 *
 * Output: 2
 *
 * Example 2:
 *
 * Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]
 *
 *      0           4
 *      |           |
 *      1 --- 2 --- 3
 *
 * Output:  1
 *
 * Note:
 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as
 * [1, 0] and thus will not appear together in edges.
 */
public class NumberOfConnectedComponentsInUndirectedGraph_323 {

    public static void main(String[] args) {
        NumberOfConnectedComponentsInUndirectedGraph_323 s = new NumberOfConnectedComponentsInUndirectedGraph_323();
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}})); //2
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {2,3}, {3, 4}})); //1

        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {3, 4}})); //2
        System.out.println(s.countComponents(5, new int[][] {{0, 1}, {1, 2}, {2,3}, {3, 4}})); //1
    }

    // O(E+V) - time, O(V) - space
    public int countComponents(int n, int[][] edges) {
        DisjointSet ds = new DisjointSet(n);
        for (int[] edge: edges) {
            ds.union(edge[0], edge[1]);
        }
        int counter = 0;
        for (int i = 0; i < ds.parent.length; i++) {
            if (ds.parent[i] == i) {
                counter++;
            }
        }
        return counter;
    }

    private static class DisjointSet {
        int[] parent;

        DisjointSet(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        int find(int node) {
            if (node != parent[node]) {
                parent[node] = find(parent[node]);
            }
            return parent[node];
        }

        void union(int n1, int n2) {
            int p1 = find(n1);
            int p2 = find(n2);
            parent[p2] = p1;
        }
    }
}
