package dynamic.RegularExpressionMatching_10;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.isMatch("aab", "c*a*b")); // true
        System.out.println(s.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(s.isMatch("ab", ".*")); // true
        System.out.println(s.isMatch("aa", "a*")); // true

        System.out.println(s.isMatch("Tushar","Tushar"));
        System.out.println(s.isMatch("Tusha","Tushar*a*b*"));
        System.out.println(s.isMatch("","a*b*"));
        System.out.println(s.isMatch("abbbbccc","a*ab*bbbbc*"));
        System.out.println(s.isMatch("abbbbccc","aa*bbb*bbbc*"));
        System.out.println(s.isMatch("abbbbccc",".*bcc"));
        System.out.println(s.isMatch("abbbbccc",".*bcc*"));
        System.out.println(s.isMatch("abbbbccc",".*bcc*"));
        System.out.println(s.isMatch("aaa","ab*a*c*a"));
    }
}
