package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
        System.out.println(s.subsets(new int[]{2,1,2}));
        System.out.println(s.subsets(new int[]{2,1,2, 3}));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsets(nums, 0, list, result);

        return result;
    }

    private void subsets(int[] nums, int start, List<Integer> list, List<List<Integer>> result) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }
            list.add(nums[i]);
            subsets(nums, i+1, list, result);
            list.remove(list.size()-1);
        }
    }

}
