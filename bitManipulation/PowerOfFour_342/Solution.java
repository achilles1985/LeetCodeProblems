package bitManipulation.PowerOfFour_342;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 Example 1:
 Input: 16
 Output: true

 Example 2:
 Input: 5
 Output: false

 Follow up: Could you solve it without loops/recursion?
 */
public class Solution {

    public boolean isPowerOfFour(int num) {
        if (num <= 0) {
            return false;
        }
        for (int i = 0; i < 32; i += 2) {
            if ((num & (1 << i)) == num) {
                return true;
            }
        }

        return false;
    }

    // count number of bits before 1, the number must be even
    public boolean isPowerOfFour2(int num) {
        if (num <= 0) {
            return false;
        }
        // check if the num contains only one 1;
        if ((num & (num-1)) != 0) {
            return false;
        }
        int count = 0;
        while (num > 1) {
            num >>= 1;
            count++;
        }
        // number of 0 after 1 must be odd
        return count%2 == 0;
    }

    public boolean isPowerOfFour3(int num) {
        return (Math.log10(num)/Math.log10(4))%1 == 0;
    }
}
