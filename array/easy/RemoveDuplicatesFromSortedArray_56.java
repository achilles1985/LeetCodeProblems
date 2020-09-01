package array.easy;

/** E
 Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.
 Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

 Example:
 Given nums = [1,1,2],
 Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 It doesn't matter what you leave beyond the new length.
 */

public class RemoveDuplicatesFromSortedArray_56 {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedArray_56 s = new RemoveDuplicatesFromSortedArray_56();

        System.out.println(s.removeDuplicates(new int[]{1,2,3,4,4,4,5,5,6})); //6

        System.out.println(s.removeDuplicates(new int[]{1,2,3,4,4,5,5,5,6})); // 8
        System.out.println(s.removeDuplicates(new int[]{1,1,1,2,2,3})); // 5
        System.out.println(s.removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3})); // 7
    }

    // O(n) - time, O(1) - space
    public int removeDuplicates(int[] nums) {
        int j = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[j]) {
                nums[++j] = nums[i];
            }
        }

        return j + 1;
    }

}
