package binarySearch.medium;

/** M
 * A peak element is an element that is greater than its neighbors.
 Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 You may imagine that nums[-1] = nums[n] = -∞.

 Example 1:
 Input: nums = [1,2,3,1]
 Output: 2
 Explanation: 3 is a peak element and your function should return the index number 2.

 Example 2:
 Input: nums = [1,2,1,3,5,6,4]
 Output: 1 or 5
 Explanation: Your function can return either index number 1 where the peak element is 2,
 or index number 5 where the peak element is 6.

 Note:
 Your solution should be in logarithmic complexity.
 */
public class FindPeakElement_162 {

    public static void main(String[] args) {
        FindPeakElement_162 s = new FindPeakElement_162();
        System.out.println(s.findPeakElement(new int[]{1,2,3,1})); // 2

        System.out.println(s.findPeakElementBF(new int[]{2,1})); //0
        System.out.println(s.findPeakElementBF(new int[]{1,2,3,4,5})); //4
        System.out.println(s.findPeakElementBF(new int[]{5,4,3,2,1})); //0
        System.out.println(s.findPeakElementBF(new int[]{1})); //0
        System.out.println(s.findPeakElementBF(new int[]{3,2,1})); //0
        System.out.println(s.findPeakElementBF(new int[]{1,2,3,1})); // 2
        System.out.println(s.findPeakElementBF(new int[]{1,2,1,3,5,6,4})); //1 or 5
    }

    // O(n) - time, O(1) - space
    public int findPeakElementBF(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                return i-1;
            }
        }
        return nums.length - 1;
    }

    // 1,2,3; 3,2,1; 1,2,4,3,2 - if n[m] < n[m+1], it means the peak is definitely to the right, otherwise n[m] might be the peak.
    // O(log(n)) - time, O(1) - space
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = (left + right)/2;
            if (nums[mid] < nums[mid+1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

    // O(log(n)) - time, O(1) - space
    public int findPeakElement2(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while (left < right) {
            int mid = left + (right - left)/2;
            if (nums[mid] > nums[mid+1]) {
                right = mid;
            } else {
                left = mid+1;
            }
        }

        return left;
    }

}
