package array.medium;

import java.util.HashMap;
import java.util.Map;

/**M
 * Given a binary array nums, return the maximum length of a contiguous subaray with an equal number of 0 and 1.
 *
 * Example 1:
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with an equal number of 0 and 1.
 *
 * Example 2:
 * Input: nums = [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 *
 * Constraints:
 *     1 <= nums.length <= 105
 *     nums[i] is either 0 or 1.
 */
/*
   if '0' count = -> -1, '1' -> +1
 */
public class ContiguousArray_525 {

    public static void main(String[] args) {
        ContiguousArray_525 s = new ContiguousArray_525();
        System.out.println(s.findMaxLength(new int[]{0,1,0,0,1,1,0,1,0,1})); //10
    }

    // O(n^2) - time, O(1) - space
    public int findMaxLengthBF(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = i; j < nums.length; j++) {
                count += nums[j] == 0 ? -1 : 1;
                if (count == 0) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    //O(n) - time, space
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int count = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return max;
    }
}
