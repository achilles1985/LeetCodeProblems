package array.TwoSum_1;

import java.util.HashMap;
import java.util.Map;

/**
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].

 */
public class Solution {

    // O(n^2)
    public int[] twoSum(int[] nums, int target) {
        for (int i = 1; i < nums.length ; i++) {
            for (int j = i-1; j < nums.length ; j++) {
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[] {j, i};
                }
            }
        }

        return new int[0];
    }

    // O{n}
    public int[] twoSumLinearTime(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int diff = target - entry.getKey();
            if (map.containsKey(diff)) {
                return new int[] {map.get(diff), entry.getValue()};
            }
        }

        return new int[0];
    }

    // O{n}
    public int[] twoSumLinearTimeOnePass(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>(numbers.length);
        for (int i = 0; i < numbers.length; i++) {
            int diff = target - numbers[i];
            if (map.containsKey(diff)) {
                return new int[] {i, map.get(diff)};
            }
            map.put(numbers[i], i);
        }
        throw new IllegalArgumentException();
    }
}
