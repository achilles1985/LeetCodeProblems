package dynamic.medium;

import java.util.*;

// https://leetcode.com/problems/word-break/

/** M [marked]
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 determine the total number of ways to form a target word.

 Example 3:
 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 */
public class WordBreak_II_140 {

    public static void main(String[] args) {
        WordBreak_II_140 s = new WordBreak_II_140();
        System.out.println(s.wordBreak("catsanddog", (Arrays.asList("cat","cats","and","sand","dog")))); // ["cats and dog","cat sand dog"]
        System.out.println(s.wordBreak("pineapplepenapple", (Arrays.asList("apple","pen","applepen","pine","pineapple")))); // ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
        System.out.println(s.wordBreak("catsandog", (Arrays.asList("cats","dog","sand","and","cat")))); // []
    }

    // O(?) - time, space
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<String, List<String>> cache = new HashMap<>();

        return helper(s, set, cache);
    }

    public List<String> helper(String s, Set<String> dict, Map<String, List<String>> cache) {
        if (cache.containsKey(s)) {
            return cache.get(s);
        }
        List<String> result = new LinkedList<>();
        if (dict.contains(s)) {
            result.add(s);
        }
        for (int i = 1; i < s.length(); i++) {
            String left = s.substring(0,i);
            String right = s.substring(i);
            if (dict.contains(left) && containsSuffix(dict, right)) {
                for (String ss : helper(right, dict, cache)) {
                    result.add(left + " " + ss);
                }
            }
        }
        cache.put(s, result);

        return result;
    }

    private boolean containsSuffix(Set<String> dict, String str) {
        for (int i = 0; i < str.length(); i++) {
            if (dict.contains(str.substring(i))) {
                return true;
            }
        }
        return false;
    }

}
