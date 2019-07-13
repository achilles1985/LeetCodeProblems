package string.AddStrings_415;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.addStrings("123", "456")); // 579
        System.out.println(s.addStrings("123", "0")); // 123
        System.out.println(s.addStrings("12", "456")); // 468
        System.out.println(s.addStrings("1", "9")); // 10
        System.out.println(s.addStrings("10", "9")); // 19
        System.out.println(s.addStrings("", "9")); // 19
        System.out.println(s.addStrings("9", "99")); // 19
        System.out.println(s.addStrings("999", "999")); // 19
        System.out.println(s.addStrings("237", "284")); // 19
    }
}
