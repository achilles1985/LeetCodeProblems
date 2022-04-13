package binarySearch.medium;

/** M
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Constraints:
 *     1 <= nums.length <= 105
 *     0 <= nums[i] <= 105
 */
public class SingleElementInSortedArray_540 {

    public static void main(String[] args) {
        SingleElementInSortedArray_540 s = new SingleElementInSortedArray_540();
        System.out.println(s.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8})); //2
        System.out.println(s.singleNonDuplicate(new int[]{3,3,7,7,10,11,11})); //10
    }

    //O(log(n)) - time, O(1) - space
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            boolean halvesAreEven = (right - mid) % 2 == 0;
            if (nums[mid + 1] == nums[mid]) {
                if (halvesAreEven) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid - 1] == nums[mid]) {
                if (halvesAreEven) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}
