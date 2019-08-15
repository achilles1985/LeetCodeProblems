package binarySearch;

/** H
 Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays. Write an algorithm to minimize the largest sum among these m subarrays.

 Note:
 If n is the length of array, assume the following constraints are satisfied:
 1 ≤ n ≤ 1000
 1 ≤ m ≤ min(50, n)

 Examples:
 Input:
 nums = [7,2,5,10,8]
 m = 2
 Output:
 18

 Explanation:
 There are four ways to split nums into two subarrays.
 The best way is to split it into [7,2,5] and [10,8],
 where the largest sum among the two subarrays is only 18.
 */
public class SplitArrayLargestSum_410 {

    public static void main(String[] args) {
        SplitArrayLargestSum_410 s = new SplitArrayLargestSum_410();
        System.out.println(s.splitArray(new int[]{7,2,5,10,8}, 2)); // 18
        System.out.println(s.splitArray(new int[]{1,2147483647}, 2)); // 2147483647
    }

    // O(n*log(sum(n)-max(n))) - time, O(1) - space
    public int splitArray(int[] nums, int m) {
        int left = 0;
        int right = 0;
        for (int num: nums) {
            left = Math.max(left, num);
            right += num;
        }

        int res = left;
        while (left <= right) {
            int mid = left + (right-left)/2;
            if (isPossible(nums, m, mid)) {
                res = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        return res;
    }

    private boolean isPossible(int[] nums, int m, int mid) {
        int curSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (curSum + nums[i] <= mid) {
                curSum += nums[i];
            } else {
                m--;
                curSum = nums[i];
            }
            if (m <= 0) {
                return false;
            }
        }
        return true;
    }
}
