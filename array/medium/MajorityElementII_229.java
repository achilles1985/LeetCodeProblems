package array.medium;

import java.util.ArrayList;
import java.util.List;

/**M
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * Example 1:
 * Input: [3,2,3]
 * Output: [3]
 *
 * Example 2:
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElementII_229 {

    public static void main(String[] args) {
        MajorityElementII_229 s = new MajorityElementII_229();
        System.out.println(s.majorityElement(new int[]{1,1,1,3,3,2,2,2})); //[1,2]
        System.out.println(s.majorityElement(new int[]{3,2,3})); //[3]
    }

    // O(n) - time, O(1) - space
    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int num1 = nums[0], num2 = nums[0];
        int count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                count1++;
            } else if (nums[i] == num2) {
                count2++;
            } else if (count1 == 0) {
                num1 = nums[i];
                count1++;
            } else if (count2 == 0) {
                num2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == num1) {
                count1++;
            } else if (nums[i] == num2) {
                count2++;
            }
        }
        List<Integer> result = new ArrayList<>();
        if (count1 > nums.length/3) {
            result.add(num1);
        }
        if (count2 > nums.length/3) {
            result.add(num2);
        }
        return result;
    }
}
