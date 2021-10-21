package string.easy;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 * Input: 123
 * Output: 321
 *
 * Example 2:
 * Input: -123
 * Output: -321
 *
 * Example 3:
 * Input: 120
 * Output: 21
 *
 * Note:
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger_7 {

    public static void main(String[] args) {
        ReverseInteger_7 s = new ReverseInteger_7();
        System.out.println(s.reverse(123)); //321
        System.out.println(s.reverse(-123)); //-321
        System.out.println(s.reverse(120)); //21
    }

    // O(log(x)) - time, O(1) - space; there are roughly log10(x) digits in x.
    public int reverse(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = -x;
        }
        long res = 0;
        while (x > 0) {
            res = res * 10 + x%10;
            if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE) {
                return 0;
            }
            x = x/10;
        }

        return isNegative ? (int)-res : (int)res;
    }

    // without using long
    public int reverse2(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
        }
        int xx = Math.abs(x);
        int newX = 0;
        while(xx > 0) {
            int c = xx%10;
            xx = xx/10;
            if (newX > Integer.MAX_VALUE/10) {
                return 0;
            }
            newX = newX*10 + c;
        }
        return isNegative ? -newX : newX;
    }
}
