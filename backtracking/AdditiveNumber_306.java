package backtracking;

/** M
 * Additive number is a string whose digits can form additive sequence.
 A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

 Example 1:
 Input: "112358"
 Output: true
 Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8

 Example 2:
 Input: "199100199"
 Output: true
 Explanation: The additive sequence is: 1, 99, 100, 199.
 1 + 99 = 100, 99 + 100 = 199

 Follow up:
 How would you handle overflow for very large input integers?
 */
public class AdditiveNumber_306 {

    public static void main(String[] args) {
        AdditiveNumber_306 s= new AdditiveNumber_306();
        System.out.println(s.isAdditiveNumber("112358")); // true
        System.out.println(s.isAdditiveNumber("199100199")); // true
        System.out.println(s.isAdditiveNumber("231458")); // false
    }

    // Cannot understand how it works
    public boolean isAdditiveNumber(String num) {
        int n = num.length();
        for (int i = 1; i <= n/2; ++i) {
            if (num.charAt(0) == '0' && i > 1) {
                return false;
            }
            for (int j = i + 1; n - j >= Math.max(i, j - i); ++j) {
                if (num.charAt(i) == '0' && j > i + 1) {
                    continue;
                }
                long a = Long.parseLong(num.substring(0, i));
                long b = Long.parseLong(num.substring(i, j));
                if (backtrack(num, j, a, b)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean backtrack(String s, int idx, long a, long b) {
        if (idx == s.length()) {
            return true;
        }
        for (int i = idx; i < s.length(); ++i) {
            if (s.charAt(idx) == '0' && i > idx){
                break;
            }
            long c = Long.parseLong(s.substring(idx, i + 1));
            if (c == a + b && backtrack(s, i + 1, b, c)) {
                return true;
            }
        }

        return false;
    }
}
