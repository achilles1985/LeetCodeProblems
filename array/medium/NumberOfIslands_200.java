package array.medium;

/**M
 Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 You may assume all four edges of the grid are all surrounded by water.

 Example 1:
 Input: grid = [
 ["1","1","1","1","0"],
 ["1","1","0","1","0"],
 ["1","1","0","0","0"],
 ["0","0","0","0","0"]
 ]
 Output: 1

 Example 2:
 Input: grid = [
 ["1","1","0","0","0"],
 ["1","1","0","0","0"],
 ["0","0","1","0","0"],
 ["0","0","0","1","1"]
 ]
 Output: 3

 Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] is '0' or '1'.
 */
public class NumberOfIslands_200 {

    public static void main(String[] args) {
        NumberOfIslands_200 s = new NumberOfIslands_200();
        System.out.println(s.numIslands(new char[][]{
                {'1','1','1','1'},
                {'1','1','1','1'},
                {'1','1','1','1'},
                {'1','1','1','1'}})); //1
        System.out.println(s.numIslands(new char[][] {
                {'1','0','0','1','0'},
                {'1','0','1','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}})); // 2
        System.out.println(s.numIslands(new char[][] {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}})); // 1
        System.out.println(s.numIslands(new char[][] {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}})); // 3
        System.out.println(s.numIslands(new char[][] {})); // 0
        System.out.println(s.numIslands(new char[][] {{}})); // 0
        System.out.println(s.numIslands(new char[][] {{}, {}})); // 0
    }

    // O(rows*columns) - time, space (since if all are '1', we keep the stack of m*n)
    public int numIslands(char[][] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[0].length; j++) {
                if (chars[i][j] == '1') {
                    dfs(i, j, chars);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int i, int j, char[][] chars) {
        if (i < 0 || i >= chars.length || j < 0 || j >= chars[0].length || chars[i][j] == '0') {
            return;
        }
        chars[i][j] = '0';
        dfs(i, j+1, chars);
        dfs(i+1, j, chars);
        dfs(i, j-1, chars);
        dfs(i-1, j, chars);
    }
}
