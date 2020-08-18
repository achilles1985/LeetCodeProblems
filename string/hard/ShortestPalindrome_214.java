package string.hard;

/** H
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and
 * return the shortest palindrome you can find by performing this transformation.
 *
 * Example 1:
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 *
 * Example 2:
 * Input: "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome_214 {

    public static void main(String[] args) {
        ShortestPalindrome_214 s = new ShortestPalindrome_214();
        System.out.println(s.shortestPalindrome("abcdad")); //dadcbabcdad
        System.out.println(s.shortestPalindrome("abc")); //cbaabc
        System.out.println(s.shortestPalindrome("aacecaaa")); //aaacecaaa
        System.out.println(s.shortestPalindrome("abcd")); //dcbabcd
        System.out.println(s.shortestPalindrome("abaklm")); //mlkabaklm
    }

    // O(n) - time (Manacher's algo to find longest palindrome)

    // O(n^2) - time, O(n) - space, to store reverse string
    public String shortestPalindromeBF(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        String reverse = reverse(s);
        int size = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(0, size - i).equals(reverse.substring(i))) {
                return reverse.substring(0, i) + s;
            }
        }
        return "";
    }

    // O(n^2) - time, O(1) - space
    public String shortestPalindrome(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }
        int anchor = -1;
        for (int i = 0; i < s.length(); i++) {
            if (isPalidrom(s.substring(0, i+1))) {
                anchor = i;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = s.length()-1; i > anchor; i--) {
            sb.append(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    private String reverse(String s) {
        StringBuilder reversed = new StringBuilder(s.length());
        for (int i = s.length()-1; i >=0; i--) {
            reversed.append(s.charAt(i));
        }
        return reversed.toString();
    }

    private boolean isPalidrom(String str) {
        int i = 0;
        int j = str.length()-1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
