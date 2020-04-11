package string;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/** M
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII,
 * which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not
 * IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction
 * is used:
 *
 *     I can be placed before V (5) and X (10) to make 4 and 9.
 *     X can be placed before L (50) and C (100) to make 40 and 90.
 *     C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 * Input: 3
 * Output: "III"
 *
 * Example 2:
 * Input: 4
 * Output: "IV"
 *
 * Example 3:
 * Input: 9
 * Output: "IX"
 *
 * Example 4:
  Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 *
 * Example 5:
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class IntegerToRoman_12 {

    public static void main(String[] args) {
        IntegerToRoman_12 s = new IntegerToRoman_12();
        System.out.println(s.intToRoman(94)); // XCIV
        System.out.println(s.intToRoman(1994)); // MCMXCIV
        System.out.println(s.intToRoman(3)); // III
        System.out.println(s.intToRoman(4)); // IV
        System.out.println(s.intToRoman(9)); // IX
        System.out.println(s.intToRoman(58)); // LVIII
    }

    // O(1) - time (there is a limited set of numbers <= 3999), O(1) - space
    public String intToRoman(int num) {
        if (num < 0 || num > 3999) {
            return "";
        }
        NavigableMap<Integer, String> map = new TreeMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");
        map.put(4, "IV");
        map.put(9, "IX");
        map.put(40, "XL");
        map.put(90, "XC");
        map.put(400, "CD");
        map.put(900, "CM");

        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            Map.Entry<Integer,String> entry = map.floorEntry(num);
            sb.append(entry.getValue());
            num -= entry.getKey();
        }

        return sb.toString();
    }
}
