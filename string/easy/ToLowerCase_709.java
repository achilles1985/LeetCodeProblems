package string.easy;

/**
 * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
 *
 * Example 1:
 * Input: "Hello"
 * Output: "hello"
 *
 * Example 2:
 * Input: "here"
 * Output: "here"
 *
 * Example 3:
 * Input: "LOVELY"
 * Output: "lovely"
 */
public class ToLowerCase_709 {

    public static void main(String[] args) {
        ToLowerCase_709 s = new ToLowerCase_709();
        System.out.println(s.toLowerCase("Hello"));
        System.out.println(s.toLowerCase("here"));
        System.out.println(s.toLowerCase("LOVELY"));
        System.out.println(s.toLowerCase("LO&ELY"));
    }

    // O(n) - time, O(1) - space
    public String toLowerCase(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char symbol = chars[i];
            if (symbol >= 'A' && symbol <= 'Z') {
                chars[i] = (char) (symbol + ('a' - 'A'));
            }
        }

        return String.valueOf(chars);
    }
}
