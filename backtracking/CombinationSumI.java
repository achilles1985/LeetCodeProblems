package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/
 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 The same repeated number may be chosen from candidates unlimited number of times.
 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.

 Example 1:
 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]

 Example 2:
 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]
 */
public class CombinationSumI {

    public static void main(String[] args) {
        CombinationSumI s = new CombinationSumI();
        System.out.println(s.combinationSum(new int[] {2,3,5}, 8));
        System.out.println(s.combinationSum(new int[] {2,3,6,7}, 7));
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, target, new ArrayList<>(), result, 0);

        return result;
    }

    private void dfs(int[] nums, int sum, int target, List<Integer> list, List<List<Integer>> result, int start) {
        if (sum == target) {
            result.add(new ArrayList<>(list));
            return;
        }

        if (sum > target) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            sum += nums[i];
            dfs(nums, sum, target, list, result, i); // not i + 1 because we can reuse same elements
            sum -= list.get(list.size()-1);
            list.remove(list.size()-1);
        }
    }
}
