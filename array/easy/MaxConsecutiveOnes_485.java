package array.easy;

/** E
 Given a binary array, find the maximum number of consecutive 1s in this array.

 Example 1:
 Input: [1,1,0,1,1,1]
 Output: 3
 Explanation: The first two digits or the last three digits are consecutive 1s.
 The maximum number of consecutive 1s is 3.

 Note:
 The input array will only contain 0 and 1.
 The length of input array is a positive integer and will not exceed 10,000
 */
public class MaxConsecutiveOnes_485 {

    public static void main(String[] args) {
        MaxConsecutiveOnes_485 s = new MaxConsecutiveOnes_485();
        System.out.println(s.findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1})); //3
        System.out.println(s.findMaxConsecutiveOnes(new int[]{0,0,1,1,0,1,0,1})); //2
    }

    // O(n) - time, O(1) - space
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        for (int j = 0, count = 0; j < nums.length; j++) {
            if (nums[j] == 1) {
                count++;
            } else {
                count = 0;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
