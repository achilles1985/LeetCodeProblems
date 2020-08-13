package string.easy;

/**
 * E
 * Implement strStr().
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * <p>
 * Example 2:
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * <p>
 * Clarification:
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr
 * () and Java's indexOf().
 */
public class ImplementStrStr_28 {

    public static void main(String[] args) {
        ImplementStrStr_28 s = new ImplementStrStr_28();
        System.out.println(s.strStr("a", "a")); //0
        System.out.println(s.strStr("", "a")); //-1
        System.out.println(s.strStr("hello", "ll")); //2
        System.out.println(s.strStr("aaaaa", "bba")); //-1
        System.out.println(s.strStr("aaaaa", "")); //0
    }

    // O((n-m)*m) - time (worse case); O(n) - best case, O(1) - space
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.isEmpty()) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)
                    && i + needle.length() <= haystack.length()
                    && haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // O((n-m)*m) - time, O(1) - space. Compare each substring with needle
    public int strStr2(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // O(n) - time, O(1) - space when applying Robin-Karp algorithm. TODO

}
