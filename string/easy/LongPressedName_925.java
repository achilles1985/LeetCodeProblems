package string.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Your friend is typing his name into a keyboard.
 * Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 * You examine the typed characters of the keyboard.
 * Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 * Example 1:
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 *
 * Example 2:
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 *
 * Example 3:
 * Input: name = "leelee", typed = "lleeelee"
 * Output: true
 *
 * Example 4:
 * Input: name = "laiden", typed = "laiden"
 * Output: true
 * Explanation: It's not necessary to long press any character.
 *
 * Constraints:
 *     1 <= name.length <= 1000
 *     1 <= typed.length <= 1000
 *     The characters of name and typed are lowercase letters.
 */
public class LongPressedName_925 {

    public static void main(String[] args) {
        LongPressedName_925 s = new LongPressedName_925();
        System.out.println(s.isLongPressedName2("kikcxmvzi", "kiikcxxmmvvzz")); // false
        System.out.println(s.isLongPressedName2("abcd", "abce")); // false
        System.out.println(s.isLongPressedName2("leelee", "lleeelee")); // true
        System.out.println(s.isLongPressedName2("alex", "aaleeeex")); // true
        System.out.println(s.isLongPressedName2("saeed", "ssaaedd")); // false
        System.out.println(s.isLongPressedName2("laiden", "laiden")); // true
        System.out.println(s.isLongPressedName2("xnhtq", "xhhttqq")); // false
    }

    // O(n + m) - time, O(1) - space
    public boolean isLongPressedName2(String name, String typed) {
        if (name == null || typed == null) {
            return false;
        }
        if (typed.length() < name.length()) {
            return false;
        }
        if (name.equals(typed)) {
            return true;
        }
        int p1 = 0;
        int p2 = 0;
        while (p1 < name.length() && p2 < typed.length()) {
            char char1 = name.charAt(p1);
            char char2 = typed.charAt(p2);
            if (char1 != char2) {
                return false;
            }
            int count1 = 0;
            int count2 = 0;
            p1++;
            p2++;
            while (p1 < name.length() && name.charAt(p1) == char1) {
                count1++;
                p1++;
            }
            while (p2 < typed.length() && typed.charAt(p2) == char2) {
                count2++;
                p2++;
            }
            if (count2 < count1) {
                return false;
            }
        }
        return p1 == name.length() && p2 == typed.length();
    }

    // O(n + m) - time, where m, n - the lengths of 'name' nad 'typed', O(m + n) - space
    public boolean isLongPressedName(String name, String typed) {
        List<Group> nameGroups = groupify(name);
        List<Group> typedGroups = groupify(typed);

        if (nameGroups.size() != typedGroups.size()) {
            return false;
        }
        for (int i = 0; i < nameGroups.size(); i++) {
            if (nameGroups.get(i).getSymbol() != typedGroups.get(i).getSymbol()) {
                return false;
            }
            if (nameGroups.get(i).getCounter() > typedGroups.get(i).getCounter()) {
                return false;
            }
        }

        return true;
    }

    private List<Group> groupify(String input) {
        char[] chars = input.toCharArray();
        List<Group> groups = new ArrayList<>();

        int counter = 1;
        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length-1 || chars[i] != chars[i+1]) {
                groups.add(new Group(chars[i], counter));
                counter = 1;
                continue;
            }
            counter++;
        }

        return groups;
    }

    private static class Group {
        char symbol;
        int counter;

        public Group(char symbol, int counter) {
            this.symbol = symbol;
            this.counter = counter;
        }

        public char getSymbol() {
            return symbol;
        }

        public int getCounter() {
            return counter;
        }
    }

}
