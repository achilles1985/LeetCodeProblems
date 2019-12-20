package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/word-break/
/** M
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:
 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.

 Example 1:
 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".

 Example 2:
 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.

 Example 3:
 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 */
public class WordBreak_139 {

    public static void main(String[] args) {
        WordBreak_139 s = new WordBreak_139();
        System.out.println(s.wordBreak("abcde", new ArrayList<>(Arrays.asList("ab", "cd", "e")))); // true
        System.out.println(s.wordBreak("leetcodemore", new ArrayList<>(Arrays.asList("leet", "code", "more")))); // true
        System.out.println(s.wordBreakTopDownDynamic("leetcode", new ArrayList<>(Arrays.asList("le", "leet", "code")))); // true

        System.out.println(s.wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false
        System.out.println(s.wordBreakTopDownDynamic("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false

        System.out.println(s.wordBreak("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true
        System.out.println(s.wordBreakTopDownDynamic("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true

        // button up
        System.out.println(s.wordBreakBottomUp("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true
        System.out.println(s.wordBreakBottomUp("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true
        System.out.println(s.wordBreakBottomUp("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false
    }

    // O(n^n) - time, O(n) - space
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakHelper(s, new HashSet<>(wordDict));
    }

    public boolean wordBreakHelper(String s, Set<String> wordDict) {
        if (wordDict.contains(s)) {
            return true;
        }
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordDict.contains(prefix)) {
                if (wordBreakHelper(s.substring(i), wordDict)) {
                    return true;
                }
            }
        }
        return false;
    }

    // O(n^2) - time, O(n) - space
    public boolean wordBreakTopDownDynamic(String s, List<String> wordDict) {
        Set<String> cache = new HashSet<>();
        return wordBreakTopDownDynamic(s, new HashSet<>(wordDict), cache);
    }

    private boolean wordBreakTopDownDynamic(String str, Set<String> wordDict, Set<String> cache) {
        if (cache.contains(str)) {
            return false;
        }
        if (wordDict.contains(str)) {
            return true;
        }
        for (int i = 1; i < str.length(); i++) {
            String prefix = str.substring(0, i);
            if (wordDict.contains(prefix)) {
                if (wordBreakTopDownDynamic(str.substring(i), wordDict, cache)) {
                    return true;
                }
            }
        }
        cache.add(str);
        return false;
    }

    // O(n^2) - time, O(n) - space
    public boolean wordBreakBottomUp(String s, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        boolean[] T = new boolean[s.length() + 1];
        T[0] = true;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String sub = s.substring(j, i);
                if (T[i] && set.contains(sub)) {
                    T[j] = true;
                    break;
                }
            }
        }
        return T[s.length()];
    }
}
