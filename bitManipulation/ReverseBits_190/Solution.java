package bitManipulation.ReverseBits_190;

/**
 * E
 * Reverse bits of a given 32 bits unsigned integer.
 * Example 1:
 * Input: 00000010100101000001111010011100
 * Outpu: 00111001011110000010100101000000
 * Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596,
 * so return 964176192 which its binary representation is 00111001011110000010100101000000.
 * <p>
 * Example 2:
 * Input: 11111111111111111111111111111101
 * Output: 10111111111111111111111111111111
 * Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293,
 * so return 3221225471 which its binary representation is 10101111110010110010011101101001.
 * <p>
 * Note:
 * Note that in some languages such as Java, there is no unsigned integer type. In this case,
 * both input and output will be given as signed integer type and should not affect your implementation,
 * as the internal binary representation of the integer is the same whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement notation. Therefore,
 * in Example 2 above the input represents the signed integer -3 and the output represents the signed integer -1073741825.
 */
public class Solution {

    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int i = 0;
        int j = 31;
        while (i < j) {
            n = swapBits(n, i, j);
            i++;
            j--;
        }

        return n;
    }

    private int swapBits(int x, int i, int j) {
        if (((x >>> i) & 1) !=((x >>> j) & 1)){
            int bitMask = (1 << i)|(1 << j);
            x ^= bitMask;
        }
        return x;
    }

    // https://www.geeksforgeeks.org/reverse-actual-bits-given-number/
    public int reverseBits2(int n) {
        int ans = 0;
        while (n > 0) {
            ans <<= 1;
            if ((n & 1) == 1) {
                ans ^= 1;
            }
            n >>= 1;
        }

        return ans;
    }
}
