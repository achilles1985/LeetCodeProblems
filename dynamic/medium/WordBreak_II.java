package dynamic.medium;

import java.util.*;

// https://leetcode.com/problems/word-break/

/** M [MARKED]
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 determine the total number of ways to form a target word.

 Example 3:
 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 */
public class WordBreak_II {

    public static void main(String[] args) {
        WordBreak_II s = new WordBreak_II();
        System.out.println(s.wordBreak("enterapotentpot", new HashSet<>(Arrays.asList("a", "p", "ent", "enter", "ot", "o", "t")))); // 4
        System.out.println(s.wordBreak("catsandog", new HashSet<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // 0
        System.out.println(s.wordBreak("leetcodemore", new HashSet<>(Arrays.asList("leet", "code", "more")))); // 1
    }

    // O(len^wordDict*len) - time, O(len^2)
    public int wordBreak(String s, Set<String> wordDict) {
        if (s.isEmpty()) {
            return 1;
        }

        int count = 0;
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i); //len
            if (wordDict.contains(prefix)) {
                count += wordBreak(s.substring(i), wordDict);
            }
        }
        return count;
    }


}
