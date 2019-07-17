package dynamic;

/** M
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 How many possible unique paths are there?

 Above is a 7 x 3 grid. How many possible unique paths are there?
 Note: m and n will be at most 100.

 Example 1:
 Input: m = 3, n = 2
 Output: 3
 Explanation:
 From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 1. Right -> Right -> Down
 2. Right -> Down -> Right
 3. Down -> Right -> Right

 Example 2:
 Input: m = 7, n = 3
 Output: 28
 */
public class UniquePaths_62 {

    public static void main(String[] args) {
        UniquePaths_62 s = new UniquePaths_62();
        System.out.println(s.uniquePaths(3,2)); //3
        System.out.println(s.uniquePaths(7,3)); // 28
    }

    // O(2^(max(m,n)))- time, O(max{m, n}) - space
    public int uniquePaths(int m, int n) {
        boolean[][] visited = new boolean[m][n];
        return uniquePaths(m, n, 0, 0, visited);
    }

    private int uniquePaths(int m, int n, int i, int j, boolean[][] visited) {
        if (i < 0 || i > m-1 || j < 0 || j > n-1 || visited[i][j]) {
            return 0;
        }
        if (i == m-1 && j == n-1) {
            return 1;
        }

        int right = uniquePaths(m, n, i+1, j, visited);
        int down = uniquePaths(m, n, i, j+1, visited);

        return right + down;
    }

}
