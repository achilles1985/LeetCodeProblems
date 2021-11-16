package array.medium;

import java.util.Arrays;

/** M
 Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 Return the sum of the three integers. You may assume that each input would have exactly one solution.
 Example:
 Given array nums = [-1, 2, 1, -4], and target = 1.
 The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest_16 {

    public static void main(String[] args) {
        ThreeSumClosest_16 s = new ThreeSumClosest_16();
        System.out.println(s.threeSumClosest(new int[]{-1,2,1,-4}, 1)); // 2
        System.out.println(s.threeSumClosest(new int[]{-3,-2,-5,3,-4}, -1)); // 2
    }

    // O(n^3) - time, O(1) - space
    public int threeSumClosestBF(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                for (int m = j+1; m < nums.length; m++) {
                    int sum = nums[i] + nums[j] + nums[m];
                    if (Math.abs(sum - target) < min) {
                        min = Math.abs(sum - target);
                        res = sum;
                    }
                }
            }
        }

        return res;
    }

    // O(n^2) - time, O(1) - space
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            return Integer.MIN_VALUE;
        }
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int result = nums[0];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int left = i + 1, right = nums.length-1;
            while (left < right) {
                int sum = nums[left] + nums[right] + num;
                if (sum < target) {
                    left++;
                } else {
                    right--;
                }
                if (Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    result = sum;
                }
            }
        }
        return result;
    }
}
