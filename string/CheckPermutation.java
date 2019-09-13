package string;

/** E
 Given two strings, write a method to decide if one is a permutation of the other.
 */
public class CheckPermutation {

    public static void main(String[] args) {
        CheckPermutation s = new CheckPermutation();
        System.out.println(s.isPermutation("god", "dog")); //true
        System.out.println(s.isPermutation("rotor", "motor")); //false
    }

    // Assumed, there are no duplicates and chars from a-z only, if not we need to ask whether str is ASCII or Unicode. if it's unicode, then [256].
    // O(n+m) - time, O(1) - space
    public boolean isPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        boolean[] chars = new boolean[26];
        for (int i = 0; i < s1.length(); i++) {
            chars[s1.charAt(i)-'a'] = true;
        }
        for (int i = 0; i < s2.length(); i++) {
            if (!chars[s2.charAt(i)-'a']) {
                return false;
            }
        }
        return true;
    }
}