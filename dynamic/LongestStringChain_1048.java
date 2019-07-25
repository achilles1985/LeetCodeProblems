package dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** M
 Given a list of words, each word consists of English lowercase letters.
 Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.  For example, "abc" is a predecessor of "abac".
 A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >= 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of word_3, and so on.
 Return the longest possible length of a word chain with words chosen from the given list of words.

 Example 1:
 Input: ["a","b","ba","bca","bda","bdca"]
 Output: 4
 Explanation: one of the longest word chain is "a","ba","bda","bdca".

 Note:
 1 <= words.length <= 1000
 1 <= words[i].length <= 16
 words[i] only consists of English lowercase letters.
 */
public class LongestStringChain_1048 {

    public static void main(String[] args) {
        LongestStringChain_1048 s = new LongestStringChain_1048();
        System.out.println(s.longestStrChain(new String[] {"a","b","ba","bca","bda","bdca"})); // 4
        System.out.println(s.longestStrChainTopDown(new String[] {"a","b","ba","bca","bda","bdca"})); // 4
    }

    // O(2^n) - time
    public int longestStrChain(String[] words) {
        int n = words.length, max = 0;
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<=n-1; i++) {
            map.put(words[i], i);
        }
        for (int i=0; i<=n-1; i++) {
            max = Math.max(max, longestStrChainUtils(words, i, map));
        }
        return max;
    }

    // O(n) - time, space
    public int longestStrChainTopDown(String[] words) {
        int n = words.length, max = 0;
        int[] cache = new int[n];
        Arrays.fill(cache, 1);
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<=n-1; i++) {
            map.put(words[i], i);
        }
        for (int i=0; i<=n-1; i++) {
            max = Math.max(max, longestStrChainTopDownUtils(words, i, map, cache));
        }
        return max;
    }

    private int longestStrChainUtils(String[] words, int i, Map<String, Integer> map) {
        if (words[i].length() == 1) {
            return 1;
        }

        int localMax = 1;
        for(int j=0; j<words[i].length(); j++) {
            StringBuilder sb = new StringBuilder(words[i]);
            sb.deleteCharAt(j);
            String ss = sb.toString();
            if(map.containsKey(ss)) {
                localMax = 1+longestStrChainUtils(words, map.get(ss), map);
            }
        }

        return localMax;
    }

    private int longestStrChainTopDownUtils(String[] words, int i, Map<String, Integer> map, int[] cache) {
        if(cache[i] != 1) {
            return cache[i];
        }

        for(int j=0; j<words[i].length(); j++) {
            StringBuilder sb = new StringBuilder(words[i]);
            sb.deleteCharAt(j);
            String ss = sb.toString();
            if(map.containsKey(ss)) {
                cache[i] = Math.max(cache[i], 1 + longestStrChainTopDownUtils(words, map.get(ss), map, cache));
            }
        }

        return cache[i];
    }
}
