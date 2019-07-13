package array.MaxContiguousSubarray_53;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * Example:
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * <p>
 * Follow up:
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class Solution {

    // O(n^2)
    public int maxSubArray(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum > max) {
                    max = sum;
                }
            }
        }

        return max;
    }

    // O(n)
    public int maxSubArray2(int[] nums) {
        int max = 0;
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i] + sum) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    public int maxSubArray3(int[] nums) {
        return maxSubArrDivideAbdConquer(nums, 0, nums.length - 1);
    }

    private int maxSubArrDivideAbdConquer(int[] nums, int l, int r) {
        if (l == r) {
            return nums[l];
        }
        int mid = l + (r - l) / 2;

        // largest subarray on the one single side
        int maxSubLeft = maxSubArrDivideAbdConquer(nums, l, mid);
        int maxSubRight = maxSubArrDivideAbdConquer(nums, mid + 1, r);
        int maxSingleSide = Math.max(maxSubLeft, maxSubRight);

        //Calculate the subarray across two sides;
        //  - calculate the largest sum of the subarray starting from the mid-point;
        int sum = 0;
        int maxRightFromMid = Integer.MIN_VALUE;
        for (int i = mid + 1; i <= r; ++i) {
            sum += nums[i];
            maxRightFromMid = Math.max(maxRightFromMid, sum);
        }
        //  - calculate the largest sum of the subarray ending at the mid-point;
        sum = 0;
        int maxLeftEndMid = Integer.MIN_VALUE;
        for (int i = mid; i >= l; --i) {
            sum += nums[i];
            maxLeftEndMid = Math.max(maxLeftEndMid, sum);
        }

        return Math.max(maxSingleSide, maxRightFromMid + maxLeftEndMid);
    }
}
