package string.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * M
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 * <p>
 * Example 1:
 * Input:
 * s: "cbaebabacd" p: "abc"
 * Output:
 * [0, 6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * <p>
 * Example 2:
 * Input:
 * s: "abab" p: "ab"
 * Output:
 * [0, 1, 2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInString_438 {

    public static void main(String[] args) {
        FindAllAnagramsInString_438 s = new FindAllAnagramsInString_438();
        System.out.println(s.findAnagrams3("cbaebabacd", "abc")); //[0,6]
        System.out.println(s.findAnagrams3("aaaaaaaaaa", "aaaaaaaaaaaaa")); //[0,6]
        System.out.println(s.findAnagrams3("", "a"));//[0,1,2]
        System.out.println(s.findAnagrams3("abab", "ab"));//[0,1,2]
    }

    // O(n + m) - time, O(1) - space (with rehashing)
    public List<Integer> findAnagrams3(String s, String p) {
        int sL = s.length(), pL = p.length();
        if (sL < pL) {
            return new ArrayList();
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        for (char ch : p.toCharArray()) {
            pCount[(ch - 'a')]++;
        }

        List<Integer> output = new ArrayList();
        for (int i = 0; i < sL; ++i) {
            sCount[(s.charAt(i) - 'a')]++;
            if (i >= pL) {
                sCount[(s.charAt(i - pL) - 'a')]--;
            }
            if (Arrays.equals(pCount, sCount)) {
                output.add(i - pL + 1);
            }
        }
        return output;
    }

    // O(n+m) - time, O(1) - space
    public List<Integer> findAnagrams2(String s, String p) {
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || s.length() < p.length()) {
            return new ArrayList<>();
        }
        char[] patternHash = hashAsArray(p, 0, p.length());
        char[] textHash = hashAsArray(s, 0, p.length());
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= s.length() - p.length(); i++) {
            if (isAnagram(patternHash, textHash)) {
                list.add(i - 1);
            }
            rehash(s, textHash, i, i + p.length() - 1);
            if (i == s.length() - p.length() && isAnagram(patternHash, textHash)) {
                list.add(i);
            }
        }

        return list;
    }

    private void rehash(String text, char[] textHash, int start, int end) {
        textHash[text.charAt(start - 1) - 'a']--;
        textHash[text.charAt(end) - 'a']++;
    }

    private boolean isAnagram(char[] patternHash, char[] textHash) {
        for (int i = 0; i < 26; i++) {
            if (patternHash[i] != textHash[i]) {
                return false;
            }
        }
        return true;
    }

    private char[] hashAsArray(String text, int start, int end) {
        char[] chars = new char[26];
        for (int i = start; i < end; i++) {
            chars[text.charAt(i) - 'a']++;
        }

        return chars;
    }

    // O(n*m) - time, O(1) - space (no rehash)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        String patternHash = hash(p, 0, p.length());
        for (int i = 0; i <= s.length() - p.length(); i++) {
            String stringHash = hash(s, i, p.length());
            if (patternHash.equals(stringHash)) {
                list.add(i);
            }
        }

        return list;
    }

    private String hash(String p, int start, int end) {
        int[] chars = new int[26];
        for (int i = start; i < start + end; i++) {
            chars[p.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder(26);
        for (int i = 0; i < 26; i++) {
            sb.append(chars[i]);
        }

        return sb.toString();
    }

}
