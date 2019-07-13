package string.LongestCommonPrefix_14;

import utils.Trie;

import java.util.HashMap;
import java.util.Map;

/**
 Write a function to find the longest common prefix string amongst an array of strings.
 If there is no common prefix, return an empty string "".

 Example 1:
 Input: ["flower","flow","flight"]
 Output: "fl"

 Example 2:
 Input: ["dog","racecar","car"]
 Output: ""
 Explanation: There is no common prefix among the input strings.

 Note:
 All given inputs are in lowercase letters a-z.
 */
public class Solution {

    // O(numberOfStrings*minStrLength) - time, O(numberOfStrings*minStrLength) - space
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0] != null ? strs[0] : "";
        }
        // find min length of the word
        int minLength = Integer.MAX_VALUE;
        for (String word: strs) {
            if (word == null || word.isEmpty()) {
                return "";
            }
            minLength = Math.min(word.length(), minLength);
        }

        Map<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= minLength; i++) {
            for (String word: strs) {
                String key = word.substring(0, i);
                if (!key.isEmpty()) {
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
            }
        }

        String maxKey = "";
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            if (entry.getValue() == strs.length) {
                if (entry.getKey().length() > maxKey.length()) {
                    maxKey = entry.getKey();
                }
            }
        }

        return maxKey;
    }

    // O(n*m) - time, O(1) - space, https://leetcode.com/problems/longest-common-prefix/solution/
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    // using Trie, O(n*m) - time to populate trie, O(s) - time to search, where s - length of the prefix
    public String longestCommonPrefix3(String[] strs) {
        if (strs == null && strs.length == 0) {
            return "";
        }
        Trie trie = new Trie();
        for (String word: strs) {
            trie.insert(word);
        }

        return trie.longestCommonPrefix(strs[0]);
    }
}
