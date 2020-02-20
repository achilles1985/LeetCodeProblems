package binarySearch;

/** M
 Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s.
 If there isn't one, return 0 instead.

 Example:
 Input: s = 7, nums = [2,3,1,2,4,3]
 Output: 2
 Explanation: the subarray [4,3] has the minimal length under the problem constraint.

 Follow up:
 If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */
public class MinimumSizeSubarraySum_209 {

    public static void main(String[] args) {
        MinimumSizeSubarraySum_209 s = new MinimumSizeSubarraySum_209();
        System.out.println(s.minSubArrayLen2(7, new int[]{2,3,1,2,4,3})); //2
        System.out.println(s.minSubArrayLen2(4, new int[]{1,4,4})); //1
        System.out.println(s.minSubArrayLen2(11, new int[]{1,2,3,4,5})); //3
    }

    // O(n^3) - time, O(1) - space
    public int minSubArrayLen(int s, int[] nums) {
        int min = nums.length+1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= s) {
                    min = Math.min(min, (j - i)+1);
                }
            }
        }

        return min == nums.length+1 ? 0 : min;
    }

    // O(n^2) - time, O(1) - space
    public int minSubArrayLen1(int s, int[] nums) {
        int min = nums.length+1;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    min = Math.min(min, j+1-i);
                }
            }
        }

        return min == nums.length+1 ? 0 : min;
    }

    // O(n*log(n)) - time, O(n) - space
    public int minSubArrayLen2(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] sums = new int[nums.length];
        sums[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        if (sums[nums.length - 1] < s) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int l = i;
            int r = nums.length - 1;
            while (l <= r) {
                int mid = l + (r - l)/2;
                if (sums[mid] - sums[i] + nums[i] == s) {
                    l = mid;
                    break;
                } else if (sums[mid] - sums[i] + nums[i] < s) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
            if (l >= sums.length) {
                break;
            }
            ans = Math.min(ans, l - i + 1);
        }

        return (ans == Integer.MAX_VALUE ? 0 : ans);
    }

    // O(n) - time, O(1) - space
    public int minSubArrayLen3(int s, int[] nums) {
        int sum = 0;
        int j = 0; int min = nums.length+1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum >= s) {
                min = Math.min(min, i+1-j);
                sum -= nums[j];
                j++;
            }
        }

        return min == nums.length+1 ? 0 : min;
    }
}
