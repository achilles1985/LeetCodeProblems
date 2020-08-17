package algorithmstechniqes;

/**
 * Longest Palindromic Substring O(n) - time
 */
public class ManachersAlgorithmLongestPalindromicSubstring {

    public static void main(String[] args) {
        ManachersAlgorithmLongestPalindromicSubstring s = new ManachersAlgorithmLongestPalindromicSubstring();
        System.out.println(s.longestPalindrome("abcdcba"));
        System.out.println(s.longestPalindrome("abcrotornjk"));
        System.out.println(s.longestPalindrome("aaaabbbbaa"));
    }

    public String longestPalindrome(String s) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0) {
            return "";
        }
        int len = s.length();
        int clen = 2 * len + 3;
        char[] c = new char[clen];
        c[0] = '@';
        c[1] = '#';
        c[clen - 1] = '$';
        int k = 2;
        for (char ch : s.toCharArray()) {
            c[k++] = ch;
            c[k++] = '#';
        }
        int[] p = new int[clen];
        int right = 1, center = 1, maxcenter = 0, maxp = 0;
        for (int i = 2; i < clen - 1; i++) {
            if (i < right) {
                p[i] = Math.min(right - i, p[2 * center - i]);
            }
            while (c[i + (1 + p[i])] == c[i - (1 + p[i])]) {
                p[i]++;
            }
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
            if (p[i] > maxp) {
                maxcenter = i;
                maxp = p[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxcenter - maxp + 1; i < maxcenter + maxp; i += 2) {
            sb.append(c[i]);
        }
        return sb.toString();
    }
}
