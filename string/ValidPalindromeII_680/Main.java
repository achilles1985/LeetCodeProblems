package string.ValidPalindromeII_680;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.validPalindrome("aba")); // true
        System.out.println(s.validPalindrome("abca")); // true
        System.out.println(s.validPalindrome("abcedba")); // false
        System.out.println(s.validPalindrome("abceeba")); // true
        System.out.println(s.validPalindrome("abcedkeba")); // false
    }
}
