package dynamic.medium;

import java.util.HashMap;
import java.util.Map;

/** M[marked]
 Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
 Example:
 Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 Output:  True  //There is a subset (4, 5) with sum 9.
 */
// https://www.techiedelight.com/subset-sum-problem/
public class SubsetSum {

    public static void main(String[] args) {
        SubsetSum s = new SubsetSum();

        System.out.println(s.subsetSumBF(new int[] {2,4,5}, 9)); // true
        System.out.println(s.subsetSumBF(new int[] {2,4,5}, 3)); // false

        System.out.println(s.subsetSumMemo(new int[] {2,4,5}, 9)); // true
        System.out.println(s.subsetSumMemo(new int[] {2,4,5}, 3)); // false

        System.out.println(s.subsetSum1(new int[] {2,4,5}, 9)); // true
        System.out.println(s.subsetSumTopDown1(new int[] {2,4,5}, 9)); // true
        System.out.println(s.subsetSumBottomUp1(new int[] {2,4,5}, 9)); // true
        System.out.println(s.subsetSum1(new int[] {2,4,5}, 8)); // false
        System.out.println(s.subsetSumTopDown1(new int[] {2,4,5}, 8)); // false
        System.out.println(s.subsetSumBottomUp1(new int[] {2,4,5}, 8)); // false

        System.out.println(s.subsetSum1(new int[] {3, 34, 4, 12, 5, 2}, 9)); // true
        System.out.println(s.subsetSumDynamicBottomUp(new int[] {3, 34, 4, 12, 5, 2}, 9)); // true
        System.out.println(s.subsetSumDynamicTopDown(new int[] {3, 34, 4, 12, 5, 2}, 9)); // true

        System.out.println(s.subsetSum1(new int[] {7, 3, 2, 5, 8}, 12)); // true
        System.out.println(s.subsetSumDynamicBottomUp(new int[] {7, 3, 2, 5, 8}, 12)); // true
        System.out.println(s.subsetSumDynamicTopDown(new int[] {7, 3, 2, 5, 8}, 12)); // true

        System.out.println(s.subsetSum1(new int[] {7, 3, 2, 5, 8}, 122)); // false
        System.out.println(s.subsetSumDynamicBottomUp(new int[] {7, 3, 2, 5, 8}, 122)); // false
        System.out.println(s.subsetSumDynamicTopDown(new int[] {7, 3, 2, 5, 8}, 122)); // false
    }

    // O(length^sum) - time, O(sum) - space
    public boolean subsetSumBF(int[] arr, int sum) {
        if (sum == 0) {
            return true;
        }
        if (sum < 0) {
            return false;
        }
        for (int num: arr) {
            if (subsetSumBF(arr, sum - num)) {
                return true;
            }
        }
        return false;
    }

    // O(length*sum) - time, O(sum) - space
    public boolean subsetSumMemo(int[] arr, int sum) {
        return helper(arr, sum, new HashMap<>());
    }

    private boolean helper(int[] arr, int sum, HashMap<Integer, Boolean> cache) {
        if (cache.containsKey(sum)) {
            return cache.get(sum);
        }
        if (sum == 0) {
            return true;
        }
        if (sum < 0) {
            return false;
        }
        for (int num: arr) {
            if (helper(arr, sum - num, cache)) {
                cache.put(sum-num, true);
                return true;
            }
        }
        cache.put(sum, false);
        return false;
    }

    // O(2^n) - time, O(n) - space, n - array length
    public boolean subsetSum1(int[] arr, int sum) {
        return subsetSumHelper1(0, 0, arr, sum);
    }

    private boolean subsetSumHelper1(int currIdx, int currSum, int[] arr, int target) {
        if (currSum == target) {
            return true;
        }
        if (currIdx == arr.length && currSum != target) {
            return false;
        }
        boolean include = subsetSumHelper1(currIdx + 1, currSum + arr[currIdx], arr, target);
        boolean exclude = subsetSumHelper1(currIdx + 1, currSum, arr, target);
        return include || exclude;
    }

    // O(n*sum) - time, O(n*sum)
    public boolean subsetSumTopDown1(int[] arr, int sum) {
        Map<String, Boolean> cache = new HashMap<>();
        return subsetSumTopDownHelper1(0, 0, arr, sum, cache);
    }

    private boolean subsetSumTopDownHelper1(int currIdx, int currSum, int[] arr, int sum, Map<String, Boolean> cache) {
        if (currSum == sum) {
            return true;
        }
        String key = currIdx + ":" + currSum;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        if (currIdx == arr.length && currSum != sum) {
            return false;
        }
        boolean result = subsetSumTopDownHelper1(currIdx + 1, currSum + arr[currIdx], arr, sum, cache)
                || subsetSumTopDownHelper1(currIdx + 1, currSum, arr, sum, cache);
        cache.put(key, result);

        return cache.get(key);
    }

    // O(n*sum) - time, space
    public boolean subsetSumBottomUp1(int[] arr, int sum) {
        boolean[][] dp = new boolean[arr.length][sum+1];
        for (int i = 0; i < sum; i++) {
            if (arr[0] == i) {
                dp[0][i] = true;
            }
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j == arr[i]) {
                    dp[i][j] = true;
                }
                else if (j > arr[i]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i]];
                }
            }
        }
        return dp[arr.length-1][sum];
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
