package binarySearch;

// https://leetcode.com/problems/intersection-of-two-arrays/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import utils.SolutionUtils;

/** E
 Given two arrays, write a function to compute their intersection.

 Example 1:
 Input: nums1 = [1,2,2,1], nums2 = [2,2]
 Output: [2]

 Example 2:
 Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 Output: [9,4]

 Note:
 Each element in the result must be unique.
 The result can be in any order.
 */
public class IntersectionOfTwoArrays_349 {

    public static void main(String[] args) {
        IntersectionOfTwoArrays_349 s = new IntersectionOfTwoArrays_349();
        SolutionUtils.print(s.intersection(new int[] {1,2,2,1}, new int[] {2,2})); // [2]
        SolutionUtils.print(s.intersection(new int[] {4,9,5}, new int[] {9,4,9,8,4})); // [9,4]
    }

    // O(m+n) - time, O(m+n) - space
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0) {
            return new int[0];
        }
        if (nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        for (Integer i: nums1) {
            set1.add(i);
        }
        Set<Integer> set2 = new HashSet<>();
        for (Integer i: nums2) {
            set2.add(i);
        }

        if (set1.size() < set2.size()) {
            return findIntersection(set1, set2);
        }
        return findIntersection(set1, set2);
    }

    private int[] findIntersection(Set<Integer> set1, Set<Integer> set2) {
        int[] res = new int[set1.size()];
        int k = 0;
        for (Integer i: set1) {
            if (set2.contains(i)) {
                res[k++] = i;
            }
        }

        return Arrays.copyOf(res, k);
    }
}
