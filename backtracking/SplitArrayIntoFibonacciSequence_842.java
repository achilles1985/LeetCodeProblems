package backtracking;

import java.util.ArrayList;
import java.util.List;

/** M
 * Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
 Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 F.length >= 3;
 and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
 Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

 Example 1:
 Input: "123456579"
 Output: [123,456,579]

 Example 2:
 Input: "11235813"
 Output: [1,1,2,3,5,8,13]

 Example 3:
 Input: "112358130"
 Output: []
 Explanation: The task is impossible.

 Example 4:
 Input: "0123"
 Output: []
 Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.

 Example 5:
 Input: "1101111"
 Output: [110, 1, 111]
 Explanation: The output [11, 0, 11, 11] would also be accepted.

 Note:
 1 <= S.length <= 200
 S contains only digits.
 */
public class SplitArrayIntoFibonacciSequence_842 {

    public static void main(String[] args) {
        SplitArrayIntoFibonacciSequence_842 s = new SplitArrayIntoFibonacciSequence_842();
        System.out.println(s.splitIntoFibonacci("123456579")); // [123,456,579]
        System.out.println(s.splitIntoFibonacci("11235813")); // [1,1,2,3,5,8,13]
        System.out.println(s.splitIntoFibonacci("112358130")); // []
    }

    public List<Integer> splitIntoFibonacci(String str) {
        List<Integer> res = new ArrayList<>();
        split(str, 0, res);

        return res;
    }

    private boolean split(String str, int pos, List<Integer> res) {
        if (pos == str.length()) {
            return res.size() >= 3;
        }

        long num = 0;
        for (int i = pos; i < str.length(); i++) {
            num = num * 10 + str.charAt(i) - '0';
            if (isValidNum(num, str, pos, i, res)) {
                res.add((int)num);
                if (split(str, i + 1, res)) {
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }

        return false;
    }

    private boolean isValidNum(long num, String str, int start, int currIdx, List<Integer> res) {
        if (start != currIdx && str.charAt(start) == '0') {
            return false;
        }

        if (num > Integer.MAX_VALUE) {
            return false;
        }
        if (res.size() <= 1) {
            return true;
        }

        int i = res.size() - 1;
        return res.get(i) + res.get(i - 1) == (int)num;
    }
}
