package assessments.microsoft;

import java.util.Arrays;

/**
 * In one step, any element of a given array can be either increased or decreased by 1.
 * Returns the minimum number of steps required to make all elements equal.
 *
 * Example 1
 * Input [3,2,1,1,2,3,1]
 * Output: 5
 * All ones can be increased by 1 and all 3s can be decreased by 1.
 *
 * Example 2
 * Input: [4,1,4,1]
 * Output: 6
 * All 4s can be decreased by 1 three times
 *
 * Example 3
 * Input: [3,3,3]
 * Output: 0
 * All elements are already the same
 *
 * Constraints:
 * N [1...100_000]
 * each element in A [1...4]
 */
public class MinimumMovesToEqualArrayElements_II_462 {

    public static void main(String[] args) {
        MinimumMovesToEqualArrayElements_II_462 s = new MinimumMovesToEqualArrayElements_II_462();
        System.out.println(s.solution(new int[]{4,1,4,1})); //6
        System.out.println(s.solution(new int[]{3,2,1,1,2,3,1})); //5
        System.out.println(s.solution(new int[]{1,10,2,9})); //16
        System.out.println(s.solution(new int[]{1,2,3})); //2

        System.out.println(s.solutionBF(new int[]{4,1,4,1})); //6
        System.out.println(s.solutionBF(new int[]{3,2,1,1,2,3,1})); //5
    }

    // O(n*log(n)) - time, O(1) - space
    public int solution(int[] nums) {
        int l = 0, r = nums.length - 1, sum = 0;
        Arrays.sort(nums);
        while (l < r) {
            sum += nums[r] - nums[l];
            l++;
            r--;
        }
        return sum;
    }

    // O(n^2) - time
    public int solutionBF(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            int sum = 0;
            for (int j = 0; j < A.length; j++) {
                sum += Math.abs(A[i] - A[j]);
            }
            min = Math.min(min, sum);
        }
        return min;
    }
}
