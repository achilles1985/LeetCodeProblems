package string.BuddyStrings_859;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.buddyStrings("ab", "ba")); // true
        System.out.println(s.buddyStrings("aaabaac", "aaacaab")); // true
        System.out.println(s.buddyStrings("", "aa")); // false
        System.out.println(s.buddyStrings("ab", "ab")); // true
        System.out.println(s.buddyStrings("aabaac", "aackab")); // false
        System.out.println(s.buddyStrings("abc", "acd")); // false
    }
}
