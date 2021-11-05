package array.easy;

// https://leetcode.com/problems/intersection-of-two-arrays/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import utils.SolutionUtils;

/** E [marked]
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
        SolutionUtils.print(s.intersection(new int[] {4,9,5}, new int[] {9,4,9,8,4})); // [9,4]

        SolutionUtils.print(s.intersection2(new int[] {4,3,9,3,1,9,7,6,9,7}, new int[] {5,0,8})); // []
        SolutionUtils.print(s.intersection2(new int[] {1,2}, new int[] {1,1})); // [1]
        SolutionUtils.print(s.intersection2(new int[] {1}, new int[] {1})); // [1]
        SolutionUtils.print(s.intersection2(new int[] {1,2,2,1}, new int[] {2,2})); // [2]
        SolutionUtils.print(s.intersection2(new int[] {4,9,5}, new int[] {9,4,9,8,4})); // [9,4]
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

    // O(n*log(n)) - time, O(1) - space
    public int[] intersection2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int first = 0, second = 0;
        int size = Math.min(nums1.length, nums2.length);
        int[] temp = new int[size];
        int k = 0;
        while (first < nums1.length && second < nums2.length) {
            if (nums1[first] < nums2[second]) {
                first++;
            } else if (nums2[second] < nums1[first]) {
                second++;
            } else {
                temp[k++] = nums1[first];
                first++;
                second++;
            }
        }
        if (k == 0) {
            return new int[]{};
        }
        int i = 0, j = 1;
        for (; j < k; j++) {
            if (temp[i] != temp[j]) {
                temp[++i] = temp[j];
            }
        }

        return Arrays.copyOf(temp, i+1);
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
