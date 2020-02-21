package array;

import java.util.Arrays;

/**E
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *     The number of elements initialized in nums1 and nums2 are m and n respectively.
 *     You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional
 *     elements from nums2.
 *
 * Example:
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray_88 {

    public static void main(String[] args) {
        MergeSortedArray_88 s = new MergeSortedArray_88();
        s.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }

    // O(n+m) - time, O(n) - space. Space can be improved to O(1) if start from the end.
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] num1Copy = Arrays.copyOf(nums1, m);
        int p1 = 0;
        int p2 = 0;
        int p = 0;
        while (p1 < m && p2 < n) {
            nums1[p++] = num1Copy[p1] < nums2[p2] ? num1Copy[p1++] : nums2[p2++];
        }
        if (p1 < m) {
            System.arraycopy(num1Copy, p1, nums1, p, m-p1);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p, n-p2);
        }
    }
}
