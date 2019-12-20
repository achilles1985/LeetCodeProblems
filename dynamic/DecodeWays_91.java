package dynamic;

// https://leetcode.com/problems/decode-ways/

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/** M
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 'A' -> 1
 'B' -> 2
 ...
 'Z' -> 26

 Given a non-empty string containing only digits, determine the total number of ways to decode it.
 Example 1:
 Input: "12"
 Output: 2
 Explanation: It could be decoded as "AB" (1 2) or "L" (12).

 Example 2:
 Input: "226"
 Output: 3
 Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays_91 {

    public static void main(String[] args) {
        DecodeWays_91 s = new DecodeWays_91();
        System.out.println(s.numDecodings2("12131")); //5

        System.out.println(s.numDecodings("0")); //0
        System.out.println(s.numDecodingsDynamicTopDown("0")); //0

        System.out.println(s.numDecodings("12")); //2
        System.out.println(s.numDecodingsDynamicTopDown("12")); //2
        System.out.println(s.numDecodings("226")); //3
        System.out.println(s.numDecodingsDynamicTopDown("226")); //3

        System.out.println(s.numDecodings("12131")); //5
        System.out.println(s.numDecodingsDynamicTopDown("12131")); //5
    }

    private int numDecodings2(String str) {
        return numDecodingsHelper2(str, 0, str.length(), new AtomicInteger(0));
    }

    private int numDecodingsHelper2(String str, int i, int length, AtomicInteger counter) {
        if (i == length) {
            return 1;
        }

        if (i + 1 <= length) {
            String substr = str.substring(i, i + 1);
            if (isValid(substr)) {
                counter.getAndAdd(numDecodingsHelper2(str, i + 1, length, counter));
            }
        }
        if (i + 2 <= length) {
            String substr = str.substring(i, i + 2);
            if (isValid(substr)) {
                counter.getAndAdd(numDecodingsHelper2(str, i + 2, length, counter));
            }
        }
        return counter.get();
    }

    // O(2^n) - time, O(n) - space
    public int numDecodings(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        return numDecodings(s, 0);
    }

    // O(n) - time, space
    public int numDecodingsDynamicTopDown(String s) {
        int[] cache = new int[s.length()+1];
        Arrays.fill(cache, -1);
        return numDecodingsDynamicTopDown(s, 0, cache);
    }

    private int numDecodingsDynamicTopDown(String str, int i, int[] cache) {
        if (cache[i] != -1) {
            return cache[i];
        }
        if (i >= str.length()) {
            return 1;
        }

        int count = 0;
        if (i+1 <= str.length()) {
            String sub1 = str.substring(i, i + 1);
            if (isValid(sub1)) {
                count += numDecodingsDynamicTopDown(str, i + 1, cache);
            }
        }

        if (i+2 <= str.length()) {
            String sub2 = str.substring(i, i + 2);
            if (isValid(sub2)) {
                count += numDecodingsDynamicTopDown(str, i + 2, cache);
            }
        }

        cache[i] = count;
        return cache[i];
    }

    private int numDecodings(String str, int i) {
        if (i >= str.length()) {
            return 1;
        }

        int count = 0;
        if (i+1 <= str.length()) {
            String sub1 = str.substring(i, i + 1);
            if (isValid(sub1)) {
                count += numDecodings(str, i + 1);
            }
        }

        if (i+2 <= str.length()) {
            String sub2 = str.substring(i, i + 2);
            if (isValid(sub2)) {
                count += numDecodings(str, i + 2);
            }
        }

        return count;
    }

    private boolean isValid(String str) {
        Integer num = Integer.parseInt(str);
        if (str.charAt(0) == '0') {
            return false;
        }

        return num >= 1 && num <= 26;
    }

}
