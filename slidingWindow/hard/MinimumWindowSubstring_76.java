package slidingWindow.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * H [marked]
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 * <p>
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 */
public class MinimumWindowSubstring_76 {

    public static void main(String[] args) {
        MinimumWindowSubstring_76 s = new MinimumWindowSubstring_76();
        System.out.println(s.minWindow3("bba", "ab")); //ba
        System.out.println(s.minWindow3("aa", "aa")); //aa
        System.out.println(s.minWindow3("a", "a")); //a
        System.out.println(s.minWindow3("abaacbab", "abc")); //acb
        System.out.println(s.minWindow3("ADOBECODEBANC", "ABC")); //BANC

        System.out.println(s.minWindow2("aa", "aa")); //aa
        System.out.println(s.minWindow2("a", "a")); //a

        System.out.println(s.minWindow("abaacbab", "abc")); //acb
        System.out.println(s.minWindow("ADOBECODEBANC", "ABC")); //BANC

        System.out.println(s.minWindowBF2("ADOBECODEBANC", "ABC")); //BANC
        System.out.println(s.minWindowBF2("abaacbab", "abc")); //acb
        System.out.println(s.minWindowBF2("bba", "ab")); //ba
    }

    // O(n^3) - time, O(m-n) - space
    public String minWindowBF(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>(); // if only letters, it's constant space
        for (int k = 0; k < t.length(); k++) {
            char c = t.charAt(k);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        String minStr = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + t.length(); j <= s.length(); j++) {
                String sub = s.substring(i, j); // O(m-n) space
                if (match(sub, t, map)) {
                    if (sub.length() < min) {
                        min = sub.length();
                        minStr = sub;
                    }
                }
            }
        }
        return minStr;
    }

    // O(s^2) - time, O(t) - space
    public String minWindowBF2(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int min = Integer.MAX_VALUE;
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            Map<Character, Integer> clone = new HashMap<>(map);
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);
                sb.append(c);
                if (clone.containsKey(c) && clone.get(c) > 0) {
                    clone.put(c, clone.get(c) - 1);
                    count++;
                }
                if (count == t.length()) {
                    if (sb.length() < min) {
                        min = sb.length();
                        result = sb.toString();
                    }
                }
            }
        }
        return result;
    }

    // O(t + s) - time, O(t + s) - space, s - since we create result each time when encounter currLength < min
    public String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c: t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        int tCount = tMap.size(); // number of unique chars in t

        int min = Integer.MAX_VALUE;
        String ans = "";
        int sCount = 0;
        Map<Character, Integer> sMap = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (tMap.containsKey(c)) {
                sMap.put(c, sMap.getOrDefault(c, 0) + 1);
                if (tMap.containsKey(c) && sMap.get(c).intValue() == tMap.get(c).intValue()) {
                    sCount++;
                }
            }
            while (left <= right && tCount == sCount) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    ans = s.substring(left, right+1);
                }
                char cc = s.charAt(left);
                if (tMap.containsKey(cc)) {
                    sMap.put(cc, sMap.getOrDefault(cc, 0) - 1);
                    if (sMap.get(cc) < tMap.get(cc)) {
                        sCount--;
                    }
                }
                left++;
            }
        }

        return ans;
    }

    public String minWindow2(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c: t.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int counter = t.length();
        int min = Integer.MAX_VALUE;
        String result = "";
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (frequency.containsKey(rightChar)) {
                frequency.put(rightChar, frequency.getOrDefault(rightChar, 0) - 1);
                if (frequency.get(rightChar) >= 0) {
                    counter--;
                }
            }
            while (counter == 0 && left <= right) {
                int currLength = right - left + 1;
                if (currLength < min) {
                    min = currLength;
                    result = s.substring(left, right + 1);
                }
                char leftChar = s.charAt(left);
                if (frequency.containsKey(leftChar)) {
                    frequency.put(leftChar, frequency.get(leftChar) + 1);
                    if (frequency.get(leftChar) >= 1) {
                        counter++;
                    }
                }
                left++;
            }
            right++;
        }

        return result;
    }

    public String minWindow3(String s, String t) {
        if (s == null || s.isEmpty() || t == null || t.isEmpty()) {
            return "";
        }
        Map<Character, Integer> tMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;
        int count = t.length();
        int min = Integer.MAX_VALUE;
        String result = "";
        while (right < s.length()) {
            char rightC = s.charAt(right);
            if (tMap.containsKey(rightC)) {
                tMap.put(rightC, tMap.get(rightC) - 1);
                if (tMap.get(rightC) >= 0) {
                    count--;
                }
            }
            while (count == 0 && left <= right) {
                int curLength = right - left + 1;
                if (curLength < min) {
                    min = curLength;
                    result = s.substring(left, right+1);
                }
                char leftC = s.charAt(left);
                if (tMap.containsKey(leftC)) {
                    tMap.put(leftC, tMap.get(leftC) + 1);
                    if (tMap.get(leftC) > 0) {
                        count++;
                    }
                }
                left++;
            }
            right++;
        }
        return result;
    }

    private boolean match(String str, String t, Map<Character, Integer> tMap) {
        Map<Character, Integer> sMap = new HashMap<>(); // if only letters, it's constant space
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        if (sMap.size() < tMap.size()) {
            return false;
        }
        int count = 0;
        for (Character key: tMap.keySet()) {
            if (sMap.containsKey(key) && sMap.get(key) >= tMap.get(key)) {
                count++;
            }
        }
        return count == tMap.size();
    }
}
