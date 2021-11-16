package array.medium;

/** M
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * Example 1:
 * Given nums = [1,1,1,2,2,3],
 * Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It doesn't matter what you leave beyond the returned length.
 *
 * Example 2:
 * Given nums = [0,0,1,1,1,1,2,3,3],
 * Your function should return length = 7, with the first seven elements of nums being modified to 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It doesn't matter what values are set beyond the returned length.
 */
/*
    Do not copy the first element, start from the second one.
 */
public class RemoveDuplicatesFromSortedArray_II_80 {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray_II_80 s = new RemoveDuplicatesFromSortedArray_II_80();
        System.out.println(s.removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3})); //[0,0,1,1,2,3,3 ,] 7
        System.out.println(s.removeDuplicates(new int[]{1,1,1,2,2,3})); //[1,1,2,2,3], 5
    }

    // O(n) - time, O(1) - space
    public int removeDuplicates(int[] nums) {
        int j = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                count++;
            } else {
                count = 1;
            }
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}
