package array;

/** M
 Given an array of non-negative integers, you are initially positioned at the first index of the array.
 Each element in the array represents your maximum jump length at that position.
 Determine if you are able to reach the last index.

 Example 1:
 Input: [2,3,1,1,4]
 Output: true
 Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 Example 2:

 Input: [3,2,1,0,4]
 Output: false
 Explanation: You will always arrive at index 3 no matter what. Its maximum
 jump length is 0, which makes it impossible to reach the last index.
 */

public class JumpGame_55 {

    public static void main(String[] args) {
        JumpGame_55 s = new JumpGame_55();
        System.out.println(s.canJump(new int[]{3,2,1,0,4})); // false
        System.out.println(s.canJump(new int[]{2,3,1,1,4})); // true
    }

    // O(n) - time, O(1) - space
    public boolean canJump(int[] nums) {
        int furthestReachestSoFar = 0;
        int lastIndex = nums.length-1;
        for (int i = 0; i <= furthestReachestSoFar && furthestReachestSoFar < lastIndex; i++) {
            furthestReachestSoFar = Math.max(furthestReachestSoFar, i + nums[i]);
        }

        return furthestReachestSoFar >= lastIndex;
    }

}
