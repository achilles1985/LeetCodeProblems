package binarySearch.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * M [marked*]
 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
 * <p>
 * Example 1:
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 * <p>
 * Note:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
/*
    Questions:
    1. A.length == B.length?
 */
public class MaximumLengthOfRepeatedSubarray_718 {

    public static void main(String[] args) {
        MaximumLengthOfRepeatedSubarray_718 s = new MaximumLengthOfRepeatedSubarray_718();
        System.out.println(s.findLength(new int[] {4,5,1,2,3,6}, new int[] {3,2,1,2,3,7})); //3
        System.out.println(s.findLength(new int[] {0,0,0,0,0}, new int[] {0,0,0,0,0})); //5

        System.out.println(s.findLengthDP(new int[] {4,5,1,2,3,6}, new int[] {3,2,1,2,3,7})); //3
        System.out.println(s.findLengthRK(new int[] {1,2,3,2,1}, new int[] {3,2,1,2,5,6,1,2,3,7})); //3
        System.out.println(s.findLengthRK(new int[] {0,0,0,0,1}, new int[] {1,0,0,0,0})); //4
        System.out.println(s.findLengthRK(new int[] {0,0,0,0,0}, new int[] {0,0,0,0,0})); //5
        System.out.println(s.findLengthRK(new int[] {0,0,0,0,0, 0, 1, 0, 0, 0}, new int[] {0, 0, 0, 0, 0, 0, 0, 1, 0, 0})); //9

        System.out.println(s.findLengthRK(new int[] {0, 1, 1, 1, 1}, new int[] {1, 0, 1, 0, 1})); //2
        System.out.println(s.findLengthRK(new int[] {0, 1, 1, 1, 1}, new int[] {1, 0, 1, 0, 1})); //2
        System.out.println(s.findLengthRK(new int[] {1, 2, 3, 2, 1}, new int[] {3, 2, 1, 4, 7})); //3
        System.out.println(s.findLengthRK(new int[] {1, 0, 0, 0, 1}, new int[] {1, 0, 0, 1, 1})); //3
    }

    // O(A*B*min(A,B)) - time, O(1) - space
    public int findLengthBF(int[] A, int[] B) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int k = 0;
                while (i + k < A.length && j + k < B.length && A[i + k] == B[j + k]) {
                    k++;
                }
                max = Math.max(max, k);
            }
        }
        return max;
    }

    // O((A+B)*min(A,B)*log(min(A,B))), O(A) - space
    public int findLength(int[] A, int[] B) {
        int left = 0, right = Math.min(A.length, B.length)+1; // to be possible to construct hash of the length == min(A,B)
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (search(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    // O((A+B)*log(min(A,B))), O(A) - space for set of hashes
    public int findLengthRK(int[] A, int[] B) {
        int left = 0, right = Math.min(A.length, B.length) + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (searchRK(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left - 1;
    }

    // O(A(len)*B(len)) - time, space
    public int findLengthDP(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    private boolean searchRK(int[] A, int[] B, int length) {
        // TODO (fix rehashing algorithm)
        int hashA = 0;
        for (int i = 0; i < length; i++) {
            hashA = hashA  +  A[i]*(int) Math.pow(9, i);
        }

        Set<Integer> hashes = new HashSet<>();
        hashes.add(hashA);
        for (int i = 1; i <= A.length - length; i++) {
            hashA = (hashA - A[i - 1] + A[i + length - 1] * (int) Math.pow(9, i + length - 1))/9;
            hashes.add(hashA);
        }
        int hashB = 0;
        for (int i = 0; i <= B.length - length; i++) {
            if (i == 0) {
                for (int j = 0; j < length; j++) {
                    hashB = hashB  +  B[j]*(int) Math.pow(9, j);
                }
                continue;
            }
            hashB = (hashB - B[i - 1] + B[i + length - 1] * (int) Math.pow(9, i + length - 1))/9;
            if (hashes.contains(hashB)) {
                return true;
            }
        }
        return false;
    }

    private boolean search(int[] A, int[] B, int length) {
        Set<String> hashes = new HashSet<>();
        for (int i = 0; i <= A.length - length; i++) {
            String hash = Arrays.toString(Arrays.copyOfRange(A, i, i + length));
            hashes.add(hash);
        }
        for (int i = 0; i <= B.length - length; i++) {
            String hash = Arrays.toString(Arrays.copyOfRange(B, i, i + length));
            if (hashes.contains(hash)) {
                return true;
            }
        }
        return false;
    }
}
