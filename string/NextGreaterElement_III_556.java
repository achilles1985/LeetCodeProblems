package string;

import java.util.Arrays;

/** M
 Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same
 digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

 Example 1:
 Input: 12
 Output: 21

 Example 2:
 Input: 21
 Output: -1
 */
public class NextGreaterElement_III_556 {

    public static void main(String[] args) {
        NextGreaterElement_III_556 s = new NextGreaterElement_III_556();

        System.out.println(s.nextGreaterElement(1999999999)); // -1
        System.out.println(s.nextGreaterElement(654321)); // -1
        System.out.println(s.nextGreaterElement(123654)); // 124356
        System.out.println(s.nextGreaterElement(123456)); // 123465

        System.out.println(s.nextGreaterElement(3254)); // 3425
        System.out.println(s.nextGreaterElement(2543)); // 3245
        System.out.println(s.nextGreaterElement(21)); // -1
        System.out.println(s.nextGreaterElement(12)); // 21
        System.out.println(s.nextGreaterElement(1_999_999_999)); // -1
        System.out.println(s.nextGreaterElement(321465)); // 321_546
        System.out.println(s.nextGreaterElement(32146572)); // 321_467_25
        System.out.println(s.nextGreaterElement(321_654_321)); // 322_113_456
        System.out.println(s.nextGreaterElement(123_654)); // 124_356
    }

    // O(n) - time, O(1) - space
    public int nextGreaterElement(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        // 1) Start from the right most digit and find the first digit that is smaller than the digit next to it.
        if (nums.length == 1) {
            return -1;
        }
        int i;
        for (i = nums.length-1; i >= 0; i--) {
            if (i-1 >= 0 && nums[i-1] < nums[i]) {
                break;
            }
        }

        // If no such digit is found, its the edge case 1.
        if (i <= 0) {
            return -1;
        }

        // 2) Find the smallest digit on right side of (i-1)'th digit that is greater than number[i-1]
        int from = i-1;
        int min = i;
        for (int j = min; j < nums.length; j++) {
            if (nums[j] > nums[from] && nums[j] < nums[min]) {
                min = j;
            }
        }
        // 3) Swap the above found smallest digit with number[i-1]
        swap(nums, from, min);
        // 4) Sort the digits after (i-1) in ascending order
        Arrays.sort(nums, from+1, nums.length);
        long res = Long.parseLong(new String(nums));
        return res <= Integer.MAX_VALUE ? Integer.parseInt(new String(nums)) : -1;
    }

    private void swap(char[] nums, int from, int to) {
        char temp = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }
}
