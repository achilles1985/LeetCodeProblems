package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**M [marked]
 * https://leetcode.com/problems/combination-sum/solution/
 Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 find all UNIQUE combinations in candidates where the candidate numbers sums to target.
 The same repeated number may be chosen from candidates unlimited number of times.
 Note:
 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.

 Example 1:
 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7]
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
public class CombinationSumI_39 {

    public static void main(String[] args) {
        CombinationSumI_39 s = new CombinationSumI_39();
        System.out.println(s.combinationSum(new int[] {2,3,5}, 8)); //[[2, 2, 2, 2], [2, 3, 3], [3, 5]]
        System.out.println(s.combinationSum(new int[] {2,3,6,7}, 7)); //[[2, 2, 3], [7]]
    }

    // O(len^target) - time, O(target) - space
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), result, 0);

        return new ArrayList<>(result);
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        helper2(candidates, target, new ArrayList(), result, 0);

        return new ArrayList(result);
    }

    void helper2(int[] nums, int target, List<Integer> temp, List<List<Integer>> result, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (target - nums[i] < 0) {
                continue;
            }
            temp.add(nums[i]);
            helper2(nums, target - nums[i], temp, result, i);
            temp.remove(temp.size()-1);
        }
    }

    private void dfs(int[] nums, int sum, int target, List<Integer> list,  List<List<Integer>>  result, int start) {
        if (sum == target) {
            //Collections.sort(list); // if all the numbers are unique, there will be no duplicate tuples (like, 2,2,3; 3,2,2, etc.)
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                continue;
            }
            list.add(nums[i]);
            //sum += nums[i];
            dfs(nums, sum + nums[i], target, list, result, i); // not i + 1 because we can reuse same elements
            //sum -= list.get(list.size()-1);
            list.remove(list.size()-1);
        }
    }



    // Incorrect since int i = 0 and we start from the beginning of the array instead of from the current index
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        helper(candidates, target, new ArrayList<>(), 0, result);

        return result;
    }

    private void helper(int[] candidates, int target, List<Integer> temp, int sum, List<List<Integer>>result) {
        if (sum == target) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (sum + candidates[i] > target) {
                continue;
            }
            temp.add(candidates[i]);
            helper(candidates, target, temp, sum + candidates[i], result);
            temp.remove(temp.size()-1);
        }
    }
}
