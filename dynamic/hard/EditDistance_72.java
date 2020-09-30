package dynamic.hard;

// https://leetcode.com/problems/edit-distance/

import java.util.HashMap;
import java.util.Map;

/** H
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
 You have the following 3 operations permitted on a word:
 Insert a character
 Delete a character
 Replace a character

 Example 1:
 Input: word1 = "horse", word2 = "ros"
 Output: 3
 Explanation:
 horse -> rorse (replace 'h' with 'r')
 rorse -> rose (remove 'r')
 rose -> ros (remove 'e')

 Example 2:
 Input: word1 = "intention", word2 = "execution"
 Output: 5
 Explanation:
 intention -> inention (remove 't')
 inention -> enention (replace 'i' with 'e')
 enention -> exention (replace 'n' with 'x')
 exention -> exection (replace 'n' with 'c')
 exection -> execution (insert 'u')
 */
/*
https://www.techiedelight.com/levenshtein-distance-edit-distance-problem/ - good explanation
 */
public class EditDistance_72 {

    public static void main(String[] args) {
        EditDistance_72 s = new EditDistance_72();
        String res = "abc".substring(0,0);
        System.out.println(s.minDistance("horse", "ros")); //3
        System.out.println(s.minDistanceDynamicBottomUp("horse", "ros")); //3
        System.out.println(s.minDistanceDynamicTopDown("horse", "ros")); //3

        System.out.println(s.minDistance("intention", "execution")); //5
        System.out.println(s.minDistanceDynamicBottomUp("intention", "execution")); //5
        System.out.println(s.minDistanceDynamicTopDown("intention", "execution")); //5
    }

    public int minDistance(String word1, String word2) {
        return minDistance(word1, word2, word1.length(), word2.length());
    }

    // O(3^n) - time, O(n) - space, where n - length of the longest string
    private int minDistance(String word1, String word2, int l1, int l2) {
        if (l1 == 0 && l2 == 0) {
            return 0;
        }
        if (l1 == 0) {
            return l2;
        }
        if (l2 == 0) {
            return l1;
        }
        if (word1.charAt(l1-1) == word2.charAt(l2-1)) {
            return minDistance(word1, word2, l1-1, l2-1);
        }

        return 1 + Math.min(minDistance(word1, word2, l1-1, l2), Math.min(minDistance(word1, word2, l1, l2-1), minDistance(word1, word2, l1-1, l2-1)));
    }

    // O(n*m) - time, space
    public int minDistanceDynamicTopDown(String word1, String word2) {
        Map<String, Integer> map = new HashMap<>();
        return minDistanceDynamicTopDown(word1, word2, word1.length()-1, word2.length()-1, map);
    }

    private int minDistanceDynamicTopDown(String word1, String word2, int l1, int l2, Map<String, Integer> map) {
        String key = l1+","+l2;
        if (map.containsKey(key)) {
            return map.get(key);
        }

        if (l1 < 0 && l2 < 0) {
            return 0;
        }
        if (l1 < 0) {
            return l2+1;
        }
        if (l2 < 0) {
            return l1+1;
        }
        if (word1.charAt(l1) == word2.charAt(l2)) {
            return minDistanceDynamicTopDown(word1, word2, l1-1, l2-1, map);
        }

        int min = 1 + Math.min(minDistanceDynamicTopDown(word1, word2, l1-1, l2, map), Math.min(minDistanceDynamicTopDown(word1, word2, l1, l2-1, map), minDistanceDynamicTopDown(word1, word2, l1-1, l2-1, map)));
        map.put(key, min);

        return map.get(key);
    }

    // O(n*m) - time, space
    public int minDistanceDynamicBottomUp(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return 0;
        }
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
