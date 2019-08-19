package binarySearch;

/** E
 Implement int sqrt(int x).
 Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

 Example 1:
 Input: 4
 Output: 2

 Example 2:
 Input: 8
 Output: 2
 Explanation: The square root of 8 is 2.82842..., and since
 the decimal part is truncated, 2 is returned.
 */
public class Sqrt {

    // We return the largest integer whose square is less than or equal to the given integer, e.g. sqr(21) = 4^2<sqr(21)<5^2, so we return 4.
    public static void main(String[] args) {
        Sqrt s = new Sqrt();
        System.out.println(s.mySqrt2(4)); //2
        System.out.println(s.mySqrt2(8)); //2
        System.out.println(s.mySqrt2(21)); //4
    }

    // O(log(x)) - time, O(1) - space
    public int mySqrt(int x) {
        long low = 0;
        long high = x;
        while (low <= high) {
            long mid = low + (high-low)/2;
            long res = mid*mid;
            if (res <= x) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }

        return (int)low-1;
    }

    public int mySqrt2(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int l = 1;
        int r = x/2;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid == x/mid) {
                return mid;
            }
            if (mid > x/mid) { // to avoid overflow
                r = mid-1;
            } else {
                l = mid+1;
                ans = mid;
            }
        }
        return ans;
    }
}