package dynamic.medium;

// https://leetcode.com/problems/delete-operation-for-two-strings/

import java.util.HashMap;
import java.util.Map;

/**
 * M
 * Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same,
 * where in each step you can delete one character in either string.
 * <p>
 * Example 1:
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * <p>
 * Note:
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 */
public class DeleteOperationForTwoStrings_583 {

    public static void main(String[] args) {
        DeleteOperationForTwoStrings_583 s = new DeleteOperationForTwoStrings_583();
        System.out.println(s.minDistanceBF("eeta", "aetka")); //2
        System.out.println(s.minDistance("sea", "eat")); //2
        System.out.println(s.minDistanceDynamicBottomUp("sea", "eat")); //2
        System.out.println(s.minDistanceTopDown("sea", "eat")); //2

        System.out.println(s.minDistanceBF("seadpck", "")); //7
        System.out.println(s.minDistanceBF("adg", "abcdefg")); //4
        System.out.println(s.minDistanceTopDown("adg", "abcdefg")); //4
    }

    public int minDistance(String s1, String s2) {
        return s1.length() + s2.length() - 2 * lcs(s1, s2, s1.length(), s2.length());
    }

    private int lcs(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        }
        return Math.max(lcs(s1, s2, m, n - 1), lcs(s1, s2, m - 1, n));
    }

    // O(2^max(n,m)) - time, O(max(m,n)) - space
    public int minDistanceBF(String word1, String word2) {
        return minDistanceBF(word1, word2, word1.length(), word2.length());
    }

    // O(m*n) - time, space
    public int minDistanceTopDown(String word1, String word2) {
        Map<String, Integer> map = new HashMap<>();
        return minDistanceTopDown(word1, word2, word1.length(), word2.length(), map);
    }

    // O(n*m) - time, space; n, m words length
    public int minDistanceDynamicBottomUp(String word1, String word2) {
        int[][] res = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) {
            res[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            res[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    res[i][j] = res[i - 1][j - 1];
                } else {
                    res[i][j] = 1 + Math.min(res[i - 1][j], res[i][j - 1]);
                }
            }
        }
        return res[word1.length()][word2.length()];
    }

    private int minDistanceBF(String word1, String word2, int i, int j) {
        if (i == 0 && j == 0) {
            return 0;
        }
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            return minDistanceBF(word1, word2, i - 1, j - 1);
        }

        return 1 + Math.min(minDistanceBF(word1, word2, i - 1, j), minDistanceBF(word1, word2, i, j - 1));
    }

    private int minDistanceTopDown(String word1, String word2, int i, int j, Map<String, Integer> map) {
        String key = i + "," + j;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (i == 0 && j == 0) {
            return 0;
        }
        if (i == 0) {
            return j;
        }
        if (j == 0) {
            return i;
        }
        if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
            return minDistanceTopDown(word1, word2, i - 1, j - 1, map);
        }

        int min = 1 + Math.min(minDistanceTopDown(word1, word2, i - 1, j, map), minDistanceTopDown(word1, word2, i, j - 1, map));
        map.put(key, min);

        return map.get(key);
    }
}
