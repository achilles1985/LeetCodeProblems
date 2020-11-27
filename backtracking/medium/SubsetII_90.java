package backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** M
  Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
  Note: The solution set must not contain duplicate subsets.
  Example:
  Input: [1,2,2]
  Output:
  [
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
  ]
 */
public class SubsetII_90 {
    public static void main(String[] args) {
        SubsetII_90 s = new SubsetII_90();
        System.out.println(s.subsetsWithDup2(new int[]{1,2,3,4,5}));
        System.out.println(s.subsetsWithDup(new int[]{2,1,2}));
        System.out.println(s.subsetsWithDup(new int[]{2,1,2,3}));
    }

    // O(N*logN + 2^N) - time, O(2^N) - space
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDup(nums, 0, list, result);

        return result;
    }

    // O(2^N*N*logN) - time, O(2^N) - space
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        dfs(nums, new ArrayList<>(), result, 0);

        return new ArrayList<>(result);
    }
    private void dfs(int[] nums, List<Integer> temp, Set<List<Integer>> result, int start) {
        List<Integer> check = new ArrayList<>(temp);
        Collections.sort(check);
        if (!result.contains(check)) {
            result.add(new ArrayList<>(check));
        }
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, temp, result, i+1);
            temp.remove(temp.size() - 1);
        }
    }

    private void subsetsWithDup(int[] nums, int start, List<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
            list.add(nums[i]);
            subsetsWithDup(nums, i+1, list, result);
            list.remove(list.size()-1);
        }
    }

}
