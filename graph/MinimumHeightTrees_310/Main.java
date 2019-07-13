package graph.MinimumHeightTrees_310;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.findMinHeightTrees(1, new int[][] {})); // []

        System.out.println(s.findMinHeightTrees(2, new int[][] {{0,1}})); // [0,1]
        System.out.println(s.findMinHeightTrees(4, new int[][] {{1,0}, {1,2}, {1,3}})); // [1]
        System.out.println(s.findMinHeightTrees(6, new int[][] {{0,3}, {1,3}, {2,3}, {4,3}, {5,4}})); // [3,4]
        System.out.println(s.findMinHeightTrees(6, new int[][] {{0,1}, {0,2}, {0,3}, {3,4}, {4,5}})); // [3]
    }
}
