package binarySearch;

/** M
 Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

 Example 1:
 Input:
 A: [1,2,3,2,1]
 B: [3,2,1,4,7]
 Output: 3
 Explanation:
 The repeated subarray with maximum length is [3, 2, 1].

 Note:
 1 <= len(A), len(B) <= 1000
 0 <= A[i], B[i] < 100
 */
public class MaximumLengthOfRepeatedSubarray_718 {

    public static void main(String[] args) {
        MaximumLengthOfRepeatedSubarray_718 s = new MaximumLengthOfRepeatedSubarray_718();
        System.out.println(s.findLength(new int[]{1,0,0,0,1}, new int[]{1,0,0,1,1})); //3
        System.out.println(s.findLength(new int[]{0,0,0,0,1}, new int[]{1,0,0,0,0})); //4
        System.out.println(s.findLength(new int[]{0,1,1,1,1}, new int[]{1,0,1,0,1})); //2
        System.out.println(s.findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7})); //3
    }

    // O(A(len)*B(len)) - time, space
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m+1][n+1];
        int max = 0;
        for(int i = 1; i<= m; i++){
            for(int j = 1; j<=n; j++){
                if(A[i-1] == B[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}
