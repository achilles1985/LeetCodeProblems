package binarySearch.SearchA2DMatrix_74;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        boolean result = s.searchMatrix2(new int[][]{{1,2,3,4,5}, {6,7,8,9,10}, {12,13,14,15,16}}, 5);
        //boolean result = s.searchMatrix(new int[][]{{1}}, 1);
        //boolean result = s.searchMatrix(new int[][]{{1}, {2}, {3}}, 4);
        //boolean result = s.searchMatrix(new int[][]{{1,2}, {}, {4}}, 4);
        System.out.println(result);
    }
}
