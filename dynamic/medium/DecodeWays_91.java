package dynamic.medium;

// https://leetcode.com/problems/decode-ways/

import java.util.Arrays;

/** M [marked]
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
        System.out.println(s.numDecodings4("10")); //1
        System.out.println(s.numDecodings4("12131")); //5
        System.out.println(s.numDecodings4("226")); //3
        System.out.println(s.numDecodings4("12")); //2

        System.out.println(s.numDecodings("0")); //0
        System.out.println(s.numDecodingsDynamicTopDown("0")); //0

        System.out.println(s.numDecodings("12")); //2
        System.out.println(s.numDecodingsDynamicTopDown("12")); //2
        System.out.println(s.numDecodings("226")); //3
        System.out.println(s.numDecodingsDynamicTopDown("226")); //3

        System.out.println(s.numDecodings("12131")); //5
        System.out.println(s.numDecodingsDynamicTopDown("12131")); //5
    }

    // O(2^n) - time, O(n) - space. Time can be improved with memoization.
    public int numDecodings3(String s) {
        return numDecodings3(s, 0);
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
        if (str.charAt(0) == '0') {
            return false;
        }
        Integer num = Integer.parseInt(str);

        return num >= 1 && num <= 26;
    }

    // Incorrect
    public int numDecodings4(String s) {
        return helper(s, 0);
    }

    private int helper(String s, int i) {
        if (s.isEmpty()) {
            return 0;
        }
        int count = 0;
        if (i+1 <= s.length()) {
            String prefix = s.substring(i, i + 1);
            if (isValid(prefix)) {
                count += helper(s.substring(i+1), i+1) + 1;
            }
        }
        if (i+2 <= s.length()) {
            String prefix2 = s.substring(i, i + 2);
            if (isValid(prefix2)) {
                count += helper(s.substring(i+2), i+2) + 1;
            }
        }
        return count;
    }

    private int numDecodings3(String s, int decodePointer) {
        if (decodePointer >= s.length()) {
            return 1;
        }
        int count = 0;
        for (int i = 1; i <= 2; i++) {
            if (decodePointer + i <= s.length()) {
                String snippet = s.substring(decodePointer, decodePointer + i);
                if (isValid(snippet)) {
                    count += numDecodings3(s, decodePointer + i);
                }
            }
        }
        return count;
    }
}
