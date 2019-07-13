package array.LongestIncreasingSubsequence_300;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 Example:

 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

 Note:
 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?
 */
public class Solution {

    // O(n^2)
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int counter = 0;
            int k = i;
            List<Integer> temp = new ArrayList<>(); temp.add(nums[i]);
            for (int j = i+1; j < nums.length; j++) {
                if (nums[j] > nums[k]) {
                    counter++;
                    k = j;
                    temp.add(nums[j]);
                }
                if (max < counter) {
                    max = counter;
                }
                if (temp.size() > result.size()) {
                    result = temp;
                }
            }
        }

        return max + 1;
    }
}
