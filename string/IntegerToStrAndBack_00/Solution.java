package string.IntegerToStrAndBack_00;

public class Solution {

    public String intToString (int num) {
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
