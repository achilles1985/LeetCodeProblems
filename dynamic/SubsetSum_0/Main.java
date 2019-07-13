package dynamic.SubsetSum_0;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {7, 3, 2, 5, 8};
        System.out.println(s.subsetSum(arr, arr.length - 1, 12));
        System.out.println(s.subsetSum2(arr, new HashMap<>(), arr.length - 1, 12));
        System.out.println(s.subsetSum3(arr, 12));
    }
}
