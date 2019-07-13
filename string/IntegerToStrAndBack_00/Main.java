package string.IntegerToStrAndBack_00;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Integer to String");
        System.out.println(s.intToString(123));
        System.out.println(s.intToString(1));
        System.out.println(s.intToString(0));
        System.out.println(s.intToString(-123));

        System.out.println("String to integer");
        System.out.println(s.stringToInt("123"));
        System.out.println(s.stringToInt("1"));
        System.out.println(s.stringToInt("0"));
        System.out.println(s.stringToInt("-123"));
        System.out.println(s.stringToInt("-1"));
    }
}
