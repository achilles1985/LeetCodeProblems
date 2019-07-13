package graph.GraphValidTree_261;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] edges1 = new int[][]{{0, 1}, {0, 2}, {0, 3}, {3, 4}};
        int[][] edges2 = new int[][]{};

        System.out.println(s.validTree(1, edges1));
        System.out.println(s.validTree(1, edges2));

    }
}
