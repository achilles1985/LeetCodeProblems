package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 Note: The solution set must not contain duplicate subsets.

 Example:
 Input: nums = [1,2,3]
 Output:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]
 */
public class SubsetI_78 {

    public static void main(String[] args) {
        SubsetI_78 s = new SubsetI_78();
        System.out.println(s.subsets(new int[] {4,4,4,1,4}));
        System.out.println(s.subsets(new int[] {1,2,3}));
        System.out.println(s.subsets(new int[] {2,1,3}));
    }

    // O(2^n) - time, space
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, 0, list, res);

        return res;
    }

    private void dfs(int[] nums, int start, List<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(nums, i+1, list, result);
            list.remove(list.size()-1);
        }
    }
}
