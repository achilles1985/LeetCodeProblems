package string.StringToInteger_atoi_8;

public class Main {

    public static void main(String[] args) {
        int a1 = 2_147_483_647;
        int a2 = -2_147_483_648;

        Solution s = new Solution();
        //System.out.println(s.myAtoi(""));
        System.out.println(s.myAtoi("  "));
        System.out.println(s.myAtoi("42"));
        System.out.println(s.myAtoi("+42"));
        System.out.println(s.myAtoi("  -42"));
        System.out.println(s.myAtoi("words and 987"));
        System.out.println(s.myAtoi("  words and 987"));
        System.out.println(s.myAtoi("123words and 987"));
        System.out.println(s.myAtoi("123-words and 987"));
        System.out.println(s.myAtoi("  123 wo rds and 987 df 0"));
        System.out.println(s.myAtoi("-912834724332")); // Integer.MIN_VALUE
        System.out.println(s.myAtoi("91283472332")); // Integer.MAX_VALUE
    }
}
