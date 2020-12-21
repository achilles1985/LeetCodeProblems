package backtracking.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** M [marked]
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array,
 * and the length of an increasing subsequence should be at least 2.

 Example:
 Input: [4, 6, 7, 7]
 Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

 Note:
 The length of the given array will not exceed 15.
 The range of integer in the given array is [-100,100].
 The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */
/*
    1.Should be contiguous?
 */
public class IncreasingSubsequences_491 {

    public static void main(String[] args) {
        IncreasingSubsequences_491 s = new IncreasingSubsequences_491();
        System.out.println(s.findSubsequences(new int[] {4,3,2,1})); //[]
        System.out.println(s.findSubsequences(new int[] {4,6,7,7})); //[[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
        System.out.println(s.findSubsequences(new int[] {1,2,3,4})); //[[1, 2, 3], [1, 3, 4], [1, 2], [1, 2, 4], [2, 3], [2, 3, 4], [3, 4], [1, 3], [2, 4], [1, 4], [1, 2, 3, 4]]
    }

    // O(n*2^n) - time, O(n^2) - space
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        findSubsequencesHelper(res, new ArrayList<>(), nums, 0);
        return new ArrayList(res);
    }

    private void findSubsequencesHelper(Set<List<Integer>> res, List<Integer> temp, int[] nums, int start) {
        if (temp.size() > 1) {
            res.add(new ArrayList(temp)); //n
        }
        for (int i = start; i < nums.length; i++) {
            if (temp.size() > 0 && nums[i] < temp.get(temp.size() - 1)) {
                continue;
            }
            temp.add(nums[i]);
            findSubsequencesHelper(res, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    // more efficient
    public List<List<Integer>> findSubsequences2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        findSubsequencesHelper2(res, new ArrayList<>(), nums, 0);

        return new ArrayList(res);
    }

    private void findSubsequencesHelper2(List<List<Integer>> res, List<Integer> temp, int[] nums, int start) {
        if (temp.size() > 1) {
            res.add(new ArrayList(temp)); //n
        }
        Set<Integer> used = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            if (temp.size() > 0 && nums[i] < temp.get(temp.size() - 1)) {
                continue;
            }
            temp.add(nums[i]);
            used.add(nums[i]);
            findSubsequencesHelper2(res, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
