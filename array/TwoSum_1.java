package array;

import java.util.HashMap;
import java.util.Map;

import utils.SolutionUtils;

/** E
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,
 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */
public class TwoSum_1 {

    public static void main(String[] args) {
        TwoSum_1 s = new TwoSum_1();
        SolutionUtils.print(s.twoSum(new int[]{2,7,11,15}, 9));
    }

    // O(n) - time, O(n) - space
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{i, map.get(diff)};
            }
            map.put(nums[i], i);
        }

        return new int[]{};
    }
}
