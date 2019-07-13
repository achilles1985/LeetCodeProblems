package string.LongPressedName_925;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isLongPressedName("alex", "aaleeeex")); // true
        System.out.println(s.isLongPressedName("saeed", "ssaaedd")); // false
        System.out.println(s.isLongPressedName("leelee", "lleeelee")); // true
        System.out.println(s.isLongPressedName("laiden", "laiden")); // true
        System.out.println(s.isLongPressedName("xnhtq", "xhhttqq")); // false
        System.out.println(s.isLongPressedName("kikcxmvzi", "kiikcxxmmvvzz")); // false
        System.out.println(s.isLongPressedName("abcd", "abce")); // false

    }
}
