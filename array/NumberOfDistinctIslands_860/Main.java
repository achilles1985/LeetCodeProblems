package array.NumberOfDistinctIslands_860;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.numDistinctIslands(new int[][]{
                {1, 1, 0, 0, 1},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 1, 0, 1, 1}})); // 3

        System.out.println(s.numDistinctIslands(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {0, 0, 0, 1, 1}})); // 1
    }
}
