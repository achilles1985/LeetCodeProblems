package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** H
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 Note:
 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.

 Example 1:
 Input:
 s = "catsanddog"
 wordDict = ["cat", "cats", "and", "sand", "dog"]
 Output:
 [
 "cats and dog",
 "cat sand dog"
 ]

 Example 2:
 Input:
 s = "pineapplepenapple"
 wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 Output:
 [
 "pine apple pen apple",
 "pineapple pen apple",
 "pine applepen apple"
 ]
 Explanation: Note that you are allowed to reuse a dictionary word.

 Example 3:
 Input:
 s = "catsandog"
 wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output:
 []
 */
public class WordBreak_II {

    public static void main(String[] args) {
        WordBreak_II s = new WordBreak_II();
        System.out.println(s.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(s.wordBreak("catsandog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
    }

    // O(n^n) - time, O(n) - space
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        wordBreak(s, wordDict, "", res);

        return res;
    }

    private void wordBreak(String str, List<String> wordDict, String out, List<String> res) {
        if (str.length() == 0 && out.length() != 0) {
            res.add(out);
            return;
        }

        for (int i = 1; i <= str.length(); i++) {
            String prefix = str.substring(0, i);
            if (wordDict.contains(prefix)) {
                wordBreak(str.substring(i), wordDict, build(prefix, out), res);
            }
        }
    }

    private String build(String prefix, String out) {
        return out.isEmpty() ? prefix : out + " " + prefix;
    }
}
