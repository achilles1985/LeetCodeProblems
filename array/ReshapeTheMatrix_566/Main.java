package array.ReshapeTheMatrix_566;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n = 10000/200;
        int a[][] = {{1,2}, {3,4}};
        int r = 1;
        int c = 4;
        Solution solution = new Solution();
        int[][] result = solution.matrixReshapeWithDivisionsAnsModules(a, r, c);

        int[] b = new int[] {1,2,3,3};
        Arrays.stream(b).filter(e-> e != -1).sum();
    }
}
