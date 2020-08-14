package string.easy;

/** E
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last
 * word in the string.
 *
 * If the last word does not exist, return 0.
 * * Note: A word is defined as a character sequence consists of non-space characters only.
 *
 * Example:
 * Input: "Hello World"
 * Output: 5
 */
public class LengthOfLastWord_58 {

    public static void main(String[] args) {
        LengthOfLastWord_58 s = new LengthOfLastWord_58();
        System.out.println(s.lengthOfLastWord2("Hello world")); // 5
        System.out.println(s.lengthOfLastWord2("Hello world my")); // 2
        System.out.println(s.lengthOfLastWord2(" Hello world my  ")); // 2
        System.out.println(s.lengthOfLastWord2("Hello")); // 5
        System.out.println(s.lengthOfLastWord2("Hellooooooo me")); // 2
        System.out.println(s.lengthOfLastWord2("a")); // 1
    }

    // O(n) - time, O(1) - space, without trim()
    public int lengthOfLastWord2(String s) {
        int count = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            if (Character.isAlphabetic(s.charAt(i))) {
                count++;
            } else {
                if (count > 0) {
                    return count;
                }
            }
        }

        return count;
    }

    // O(n) - time, O(1) - space
    public int lengthOfLastWord(String s) {
        int count = 0;
        String trimmed = s.trim();
        for (int i = trimmed.length() - 1; i >= 0; i--) {
            if (Character.isAlphabetic(trimmed.charAt(i))) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }
}
