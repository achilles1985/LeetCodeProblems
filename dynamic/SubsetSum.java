package dynamic;

import java.util.HashMap;
import java.util.Map;

/** M
 Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
 Example:
 Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 Output:  True  //There is a subset (4, 5) with sum 9.
 */
// https://www.techiedelight.com/subset-sum-problem/
public class SubsetSum {

    public static void main(String[] args) {
        SubsetSum s = new SubsetSum();
        System.out.println(s.subsetSum(new int[] {3, 34, 4, 12, 5, 2}, 9)); // true
        System.out.println(s.subsetSumDynamicBottomUp(new int[] {3, 34, 4, 12, 5, 2}, 9)); // true
        System.out.println(s.subsetSumDynamicTopDown(new int[] {3, 34, 4, 12, 5, 2}, 9)); // true

        System.out.println(s.subsetSum(new int[] {7, 3, 2, 5, 8}, 12)); // true
        System.out.println(s.subsetSumDynamicBottomUp(new int[] {7, 3, 2, 5, 8}, 12)); // true
        System.out.println(s.subsetSumDynamicTopDown(new int[] {7, 3, 2, 5, 8}, 12)); // true

        System.out.println(s.subsetSum(new int[] {7, 3, 2, 5, 8}, 122)); // false
        System.out.println(s.subsetSumDynamicBottomUp(new int[] {7, 3, 2, 5, 8}, 122)); // false
        System.out.println(s.subsetSumDynamicTopDown(new int[] {7, 3, 2, 5, 8}, 122)); // false
    }

    // O(2^n) - time, O(n) - space
    public boolean subsetSum(int[] arr, int sum) {
        return subsetSum(arr, sum, arr.length);
    }

    // O(n*sum) - time, O(n*sum)
    public boolean subsetSumDynamicTopDown(int[] arr, int sum) {
        Map<String, Boolean> map = new HashMap<>();
        return subsetSumDynamicTopDown(arr, sum, arr.length, map);
    }

    // O(n*sum) - time, space
    public boolean subsetSumDynamicBottomUp(int[] arr, int sum) {
        boolean[][] res = new boolean[arr.length+1][sum+1];
        for (int i = 0; i <= arr.length; i++) {
            res[i][0] = true;
        }

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i-1] > j) {
                    res[i][j] = res[i-1][j];
                } else {
                    res[i][j] = res[i-1][j-arr[i-1]] || res[i-1][j];
                }
            }
        }

        return res[arr.length][sum];
    }

    private boolean subsetSumDynamicTopDown(int[] arr, int sum, int n, Map<String, Boolean> map) {
        String key = sum+","+n;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (sum == 0 && n >= 0) {
            return true;
        }
        if (sum > 0 && n <= 0 || sum < 0) {
            return false;
        }
        boolean res = subsetSumDynamicTopDown(arr, sum - arr[n-1], n-1, map) || subsetSumDynamicTopDown(arr, sum, n-1, map);
        map.put(key, res);

        return map.get(key);
    }

    private boolean subsetSum(int[] arr, int sum, int n) {
        if (sum == 0 && n >= 0) {
            return true;
        }
        if (sum > 0 && n <= 0 || sum < 0) {
            return false;
        }
        return subsetSum(arr, sum - arr[n-1], n-1) || subsetSum(arr, sum, n-1);
    }
}
