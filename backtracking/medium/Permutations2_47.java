package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** M [marked]
 * https://leetcode.com/problems/permutations-ii/#/description
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
/*
    1. distinct, sorted?
 */
//https://discuss.leetcode.com/topic/31445/really-easy-java-solution-much-easier-than-the-solutions-with-very-high-vote
// https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
public class Permutations2_47 {

    public static void main(String[] args) {
        Permutations2_47 s = new Permutations2_47();
        System.out.println(s.permuteUnique(new int[]{1,1,2})); //[[1, 1, 2], [1, 2, 1], [2, 1, 1]]
        System.out.println(s.permuteUnique(new int[]{1,2,3})); //[[1, 1, 2], [1, 2, 1], [2, 1, 1]]
    }

    // O(n*log(n) + n*n!) - time, O(n^2) - space (if result is not counted, if it does - n*n!)(depth of the recursion tree * temp array)
    // if you do not used Set, you need sorting because of duplicates
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, current, nums, new boolean[nums.length]);

        return result;
    }

    // in case of duplicates (a[i] == a[i-1]), we can use a[i] only if a[i-1] already used.
    // in other case duplicate sets be generated. like [1,2,1] [1,2,1].
    private void backtrack(List<List<Integer>> result, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            System.out.println(list);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            list.add(nums[i]);
            backtrack(result, list, nums, used);
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }

    // Correct but it's not a backtracking, it still travers thrown all recursion tree because of that
    // it's less efficient as the first approach which is totally backtracking we do not traverse subtrees that fails condition.
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();
        backtrack2(set, current, nums, new boolean[nums.length]);
        result.addAll(set);

        return result;
    }

    private void backtrack2(Set<List<Integer>> set, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            set.add(new ArrayList<>(list));
            System.out.println(list);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            list.add(nums[i]);
            used[i] = true;
            backtrack2(set, list, nums, used);
            list.remove(list.size()-1);
            used[i] = false;
        }
    }
}
