package array.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * M
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note:
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 */
public class FourSum_18 {

    public static void main(String[] args) {
        FourSum_18 s = new FourSum_18();
        System.out.println(s.fourSumBF(new int[] {1, 0, -1, 0, -2, 2}, 0)); //[[-1,0,0,1], [-2,-1,1,2], [-2,0,0,2]]
    }

    // O(n^4) - time, O(1) - space
    public List<List<Integer>> fourSumBF(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                            List<Integer> entry = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(entry);
                            result.add(entry);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    // O(n^3) - time, O(n) - space
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = target - (nums[i] + nums[j]);
                int lo = j + 1;
                int hi = nums.length - 1;
                while (lo < hi) {
                    if (nums[lo] + nums[hi] < sum) {
                        lo++;
                    } else if (nums[lo] + nums[hi] > sum) {
                        hi--;
                    } else {
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]);
                        Collections.sort(list);
                        set.add(list);
                        lo++;
                        hi--;
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    // O(n^3) - time, O(n) - space
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                int sum1 = nums[i] + nums[j];
                int left = j+1, right = nums.length-1;
                while (left < right) {
                    int sum2 = sum1 + nums[left] + nums[right];
                    if (sum2 == target) {
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
                        Collections.sort(temp);
                        set.add(temp);
                        left++;
                        right--;
                    } else if (sum2 < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }
}
