package dynamic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** M
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2.

 Example:
 Input: [4, 6, 7, 7]
 Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

 Note:
 The length of the given array will not exceed 15.
 The range of integer in the given array is [-100,100].
 The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
 */
public class IncreasingSubsequences_491 {

    public static void main(String[] args) {
        IncreasingSubsequences_491 s = new IncreasingSubsequences_491();
        System.out.println(s.findSubsequences(new int[] {4,6,7,7})); // [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
    }

    // O(2^n) - time
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();
        findSubsequencesHelper(res, new ArrayList<>(), nums, 0);
        return new ArrayList(res);
    }

    private void findSubsequencesHelper(Set<List<Integer>> res, List<Integer> curr, int[] nums, int pos) {
        if (curr.size() > 1) {
            res.add(new ArrayList(curr));
        }
        for (int i = pos; i < nums.length; i++) {
            if (curr.size() > 0 && curr.get(curr.size() - 1) > nums[i]) {
                continue;
            }
            curr.add(nums[i]);
            findSubsequencesHelper(res, curr, nums, i + 1);
            curr.remove(curr.size() - 1);
        }
    }
}
