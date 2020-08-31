package array.easy;

/** E
 Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

 Example 1:
 Input: [3,0,1]
 Output: 2

 Example 2:
 Input: [9,6,4,2,3,5,7,0,1]
 Output: 8

 Note:
 Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
public class MissingNumber_268 {

    public static void main(String[] args) {
        MissingNumber_268 s = new MissingNumber_268();
        System.out.println(s.missingNumber(new int[]{3,0,1})); //2
        System.out.println(s.missingNumber(new int[]{9,6,4,2,3,5,7,0,1})); //8

        System.out.println(s.missingNumber2(new int[]{3,0,1})); //2
        System.out.println(s.missingNumber2(new int[]{9,6,4,2,3,5,7,0,1})); //8

        System.out.println(s.missingNumber3(new int[]{3,0,1})); //2
        System.out.println(s.missingNumber3(new int[]{9,6,4,2,3,5,7,0,1})); //8
    }

    // O(n) - time, O(1) - space
    public int missingNumber(int[] nums) {
        int sum1 = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum1 += nums[i];
        }
        int sum2 = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum2 += i;
        }
        return sum2-sum1;
    }

    // O(n) - time, O(1) - space
    public int missingNumber2(int[] nums) {
        int miss = nums.length;
        for (int i = 0; i < nums.length; i++) {
            miss ^= i ^ nums[i];
        }
        return miss;
    }

    // O(n) - time, O(1) - space
    // formula N*(N+1)/2
    public int missingNumber3(int[] nums) {
        int sum = nums.length*(nums.length+1)/2;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
        }
        return sum;
    }
}
