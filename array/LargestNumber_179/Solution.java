package array.LargestNumber_179;

import java.util.Arrays;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 Example 1:
 Input: [10,2]
 Output: "210"

 Example 2:
 Input: [3,30,34,5,9]
 Output: "9534330"

 Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class Solution {

    // O(n^2)-time
    public String largestNumber(int[] nums) {
        int[] arr1 = new int[10000];
        Arrays.fill(arr1, -1);
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            int num = nums[j];
            while(num > 0) {
                arr1[i++] = num%10;
                num = num/10;
            }
        }
        int[] arr2 = Arrays.copyOf(arr1, i);
        Arrays.sort(arr2);
        StringBuilder sb = new StringBuilder();
        for (int j = arr2.length-1; j >= 0 ; j--) {
            sb.append(arr2[j]);
        }
        return sb.toString();
    }
}
