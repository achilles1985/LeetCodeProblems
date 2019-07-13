package dynamic.WildcardMatching_44;

public class
Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        //System.out.println(s.isMatch("baaabab", "ba*****ab")); // true
        System.out.println(s.isMatch("aa","a")); // false
        System.out.println(s.isMatch("aa","aa")); // true
        System.out.println(s.isMatch("aaa","aa")); // false
        System.out.println(s.isMatch("aa", "*")); // true
        System.out.println(s.isMatch("aa", "a*")); // true
        System.out.println(s.isMatch("ab", "?*")); //true
        System.out.println(s.isMatch("aab", "c*a*b")); // false
    }
}
