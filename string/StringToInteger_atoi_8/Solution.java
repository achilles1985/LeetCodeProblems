package string.StringToInteger_atoi_8;

public class Solution {

    public int myAtoi(String str) {
        str = str.trim();
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int i = 0;
        int limit = Integer.MAX_VALUE;
        for (; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) || (str.charAt(i) == '-' || str.charAt(i) == '+')) {
                limit = str.charAt(i) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                break;
            }
            if (Character.isAlphabetic(str.charAt(i))) {
                return 0;
            }
        }

        long res = 0;
        for (int j = str.charAt(0) == '-' || str.charAt(0) == '+' ? i : i; j < str.length(); j++) {
            if (!Character.isDigit(str.charAt(j))) {
                continue;
            }
            int d = str.charAt(j) - '0';
            res = res*10 + d;
            if (res > Integer.MAX_VALUE) {
                return limit;
            }
        }

        return str.charAt(i) == '-' ? (int) -res : (int) res;
    }
}
