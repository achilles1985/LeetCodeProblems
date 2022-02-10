package array.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**H
 * Given an unsorted integer array nums, return the smallest missing positive integer.
 * You must implement an algorithm that runs in O(n) time and uses constant extra space.
 *
 * Example 1:
 * Input: nums = [1,2,0]
 * Output: 3
 *
 * Example 2:
 * Input: nums = [3,4,-1,1]
 * Output: 2
 *
 * Example 3:
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 *
 * Constraints:
 *     1 <= nums.length <= 5 * 105
 *     -231 <= nums[i] <= 231 - 1
 */
// Any duplicates, negatives?
public class FirstMissingPositive_41 {

    public static void main(String[] args) {
        FirstMissingPositive_41 s = new FirstMissingPositive_41();
        System.out.println(s.firstMissingPositive(new int[]{0,2,3,1})); //4
        System.out.println(s.firstMissingPositive(new int[]{7,8,9,11,12})); //1
        System.out.println(s.firstMissingPositive(new int[]{3,4,-1,1})); //2
    }

    //O(n*log(n)) - time, O(n) - space
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int num: nums) {
            set.add(num);
            max = Math.max(max, num);
        }
        for (int i = 1; i <= Integer.MAX_VALUE; i++) {
            if (!set.contains(i)) {
                return i;
            }
        }
        return 0;
    }

    // O(n) - time, O(1) - space
    public int firstMissingPositive2(int[] nums) {
        int n = nums.length;
        // Base case.
        int contains = 0;
        for (int i = 0; i < n; i++)
            if (nums[i] == 1) {
                contains++;
                break;
            }
        if (contains == 0) {
            return 1;
        }
        // Replace negative numbers, zeros,
        // and numbers larger than n by 1s.
        // After this convertion nums will contain
        // only positive numbers.
        for (int i = 0; i < n; i++)
            if ((nums[i] <= 0) || (nums[i] > n)) {
                nums[i] = 1;
            }
        // Use index as a hash key and number sign as a presence detector.
        // For example, if nums[1] is negative that means that number `1`
        // is present in the array.
        // If nums[2] is positive - number 2 is missing.
        for (int i = 0; i < n; i++) {
            int a = Math.abs(nums[i]);
            // If you meet number a in the array - change the sign of a-th element.
            // Be careful with duplicates : do it only once.
            if (a == n) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                nums[a] = -Math.abs(nums[a]);
            }
        }
        // Now the index of the first positive number
        // is equal to first missing positive.
        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        if (nums[0] > 0) {
            return n;
        }

        return n + 1;
    }
}
