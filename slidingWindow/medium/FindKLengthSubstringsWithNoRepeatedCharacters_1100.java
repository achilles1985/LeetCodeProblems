package slidingWindow.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * M [marked]
 * Given a string S, return the number of substrings of length K with no repeated characters.
 * <p>
 * Example 1:
 * Input: S = "havefunonleetcode", K = 5
 * Output: 6
 * Explanation:
 * There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
 * <p>
 * Example 2:
 * Input: S = "home", K = 5
 * Output: 0
 * Explanation:
 * Notice K can be larger than the length of S. In this case is not possible to find any substring.
 * <p>
 * Note:
 * 1 <= S.length <= 10^4
 * All characters of S are lowercase English letters.
 * 1 <= K <= 10^4
 */
public class FindKLengthSubstringsWithNoRepeatedCharacters_1100 {

    public static void main(String[] args) {
        FindKLengthSubstringsWithNoRepeatedCharacters_1100 s = new FindKLengthSubstringsWithNoRepeatedCharacters_1100();
        System.out.println(s.numKLenSubstrNoRepeats2("havefunonleetcode", 5)); //6
        System.out.println(s.numKLenSubstrNoRepeats("home", 5)); //0
    }

    // O((n-k)*k) - time, O(1) - space
    public int numKLenSubstrNoRepeatsBF(String S, int K) {
        if (K > S.length()) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i <= S.length() - K; i++) {
            String sub = S.substring(i, i + K);
            if (allUnique(sub)) {
                count++;
            }
        }
        return count;
    }

    // O(n) - time, O(1) - space
    public int numKLenSubstrNoRepeats2(String S, int K) {
        Set<Character> set = new HashSet<>();
        int count = 0;
        for (int left = 0, right = 0; right < S.length(); right++) {
            while (set.contains(S.charAt(right)) || set.size() > K) {
                set.remove(S.charAt(left++));
            }
            set.add(S.charAt(right));
            if (set.size() == K) {
                count++;
                set.remove(S.charAt(left++));
            }
        }
        return count;
    }

    public int numKLenSubstrNoRepeats(String S, int K) {
        if (K > S.length()) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < K; i++) {
            char key = S.charAt(i);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int count = 0;
        for (int i = K; i < S.length(); i++) {
            char toRemove = S.charAt(i - K);
            char toAdd = S.charAt(i);
            map.put(toRemove, map.getOrDefault(toRemove, 0) - 1);
            map.put(toAdd, map.getOrDefault(toAdd, 0) + 1);
            if (map.get(toAdd) == 1) {
                count++;
            }
        }

        return count;
    }

    private boolean allUnique(String sub) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < sub.length(); i++) {
            if (set.contains(sub.charAt(i))) {
                return false;
            }
            set.add(sub.charAt(i));
        }
        return true;
    }
}
