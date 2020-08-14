package string.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**M [marked]
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
    public int numMatchingSubseq(String S, String[] words) {
        if (S == null || S.isEmpty()) {
            return 0;
        }
        Map<Character, List<String>> map = new HashMap<>();
        for (String word: words) {
            map.computeIfAbsent(word.charAt(0), w -> new ArrayList<>()).add(word);
        }
        int counter = 0;
        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(S.charAt(i))) {
                List<String> values = map.get(S.charAt(i));
                map.remove(S.charAt(i));
                for (int j = 0; j < values.size(); j++) {
                    String value = values.get(j);
                    if (value.length() == 1) {
                        counter++;
                    } else {
                        map.computeIfAbsent(value.charAt(1), v -> new ArrayList<>()).add(value.substring(1));
                    }
                }
            }
        }
        return counter;
    }

}
