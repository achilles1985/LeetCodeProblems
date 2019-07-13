package algorithmstechniqes;

import bitManipulation.MajorityElement_169.Solution;

/**
 *  Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 You may assume that the array is non-empty and the majority element always exist in the array.
 Example 1:
 Input: [3,2,3]
 Output: 3

 Example 2:
 Input: [2,2,1,1,1,2,2]
 Output: 2
 */
public class BoyerMooreVotingAlgorithm {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.majorityElement(new int[] {3,2,3})); // 3
        System.out.println(s.majorityElement(new int[] {2,2,1,1,1,2,2})); // 2
    }

    // O(n) - time, O(1) - space
    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == ans) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                ans = nums[i];
            }
        }

        return ans;
    }
}
