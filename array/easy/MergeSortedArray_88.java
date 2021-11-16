package array.easy;

import java.util.Arrays;

/** E
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
        s.merge(new int[]{0}, 0, new int[]{1}, 1);
        s.merge(new int[]{1,2,3,0,0,0}, 3, new int[]{2,5,6}, 3);
    }

    // O(n+m)*log(n+m) - time
    public void mergeBF(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    // O(n+m) - time, O(n) - space. Space can be improved to O(1) if start from the end.
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1Copy = Arrays.copyOf(nums1, m);
        int write = 0;
        int i = 0, j = 0;
        while(i < m && j < n) {
            if (nums1Copy[i] < nums2[j]) {
                nums1[write++] = nums1Copy[i++];
            } else {
                nums1[write++] = nums2[j++];
            }
        }
        while(i < m) {
            nums1[write++] = nums1Copy[i++];
        }
        while(j < n) {
            nums1[write++] = nums2[j++];
        }
    }

    // O(n+m) - time, O(1) - space
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int write = nums1.length - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[write--] = nums1[i--];
            } else {
                nums1[write--] = nums2[j--];
            }
        }
        while (i >= 0) {
            nums1[write--] = nums1[i--];
        }
        while (j >= 0) {
            nums1[write--] = nums2[j--];
        }
    }
}
