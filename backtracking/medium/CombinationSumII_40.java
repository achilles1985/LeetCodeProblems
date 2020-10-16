package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** M
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all UNIQUE combinations in candidates where the candidate numbers sums to target.
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
public class CombinationSumII_40 {

    public static void main(String[] args) {
        CombinationSumII_40 s = new CombinationSumII_40();
        System.out.println(s.combinationSum2(new int[] {1,3,2,1,4,5}, 5)); //[[1, 1, 3], [1, 4], [2, 3], [5]]

        System.out.println(s.combinationSum2(new int[] {2,5,2,1}, 3)); //[[1, 2, 2], [5]]
        System.out.println(s.combinationSum2(new int[] {1,2,2,2,3}, 4)); //[[1, 2, 2], [5]]
        System.out.println(s.combinationSum2(new int[] {2,3,5}, 8)); //[[3, 5]]
        System.out.println(s.combinationSum2(new int[] {10,1,2,7,6,1,5}, 8)); //[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
        System.out.println(s.combinationSum2(new int[] {2,3,5}, 8)); //[[3, 5]]
    }

    // O(n*2^n) - time, space
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumHelper(0, 0, target, new ArrayList<>(), result, candidates);
        return result;
    }

    private void combinationSumHelper(int start, int sum, int target, List<Integer> list, List<List<Integer>> result, int[] nums) {
        if (sum == target) {
            result.add(new ArrayList<>(list)); //O(n)
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (sum + nums[i] > target) { //no go deeper if condition true
                continue;
            }
            if (i > start && nums[i] == nums[i-1]) { // skip duplicates
                continue;
            }
            list.add(nums[i]);
            combinationSumHelper(i+1, sum + nums[i], target, list, result, nums); // i+1 because we cannot reuse same elements
            list.remove(list.size()-1);
        }
    }

}
