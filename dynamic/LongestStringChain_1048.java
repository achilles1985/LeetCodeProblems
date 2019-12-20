package dynamic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/** M
 Given a list of words, each word consists of English lowercase letters.
 Let's say word1 is a predecessor of word2 if and only if we can add exactly one letter anywhere in word1 to make it equal to word2.
 For example, "abc" is a predecessor of "abac".
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
/*
Mistakes:
    1. The chain can be not consequitive (example 1)
 */
public class LongestStringChain_1048 {

    public static void main(String[] args) {
        LongestStringChain_1048 s = new LongestStringChain_1048();
        System.out.println(s.longestStrChain2(new String[] {"ba","a","b","bca","bda","bdca"})); // 4 (example 1)
        System.out.println(s.longestStrChainTopDown(new String[] {"a","b","ba","bca","bda","bdca"})); // 4

        System.out.println(s.longestStrChain2(new String[] {"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"})); // 7
    }

    // O(2^n) - time
    public int longestStrChain(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++) {
            map.put(words[i], i);
        }
        int max = 0;
        for (int i=0; i<words.length; i++) {
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

    // incorrect
    public int longestStrChain2(String[] words) {
        Arrays.sort(words, (w1, w2) -> w1.length() - w2.length());
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            int localMax = 1;
            for (int j = i+1; j < words.length; j++) {
                if (words[i].length() + 1 == words[j].length()) {
                    if (isValid(words[i], words[j])) {
                        i = j;
                        localMax++;
                    }
                }
            }
            max = Math.max(max, localMax);
        }
        return max;
    }

    private boolean isValid(String current, String next) {
        for (int i = 0; i < current.length(); i++) {
            String prefix = current.substring(0, i);
            String suffix = current.substring(i);
            for (int j = 0; j < 26; j++) {
                String word = prefix + (char)(j + 'a') + suffix;
                if (word.equals(next)) {
                    return true;
                }
            }
        }
        return false;
    }
}
