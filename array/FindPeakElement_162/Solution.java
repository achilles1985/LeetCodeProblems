package array.FindPeakElement_162;

/**
 * /**
 * 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 * <p>
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * <p>
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 */

public class Solution {

    // O(n)
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int k = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[k]) {
                k = i;
            }
            if (k < i) {
                return nums[k];
            }

        }
        return nums[k];
    }

    // O(log(n)) https://leetcode.com/problems/find-peak-element/solution/
    public int findPeakElement2(int[] nums) {
        return findPeakElement2(nums, 0, nums.length - 1);

    }

    private int findPeakElement2(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int middle = (left + right) / 2;
        if (nums[middle] > nums[middle+1]) {
            return findPeakElement2(nums, left, middle);
        }
        return findPeakElement2(nums, middle+1, right);
    }

    // iterative approach
    public int findPeakElement3(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
