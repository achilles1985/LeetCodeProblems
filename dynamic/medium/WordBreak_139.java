package dynamic.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/word-break/
/** M [MARKED]
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
        System.out.println(s.wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false
        System.out.println(s.wordBreak("leetcodemore", new ArrayList<>(Arrays.asList("leet", "code", "more")))); // true

        System.out.println(s.wordBreak("abcde", new ArrayList<>(Arrays.asList("ab", "cd", "e")))); // true
        System.out.println(s.wordBreak("leetcodemore", new ArrayList<>(Arrays.asList("leet", "code", "more")))); // true

        System.out.println(s.wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false
        System.out.println(s.wordBreakTopDownDynamic("leetcode", new ArrayList<>(Arrays.asList("leet", "code")))); // true
        System.out.println(s.wordBreakTopDownDynamic("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false

        System.out.println(s.wordBreak("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true
        System.out.println(s.wordBreakTopDownDynamic("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true

        // button up
        System.out.println(s.wordBreakBottomUp("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true
        System.out.println(s.wordBreakBottomUp("abcd", new ArrayList<>(Arrays.asList("ab", "cd")))); // true
        System.out.println(s.wordBreakBottomUp("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false
    }

    // O(n^n) - time (Consider the worst case where ss = "aaaaaaa" and every prefix of ss is present in the dictionary of words,
    // then the recursion tree can grow upto n^n,
    // O(n^2) - space (recursion tree depth + prefix). T(n) = T(n-1) + T(n-2) + T(n-3) + .... + T(1)
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreakHelper(s, new HashSet<>(wordDict));
    }

    public boolean wordBreakHelper(String s, Set<String> wordDict) {
        if (wordDict.contains(s)) {
            return true;
        }
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i); //len
            if (wordDict.contains(prefix) && wordBreakHelper(s.substring(i), wordDict)) {
                return true;
            }
        }
        return false;
    }

    // O(n^3) - time (recursion tree can go up to n^2 + s.substring takes O(n)), O(n) - space. T(n) = T(n-1) + n - 2
    public boolean wordBreakTopDownDynamic(String s, List<String> wordDict) {
        Map<String, Boolean> cache = new HashMap<>();
        return wordBreakTopDownDynamic(s, new HashSet<>(wordDict), cache);
    }

    private boolean wordBreakTopDownDynamic(String suffix, Set<String> wordDict, Map<String, Boolean> cache) {
        if (cache.containsKey(suffix)) {
            return cache.get(suffix);
        }
        if (wordDict.contains(suffix)) {
            return true;
        }
        for (int i = 1; i < suffix.length(); i++) {
            String prefix = suffix.substring(0, i);
            if (wordDict.contains(prefix)) {
                if (wordBreakTopDownDynamic(suffix.substring(i), wordDict, cache)) {
                    cache.put(suffix.substring(i), true);
                    return true;
                }
            }
        }
        cache.put(suffix, false);
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

    // O(set^len*len) - time, O(len^2)
    public boolean wordBreakBF2(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        return helper2(s, new HashSet<>(wordDict));
    }

    private boolean helper2(String s, Set<String> set) {
        if (set.contains(s)) {
            return true;
        }
        for (String word: set) { // traverse not through the s but set of words
            int prefix = s.indexOf(word); // len
            if (prefix == 0 && helper2(s.substring(word.length()), set)) {
                return true;
            }
        }

        return false;
    }
}
