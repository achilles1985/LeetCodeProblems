package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

/** M [marked]
 * https://leetcode.com/problems/permutations/#/description
 * Given a collection of distinct numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
/*
    Questions:
    1. All numbers are distinct?
    2. results with/without duplicates?
 */
// https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
public class Permutations_46 {

    public static void main(String[] args) {
        Permutations_46 s = new Permutations_46();
        System.out.println(s.permute(new int[]{1,2,3}));
        System.out.println(s.permute(new int[]{1,1,2}));
    }

    // O(n*n!) - time (initially we have N choices, then N-1, ... 1 = N*(N-1)*(N-2)*...1 = N! + n to copy, space (to keep N! solutions)
    // O(n^2) - space (depth of the recursion tree * temp array)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);

        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list)); //n
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            backtrack(result, list, nums, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }

}
