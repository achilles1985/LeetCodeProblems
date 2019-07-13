package binarySearch.Sqrt_69;

/**
 https://leetcode.com/problems/sqrtx/#/description
 69. Sqrt(x)
 https://leetcode.com/problems/sqrtx/
 Implement int sqrt(int x).
 */

// Great solution
// https://discuss.leetcode.com/topic/24532/3-4-short-lines-integer-newton-every-language


public class Solution {

    // Newton`s algorithm
    // https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division
    // https://ru.wikipedia.org/wiki/%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%BD%D0%B0%D1%85%D0%BE%D0%B6%D0%B4%D0%B5%D0%BD%D0%B8%D1%8F_%D0%BA%D0%BE%D1%80%D0%BD%D1%8F_n-%D0%BD%D0%BE%D0%B9_%D1%81%D1%82%D0%B5%D0%BF%D0%B5%D0%BD%D0%B8
    // http://webhamster.ru/mytetrashare/index/mtb0/1684
    // https://discuss.leetcode.com/topic/24532/3-4-short-lines-integer-newton-every-language
    public int newtonSqr(int x) {
        long r = x;
        while(r*r > x) r = (r + x/r)/2;
        return (int)r;
    }

    public double mySqrt(int number) {
        if (number == 0) {
            return 0;
        }
        double lo = 1;
        double hi = number/2;
        while(lo <= hi) {
            double middle = lo + (hi - lo)/2;
            if (Math.abs(middle*middle - number) <= 0.01) {
                return middle;
            }
            if (middle*middle > number) {
                hi = middle - 0.001;
            } else {
                lo = middle + 0.001;
            }
        }

        return -1;
    }
}
