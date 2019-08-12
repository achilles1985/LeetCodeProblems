package binarySearch;

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
        System.out.println(s.findPeakElement(new int[]{3,2,1})); //0
        System.out.println(s.findPeakElement(new int[]{1,2,3,1})); // 2
        System.out.println(s.findPeakElement(new int[]{1,2,1,3,5,6,4})); //1 or 5
        System.out.println(s.findPeakElement(new int[]{2,1})); //0
        System.out.println(s.findPeakElement(new int[]{1})); //0
    }

    // O(log(n)) - time, O(1) - space
    public int findPeakElement(int[] nums) {
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
