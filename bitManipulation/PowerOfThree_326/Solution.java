package bitManipulation.PowerOfThree_326;

/** E
 * Given an integer, write a function to determine if it is a power of three.

 Example 1:
 Input: 27
 Output: true

 Example 2:
 Input: 0
 Output: false

 Example 3:
 Input: 9
 Output: true

 Example 4:
 Input: 45
 Output: false

 Follow up:
 Could you do it without using any loop / recursion?
 */
public class Solution {

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n%3 == 0) {
            n = n/3;
        }
        return n == 1;
    }

    // https://leetcode.com/problems/power-of-three/solution/
    public boolean isPowerOfThree2(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
