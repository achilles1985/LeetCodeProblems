package dynamic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

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
        System.out.println(s.longestStrChain4(new String[] {"ba","a","b","bca","bda","bdca"})); // 4 (example 1)
        System.out.println(s.longestStrChainTopDown(new String[] {"a","b","ba","bca","bda","bdca"})); // 4

        System.out.println(s.longestStrChainBF(new String[] {"ba","a","b","bca","bda","bdca"})); // 4 (example 1)
        System.out.println(s.longestStrChainBF(new String[] {"ksqvsyq","ks","kss","czvh","zczpzvdhx","zczpzvh","zczpzvhx","zcpzvh","zczvh","gr","grukmj","ksqvsq","gruj","kssq","ksqsq","grukkmj","grukj","zczpzfvdhx","gru"})); // 7
    }

    // O(2^n) - time
    public int longestStrChainBF(String[] words) {
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

    // O(n^2) - time, space
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

    // BFS
    // O(n^2) - time, O(n) - space
    public int longestStrChain3(String[] words) {
        TreeMap<Integer, Set<String>> map = new TreeMap<>(Collections.reverseOrder());
        for (String word : words) {
            int len = word.length();
            if (!map.containsKey(len)) {
                map.put(len, new HashSet<>());
            }
            map.get(len).add(word);
        }

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int maxChain = 0;

        for (int len : map.keySet()) {
            Set<String> wordsWithCurrentLen = map.get(len);
            for (String word : wordsWithCurrentLen) {
                if (visited.contains(word)) {
                    continue;
                }
                visited.add(word);
                q.offer(word);
            }

            int chainLen = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                chainLen++;
                int currLevelWordLen = q.peek().length();
                Set<String> wordsWithPrevLen = map.get(currLevelWordLen - 1);
                if (wordsWithPrevLen == null || wordsWithPrevLen.size() == 0) {
                    break;
                }
                while (size > 0) {
                    String currStr = q.poll();
                    for (int i = 0; i < currStr.length(); i++) {
                        String cand = currStr.substring(0, i) + currStr.substring(i + 1);
                        if (wordsWithPrevLen.contains(cand) && !visited.contains(cand)) {
                            visited.add(cand);
                            q.offer(cand);
                        }
                    }
                    size--;
                }
            }
            maxChain = Math.max(maxChain, chainLen);
        }

        return maxChain;
    }

    // O(words.word.length) - time, space
    public int longestStrChain4(String[] words) {
        Map<String, Integer> dp = new HashMap<>();
        Arrays.sort(words, (a, b)->a.length() - b.length());
        int res = 0;
        for (String word : words) {
            int max = 0;
            for (int i = 0; i < word.length(); ++i) {
                String prev = word.substring(0, i) + word.substring(i + 1);
                max = Math.max(max, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(word, max);
            res = Math.max(res, max);
        }
        return res;
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

/*    // incorrect
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
    }*/
}
