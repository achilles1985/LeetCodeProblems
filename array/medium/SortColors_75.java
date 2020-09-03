package array.medium;

import utils.SolutionUtils;

/** M
 Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
 Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 Note: You are not suppose to use the library's sort function for this problem.

 Example:
 Input: [2,0,2,1,1,0]
 Output: [0,0,1,1,2,2]

 Follow up:
 A rather straight forward solution is a two-pass algorithm using counting sort.
 First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
 Could you come up with a one-pass algorithm using only constant space?
 */
public class SortColors_75 {

    public static void main(String[] args) {
        SortColors_75 s = new SortColors_75();
        s.sortColors(new int[]{2,0,2,1,1,0});
        s.sortColors(new int[]{2,0,2,1,1,0,1,2,0,1,1,1});

        s.sortColors2(new int[]{2,0,2,1,1,0});
        s.sortColors2(new int[]{2,0,2,1,1,0,1,2,0,1,1,1});
    }

    // O(n) - time, O(1) - space, one pass
    public void sortColors2(int[] nums) {
        int left = 0, curr = 0, right = nums.length-1;
        while (curr <= right) {
            if (nums[curr] == 2) {
                swap(nums, curr, right);
                right--;
            } else if (nums[curr] == 0) {
                swap(nums, curr, left);
                curr++;
                left++;
            } else {
                curr++;
            }
        }
        SolutionUtils.print(nums);
    }

    // O(n) - time, O(1) - space (similar to Bucket sort)
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int[] count = new int[3];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        int k = 0;
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                nums[k++] = i;
            }
        }

        SolutionUtils.print(nums);
    }
    private void swap(int[] nums, int from, int to) {
        int temp = nums[from];
        nums[from] = nums[to];
        nums[to] = temp;
    }
}
