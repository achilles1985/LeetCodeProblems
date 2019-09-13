package string;

/** E
 Implement an algorithm to determine if a string has all unique characters (letters, numbers, ,special characters).
 What if you cannot use additional data structures?
 */
public class IsUnique {

    public static void main(String[] args) {
        IsUnique s = new IsUnique();
        System.out.println(s.isUnique("abcdefg!,.")); // true
        System.out.println(s.isUnique("abccddef")); // false
        System.out.println(s.isUnique("a!!")); // false

        System.out.println(s.isUnique2("abcdefg")); // true
        System.out.println(s.isUnique2("abccddef")); // false
        System.out.println(s.isUnique2("aa")); // false
    }

    // O(n) - time, O(1) - space
    // [128] if str contains all in ACSII, if it's unicode, then [256]
    public boolean isUnique(String str) {
        // If str contains more then 128 chars, it must contain duplicates
        if (str.length() > 128) {
            return false;
        }
        boolean[] chars = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int character = str.charAt(i);
            if (chars[character]) {
                return false;
            }
            chars[character] = true;
        }
        return true;
    }

    // O(n) - time, O(1) - space
    // Let's assume, str contains only chars from a-z. Then we can use int as a 32bit vector.
    public boolean isUnique2(String str) {
        if (str.length() > 26) {
            return false;
        }
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }

        return true;
    }
}
