package array.medium;

/** M
 Given an array A of 0s and 1s, we may change up to K values from 0 to 1.
 Return the length of the longest (contiguous) subarray that contains only 1s.

 Example 1:
 Input: A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 Output: 6
 Explanation:
 [1,1,1,0,0,1,1,1,1,1,1]
 Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

 Example 2:
 Input: A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 Output: 10
 Explanation:
 [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 Bolded numbers were flipped from 0 to 1.  The longest subarray is underlined.

 Note:
 1 <= A.length <= 20000
 0 <= K <= A.length
 A[i] is 0 or 1
 */
public class MaxConsecutiveOnes_III_1004 {

    public static void main(String[] args) {
        MaxConsecutiveOnes_III_1004 s = new MaxConsecutiveOnes_III_1004();

        System.out.println(s.longestOnes(new int[]{0,0,0,0,0,1,1,1}, 2)); //5
        System.out.println(s.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2)); //6
        System.out.println(s.longestOnes(new int[]{1,1,0,0,0,0,1,0}, 2)); //4
        System.out.println(s.longestOnes(new int[]{1,1,1,0,0,0,1,1}, 2)); //5
        System.out.println(s.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3)); //10
    }

    // O(n) - time, O(1) - space (sliding window)
    public int longestOnes(int[] A, int k) {
        int left = 0, max = 0;
        for (int right = 0; right < A.length; right++) {
            if (A[right] == 0) {
                k--;
            }
            if (k < 0) {
                if (A[left] == 0) {
                    k++;
                }
                left++;
            }
            max = Math.max(max, right- left + 1);
        }

        return max;
    }

    public int longestOnes2(int[] A, int k) {
        int zeroCount = 0, left = 0, res = 0;
        for (int right=0; right<A.length; right++){
            if (A[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > k){
                if (A[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            res = Math.max(res, right-left + 1);
        }
        return res;
    }

}
