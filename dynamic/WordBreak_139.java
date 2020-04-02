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
        System.out.println(s.wordBreak2("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "sand", "and")))); // false
        System.out.println(s.wordBreak2("leetcodemore", new ArrayList<>(Arrays.asList("leet", "code", "more")))); // true

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

    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> unique = new HashSet<>(wordDict);
        return wordBreak2Helper(0, "", s, unique);
    }

    private boolean wordBreak2Helper(int start, String suffix, String s, Set<String> set) {
        if (set.contains(suffix)) {
            return true;
        }
        for (int i = start; i <= s.length(); i++) {
            String prefix = s.substring(start, i);
            if (set.contains(prefix) && wordBreak2Helper(i, s.substring(i), s, set)) {
                    return true;
                }
            }
        return false;
    }


    // O(2^n) - time, O(n) - space (recursion tree depth). T(n) = T(n-1) + T(n-2) + T(n-3) + .... + T(1)
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

    // O(n^2) - time, O(n) - space. T(n) = T(n-1) + n - 2
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
}
