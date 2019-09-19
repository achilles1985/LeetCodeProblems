package string;

/** E
 * Implement strStr().
 *Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Example 1:
 *Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 * Example 2:
  Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 * Clarification:
   What should we return when needle is an empty string? This is a great question to ask during an interview.
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr
 * () and Java's indexOf().
 */
public class ImplementStrStr_28 {

    public static void main(String[] args) {
        ImplementStrStr_28 s = new ImplementStrStr_28();
        System.out.println(s.strStr("hello", "ll")); //2
        System.out.println(s.strStr("aaaaa", "bba")); //-1
        System.out.println(s.strStr("aaaaa", "")); //0
    }

    // O(n*m) - time, O(1) - space
    public int strStr2(String haystack, String needle) {
        if (needle == null || needle.isEmpty()) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i+needle.length()).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // O(n*m) - time, O(1) - space
    public int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            int count = 0;
            for (int j = i, k = 0; j < i + needle.length(); j++) {
                if (haystack.charAt(j) == needle.charAt(k)) {
                    count++;
                    k++;
                }
            }
            if (count == needle.length()) {
                return i;
            }
        }
        return -1;
    }
}
