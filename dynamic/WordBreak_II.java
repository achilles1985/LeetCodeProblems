package dynamic;

import java.util.*;

/**
 * H
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * <p>
 * Example 1:
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * <p>
 * Example 2:
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * <p>
 * Example 3:
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreak_II {

    public static void main(String[] args) {
        WordBreak_II s = new WordBreak_II();
        System.out.println(s.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"))); //[cat sand dog, cats and dog]
        System.out.println(s.wordBreak("catsandog", Arrays.asList("cat", "cats", "and", "sand", "dog"))); //[]
        System.out.println(s.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"))); //[pine apple pen apple, pine applepen apple, pineapple pen apple]

        System.out.println(s.wordBreakTopDown("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"))); //[cat sand dog, cats and dog]
        System.out.println(s.wordBreakTopDown("catsandog", Arrays.asList("cat", "cats", "and", "sand", "dog"))); //[]
        System.out.println(s.wordBreakTopDown("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"))); //[pine apple pen apple, pine applepen apple, pineapple pen apple]

    }

    // O(n*2^n) - time, O(2^n) - space
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> unique = new HashSet<>(wordDict);
        List<String> res = new ArrayList<>();
        wordBreak(s, unique, "", res);

        return res;
    }

    private void wordBreak(String suffix, Set<String> wordDict, String out, List<String> res) {
        if (suffix.length() == 0 && out.length() != 0) {
            res.add(out);
            return;
        }

        for (int i = 1; i <= suffix.length(); i++) {
            String prefix = suffix.substring(0, i);
            if (wordDict.contains(prefix)) {
                wordBreak(suffix.substring(i), wordDict, build(prefix, out), res);
            }
        }
    }

    private String build(String prefix, String out) {
        return out.isEmpty() ? prefix : out + " " + prefix;
    }

    // O(n^3) - time. Size of recursion tree can go up to n2n^2n2. The creation of list takes nnn time.
    // O(n^3) - space. The depth of the recursion tree can go up to nnn and each activation record can contains a string list of size nnn.
    public List<String> wordBreakTopDown(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<String, List<String>> cache = new HashMap();
        return wordBreakTopDownHelper(s, wordDict, cache, set);
    }

    private List<String> wordBreakTopDownHelper(String s, List<String> wordDict, Map<String, List<String>> cache, Set<String> set) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        List l = new ArrayList<>();
        if (s.length() == 0) {
            l.add("");
            return l;
        }
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.substring(0, i + 1))) {
                List<String> count = wordBreakTopDownHelper(s.substring(i + 1), wordDict, cache, set);
                for (String ls : count) {
                    String temp = s.substring(0, i + 1);
                    l.add(temp + (ls.length() == 0 ? "" : " ") + ls);
                }
            }
        }
        cache.put(s, l);
        return l;
    }

}
