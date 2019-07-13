package graph.RedundantConnection_684;

import utils.SolutionUtils;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix1 = new int[][] {{1,2}, {1,3}, {2,3}};
        int[][] matrix2 = new int[][] {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};

        SolutionUtils.printArray(SolutionUtils.toObjectArray((s.findRedundantConnection(matrix1)))); // [2,3]
        SolutionUtils.printArray(SolutionUtils.toObjectArray(s.findRedundantConnection(matrix2))); // [1,4]
    }
}
