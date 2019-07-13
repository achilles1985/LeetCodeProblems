package string.ImplementStrStr_28;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.strStr("mississippi", "pi")); //2
        System.out.println(s.strStr("abc", "abc")); //2
        System.out.println(s.strStr("a", "a")); //2
        System.out.println(s.strStr("hello", "ll")); //2
        System.out.println(s.strStr("aaaaa", "bba")); //-1
        System.out.println(s.strStr("abcdef", "cdg")); // -1
        System.out.println(s.strStr("abcdef", "cde")); // 2
    }
}
