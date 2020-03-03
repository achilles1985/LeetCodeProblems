package bitManipulation.SumTwoIntegers_371;

/** E
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 */
/*
    1. Find carry.
    2. Add x and y with ^ and assign to x.
    3. Apply carry to y
 */
public class Solution {

    public int getSum(int x, int y) {
        while (y != 0) {
            int carry = x & y;
            x = x ^ y;
            y = carry << 1;
        }

        return x;
    }
}
