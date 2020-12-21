package backtracking.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * M [marked]
 * Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
 * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 * 0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 * F.length >= 3;
 * and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 * Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
 * Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
 * <p>
 * Example 1:
 * Input: "123456579"
 * Output: [123,456,579]
 * <p>
 * Example 2:
 * Input: "11235813"
 * Output: [1,1,2,3,5,8,13]
 * <p>
 * Example 3:
 * Input: "112358130"
 * Output: []
 * Explanation: The task is impossible.
 * <p>
 * Example 4:
 * Input: "0123"
 * Output: []
 * Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
 * <p>
 * Example 5:
 * Input: "1101111"
 * Output: [110, 1, 111]
 * Explanation: The output [11, 0, 11, 11] would also be accepted.
 * <p>
 * Note:
 * 1 <= S.length <= 200
 * S contains only digits.
 */
/*
    1. Try to expand the the last element in the list till end of the string. Then remove last element from the list and try to expand it.
 */
public class SplitArrayIntoFibonacciSequence_842 {

    public static void main(String[] args) {
        SplitArrayIntoFibonacciSequence_842 s = new SplitArrayIntoFibonacciSequence_842();
        System.out.println(s.splitIntoFibonacci("214748364721474836422147483641"));
        System.out.println(s.splitIntoFibonacci("5511816597"));
        System.out.println(s.splitIntoFibonacci("1101111")); // [11,0,11,11]
        System.out.println(s.splitIntoFibonacci("0123")); // []
        System.out.println(s.splitIntoFibonacci("123446")); // [12,34,46]

        System.out.println(s.splitIntoFibonacci2("123456579")); // [123,456,579]
        System.out.println(s.splitIntoFibonacci2("11235813")); // [1,1,2,3,5,8,13]
        System.out.println(s.splitIntoFibonacci2("112358130")); // []
    }

    // O(2^n) - time, O(n) - space
    public List<Integer> splitIntoFibonacci(String str) {
        List<List<Integer>> res = new ArrayList<>(); // since there can be several answers, but we need to return only the first one.
        split2(str, 0, res, new ArrayList<>());

        return res.isEmpty() ? Collections.emptyList() : res.get(0);
    }

    public List<Integer> splitIntoFibonacci2(String str) {
        List<Integer> res = new ArrayList<>();
        split(str, 0, res);

        return res;
    }

    private void split2(String str, int pos, List<List<Integer>> res, List<Integer> temp) {
        if (pos == str.length() && temp.size() >= 3) {
            res.add(new ArrayList<>(temp));
            return;
        }
        long num = 0;
        for (int i = pos; i < str.length(); i++) {
            num = num * 10 + str.charAt(i) - '0';
            if (isValidNum(num, str, pos, i, temp)) {
                temp.add((int) num);
                split2(str, i + 1, res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }

    private boolean split(String str, int pos, List<Integer> res) {
        if (pos == str.length()) {
            return res.size() >= 3;
        }

        long num = 0;
        for (int i = pos; i < str.length(); i++) {
            num = num * 10 + str.charAt(i) - '0';
            if (isValidNum(num, str, pos, i, res)) {
                res.add((int) num);
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

        int last = res.size() - 1;
        return res.get(last) + res.get(last - 1) == (int) num;
    }

    // Incorrect if str > Integer.MAX or Long.MAX
    //////////////// alternative to fibonacci2() if str < Long.MAX
    public List<Integer> splitIntoFibonacci3(String S) {
        if (S == null || S.length() < 3) {
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        helper(S, new ArrayList<>(), result, 0);

        return result.isEmpty() ? Collections.emptyList() : result.get(0);
    }

    private void helper(String str, List<Integer> temp, List<List<Integer>> result, int start) {
        if (start == str.length() && temp.size() >= 3) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < str.length(); i++) {
            String sub = str.substring(start, i+1);
            if (isValid(sub, temp)) {
                int num = Integer.parseInt(sub);
                temp.add(num);
                helper(str, temp, result, i+1);
                temp.remove(temp.size()-1);
            }
        }
    }

    private boolean isValid(String str, List<Integer> list) {
        if (str.length() >= 2 && str.startsWith("0")) {
            return false;
        }
        long num = Long.parseLong(str);
        if (num > Integer.MAX_VALUE) {
            return false;
        }
        if (list.size() < 2) {
            return true;
        }
        int last = list.size()-1;

        return (list.get(last) + list.get(last-1)) == (int) num;
    }

}
