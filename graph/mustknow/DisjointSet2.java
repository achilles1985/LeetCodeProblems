package graph.mustknow;

// Good explanation: https://leetcode.com/articles/redundant-connection/#
public class DisjointSet2 {
    private int[] parent;
    private int[] rank;

    public DisjointSet2(int N) {
        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; ++i)
            parent[i] = i;
    }

    // No path compression
/*    public int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }*/

    // With path compression. Make node points to its parent directly.
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int p1 = find(x);
        int p2 = find(y);
        parent[p1] = p2;
    }

    // The idea of UNION BY RANK is to ensure that when we combine two trees, we try to keep the overall depth of the resulting tree small
    // (https://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/)
    public void unionWithRank(int x, int y) { // Inverse-Ackermann function which boils down to O(1)
        int p1 = find(x);
        int p2 = find(y);
        if (p1 != p2) {
            if (rank[p1] > rank[p2]) {
                parent[y] = p1;
            } else if (rank[p2] > rank[p1]) {
                parent[x] = p2;
            } else {
                parent[y] = x;
                rank[x] += 1;
            }
        }
    }

    public static void main(String[] args) {
        DisjointSet2 ds = new DisjointSet2(5);
        ds.union(0,1);
        ds.union(1,2);
        ds.union(2,3);
        ds.union(3,4);

        System.out.println(ds.find(0));
        System.out.println(ds.find(1));
        System.out.println(ds.find(2));
        System.out.println(ds.find(3));
        System.out.println(ds.find(4));
    }
}
