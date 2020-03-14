package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**M
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
public class NumberOfMatchingSubsequences_792 {

    public static void main(String[] args) {
        NumberOfMatchingSubsequences_792 s = new NumberOfMatchingSubsequences_792();
        System.out.println(s.numMatchingSubseq("abcde", new String[]{"a", "bb", "acd", "ace"})); //3

        System.out.println(s.numMatchingSubseq2("abcde", new String[]{"a", "bb", "acd", "ace"})); //3
        System.out.println(s.numMatchingSubseq2("dsahjpjauf", new String[]{"ahjpjau","ja","ahbwzgqnuk","tnmlanowax"})); //2
    }

    // O(words*S) - time, O(1) - space
    public int numMatchingSubseq(String S, String[] words) {
        if (S == null || S.length() == 0 || words == null || words.length == 0) {
            return 0;
        }
        int result = 0;
        for (String word: words) {
            if (match(S, word)) {
                result++;
            }
        }
        return result;
    }

    // O(words + S) - time, O(words) - space
    public int numMatchingSubseq2(String S, String[] words) {
        if (S == null || S.length() == 0 || words == null || words.length == 0) {
            return 0;
        }
        int result = 0;
        List<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; i++) {
            heads[i] = new ArrayList<Node>();
        }
        for (String word: words) {
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        }
        for (char c: S.toCharArray()) {
            List<Node> nodes = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<>();
            for (int j = 0; j < nodes.size(); j++) {
                Node node = nodes.get(j);
                node.index++;
                if (node.index == node.word.length()) {
                    result++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
        }
        return result;
    }

    private boolean match(String s, String word) {
        int i = 0, j = 0;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                i++;
                j++;
            } else {
                i++;
            }
        }
        return j == word.length();
    }

    private static class Node {
        private String word;
        private int index;

        public Node(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
}
