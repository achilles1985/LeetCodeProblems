package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * <p>
 * Example 1:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * Example 2:
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 * [1,2,2],
 * [5]
 * ]
 */
public class CombinationSumII {

    public static void main(String[] args) {
        CombinationSumII s = new CombinationSumII();
        System.out.println(s.combinationSum(new int[] {2,5,2,1}, 5));
        System.out.println(s.combinationSum(new int[] {10,1,2,7,6,1,5}, 8));
        System.out.println(s.combinationSum(new int[] {2,3,5}, 8));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        dfs(0, 0, nums, list, result, target);

        return result;
    }

    private void dfs(int sum, int start, int[] nums, List<Integer> list, List<List<Integer>> result, int target) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        if (sum > target) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) { // skip duplicates
                continue;
            }
            sum += nums[i];
            list.add(nums[i]);
            dfs(sum, i+1, nums, list, result, target); // i+1 because we cannot reuse same elements
            sum -= list.get(list.size()-1);
            list.remove(list.size()-1);
        }
    }
}
