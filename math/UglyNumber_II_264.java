package math;

/** M
 Write a program to find the n-th ugly number.
 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example:
 Input: n = 10
 Output: 12
 Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

 Note:
 1 is typically treated as an ugly number.
 n does not exceed 1690.
 */
public class UglyNumber_II_264 {

    public static void main(String[] args) {
        UglyNumber_II_264 s = new UglyNumber_II_264();

        System.out.println(s.nthUglyNumberBF(7)); //8
        System.out.println(s.nthUglyNumberBF(10)); //12
        System.out.println(s.nthUglyNumberBF(15)); //24

        System.out.println(s.nthUglyNumber(7)); //8
        System.out.println(s.nthUglyNumber(10)); //12
        System.out.println(s.nthUglyNumber(15)); //24
    }

    // O(?) - time, O(1) - space
    public int nthUglyNumberBF(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 1;
        int i = 1;
        while (count < n) {
            i++;
            if (isUgly(i)) {
                count++;
            }
        }
        return i;
    }

    // https://leetcode.com/problems/ugly-number-ii/discuss/327344/O(n)-Java-Solution-Iterative
    // O(n) - time, O(n) - space
    public int nthUglyNumber(int n) {
        if (n < 0) {
            return -1;
        }
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        for (int i = 1; i < n; i++) {
            ugly[i] = Math.min(2 * ugly[i2], Math.min(3 * ugly[i3], 5 * ugly[i5]));
            if (ugly[i] == 2 * ugly[i2]) {
                i2++;
            }
            if (ugly[i] == 3 * ugly[i3]) {
                i3++;
            }
            if (ugly[i] == 5 * ugly[i5]) {
                i5++;
            }
        }

        return ugly[n-1];
    }

    private boolean isUgly(int num) {
        num = check(num, 2);
        num = check(num, 3);
        num = check(num, 5);

        return num == 1 ? true : false;
    }

    private int check(int num, int div) {
        while (num%div == 0) {
            num /= div;
        }

        return num;
    }
}
