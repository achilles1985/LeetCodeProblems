package recursion;

import java.util.ArrayList;
import java.util.List;

/** E
 * The problem is to findAllPaths all the possible paths from top left to bottom right of a mXn matrix
 * with the constraints that from each cell you can either move only to right or down.
 Examples :
 Input : 1 2 3
         4 5 6
 Output : 1 4 5 6
          1 2 5 6
          1 2 3 6

 Input : 1 2
         3 4
 Output : 1 2 4
          1 3 4
 */
public class FindAllPathFromTopLeftToBottomRight {

    // O(2^(n+m)) - ?
    public List<List<Integer>> findAllPaths(int[][] grid) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(grid, 0, 0, result, new ArrayList());

        return result;
    }

    private void dfs(int arr[][], int row, int col, List<List<Integer>> result, List path) {
        if (row < 0 || row > arr.length-1 || col < 0 || col > arr[0].length-1) {
            return;
        }

        if (row == arr.length-1 && col == arr[0].length-1) {
            ArrayList item = new ArrayList<>(path);
            item.add(arr[row][col]);
            result.add(item);
            return;
        }
        path.add(arr[row][col]);
        dfs(arr, row, col+1, result, path);
        dfs(arr, row+1, col, result, path);
        path.remove(path.size()-1);
    }

    public static void main(String args[]){
        FindAllPathFromTopLeftToBottomRight pam = new FindAllPathFromTopLeftToBottomRight();
        int arr[][] = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}};
        int arr2[][] = {
                {1,2,3},
                {4,5,6}};

        System.out.println(pam.findAllPaths(arr));
        System.out.println(pam.findAllPaths(arr2));
    }
}
