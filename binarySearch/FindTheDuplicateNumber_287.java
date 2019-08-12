package binarySearch;

// https://leetcode.com/problems/find-the-duplicate-number/

import java.util.Arrays;

/** M
 Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist.
 Assume that there is only one duplicate number, find the duplicate one.

 Example 1:
 Input: [1,3,4,2,2]
 Output: 2

 Example 2:
 Input: [3,1,3,4,2]
 Output: 3

 Note:
 You must not modify the array (assume the array is read only).
 You must use only constant, O(1) extra space.
 Your runtime complexity should be less than O(n2).
 There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindTheDuplicateNumber_287 {

    public static void main(String[] args) {
        FindTheDuplicateNumber_287 s = new FindTheDuplicateNumber_287();
        System.out.println(s.findDuplicate3(new int[] {1,3,4,2,2})); //2
        //System.out.println(s.findDuplicate2(new int[] {3,1,3,4,2})); //3
        System.out.println(s.findDuplicate2(new int[] {2,5,9,6,9,3,8,9,7,1})); //9
    }

    // O(n*log(n)) - time, O(1) - space
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }

    // O(n) - time, O(1) - space
    public int findDuplicate2(int[] nums) {
        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        int p1 = nums[0];
        int p2 = slow;
        while (p1 != p2) {
            p1 = nums[p1];
            p2 = nums[p2];
        }

        return p1;
    }

    public int findDuplicate3(int[] nums) {
        for (int i=0;i<=nums.length-1;i++){
            int val=Math.abs(nums[i]);
            if (nums[val]>0){
                nums[val]=-nums[val];
            }
            else {
                return Math.abs(nums[i]);
            }
        }
        return -1;
    }
}
