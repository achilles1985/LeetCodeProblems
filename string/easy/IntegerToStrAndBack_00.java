package string.easy;

import java.util.Stack;

public class IntegerToStrAndBack_00 {

    public static void main(String[] args) {
        IntegerToStrAndBack_00 s = new IntegerToStrAndBack_00();
        System.out.println("Integer to String");
        System.out.println(s.intToString2(-123));
        System.out.println(s.intToString2(123));
        System.out.println(s.intToString2(1));
        System.out.println(s.intToString2(0));

        System.out.println("String to integer");
        System.out.println(s.stringToInt("123"));
        System.out.println(s.stringToInt("1"));
        System.out.println(s.stringToInt("0"));
        System.out.println(s.stringToInt("-123"));
        System.out.println(s.stringToInt("-1"));
    }

    public String intToString2(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isPositive = num >= 0;
        if (!isPositive) {
            num *= -1;
        }
        Stack<Integer> stack = new Stack<>();
        while (num%10 > 0) {
            int digit = num%10;
            stack.push(digit);
            num = num/10;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return isPositive ? sb.toString() : "-" + sb.toString();
    }

    public String intToString(int num) {
        boolean isNegative = false;
        if (num < 0) {
            isNegative = true;
        }
        StringBuilder sb = new StringBuilder();
        num = Math.abs(num);
        do {
            int r = num%10;
            sb.append(r);
            num = num/10;
        } while (num > 0);

        return isNegative ? sb.append("-").reverse().toString() : sb.reverse().toString();
    }

    public int stringToInt(String s) {
        int res = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {
            int d = s.charAt(i) - '0';
            res = res * 10 + d;
        }

        return s.charAt(0) == '-' ? res*(-1) : res;
    }

}
