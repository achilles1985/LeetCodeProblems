package string.ZigZagConversion_6;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(s.convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(s.convert("AB", 1)); // PINALSIGYAHRPI
    }
}
