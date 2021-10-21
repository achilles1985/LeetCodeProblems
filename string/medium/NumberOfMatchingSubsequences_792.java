package string.medium;

import java.util.*;

/**M [marked] (one path via string, one simultaneous path via entries in array of words)
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 */
//https://leetcode.com/problems/number-of-matching-subsequences/discuss/117634/Efficient-and-simple-go-through-words-in-parallel-with-explanation/
public class NumberOfMatchingSubsequences_792 {

    public static void main(String[] args) {
        NumberOfMatchingSubsequences_792 s = new NumberOfMatchingSubsequences_792();
        System.out.println(s.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"})); //3
        System.out.println(s.numMatchingSubseq("dsahjpjauf", new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"})); //2
    }

    // O(words * S) - time, O(1) - space
    public int numMatchingSubseqBF(String S, String[] words) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (String word: words) {
            int i = 0;
            int j = 0;
            while (i < word.length() && j < S.length()) {
                if (word.charAt(i) == S.charAt(j)) {
                    i++;
                }
                j++;
            }
            if (i == word.length()) {
                count++;
            }
        }
        return count;
    }

    // O(S + words) - time, O(words) - space
    public int numMatchingSubseq(String s, String[] words) {
        if (s.isEmpty() || words.length == 0) {
            return 0;
        }
        Map<Character, List<String>> map = new HashMap<>();
        for (String word: words) {
            map.computeIfAbsent(word.charAt(0), k -> new ArrayList<>()).add(word);
        }
        int count = 0;
        for (char c: s.toCharArray()) {
            List<String> suffixes = map.getOrDefault(c, Collections.emptyList());
            map.remove(c);
            for (int i = 0; i < suffixes.size(); i++) { // for each because of ConcurrentModificationException
                String suffix = suffixes.get(i);
                if (suffix.length() == 1) {
                    count++;
                } else {
                    String newSuffix = suffix.substring(1);
                    map.computeIfAbsent(newSuffix.charAt(0), k -> new ArrayList<>()).add(newSuffix);
                }
            }
        }
        return count;
    }
}
