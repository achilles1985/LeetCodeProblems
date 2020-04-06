package array;

import java.util.HashMap;
import java.util.Map;

/**
 * M
 * Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.
 * <p>
 * Example 1:
 * Input: A = [4,5,0,-2,-3,1], K = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by K = 5:
 * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 * <p>
 * Note:
 * 1 <= A.length <= 30000
 * -10000 <= A[i] <= 10000
 * 2 <= K <= 10000
 */
public class SubarraySumsDivisibleByK_974 {

    public static void main(String[] args) {
        SubarraySumsDivisibleByK_974 s = new SubarraySumsDivisibleByK_974();
        System.out.println(s.subarraysDivByK2(new int[]{4, 5, 0, -2, -3, 1}, 5)); //7
    }

    // O(n^2) - time, O(1) - space
    public int subarraysDivByK(int[] A, int K) {
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int sum = 0; // it's safe to use int since max sum <= 10,000*10,000
            for (int j = i; j < A.length; j++) {
                sum += A[j];
                if (sum % K == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraysDivByK2(int[] A, int K) {
        //this hashmap contains <sum,no of times that sum has came>
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);
        int cnt = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = (sum + A[i]) % K;
            //if array is [-2,-3] and K = 5, then for 1st iteration sum would be -2 . According to question [-2,-3] will divisible by 5, so
            // inorder to accomodate those changes with make it positive so sum = -2 +3 =>  sum =1;
            if (sum < 0) {
                sum += K;
            }
            //here we are using concept of prefix sum
            //sum[i] = sum[0] + sum[1]....+sum[i];
            //sum[j] = sum[0]+sum[1]+....sum[j];
            //suppose if (sum[i]%k) = t and sum[j] = t where j<i
            // then there has to be sum[j] + k*N =sum[i] ,where N is some natural number.
            //so basically we are always checking if (sum+nums[i])%k is peresent in out hashMap,
            //then we are using our hashmap to know in how many ways we can achieve the sum+nums[i]%k and add it to result
            if (sumMap.get(sum) != null) {
                cnt += sumMap.get(sum);
            }
            if (sumMap.get(sum) != null) {
                sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
            } else {
                sumMap.put(sum, 1);
            }
        }
        return cnt;
    }
}
