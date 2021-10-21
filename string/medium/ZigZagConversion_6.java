package string.medium;

import java.util.ArrayList;
import java.util.List;

/** M
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R

 And then read line by line: "PAHNAPLSIIGYIR"
 Write the code that will take a string and make this conversion given a number of rows:
 string convert(string s, int numRows);

 Example 1:
 Input: s = "PAYPALISHIRING", numRows = 3
 Output: "PAHNAPLSIIGYIR"

 Example 2:
 Input: s = "PAYPALISHIRING", numRows = 4
 Output: "PINALSIGYAHRPI"
 Explanation:

 P     I    N
 A   L S  I G
 Y A   H R
 P     I
 */
public class ZigZagConversion_6 {

    public static void main(String[] args) {
        ZigZagConversion_6 s = new ZigZagConversion_6();
        System.out.println(s.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(s.convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(s.convert("AB", 1)); // PINALSIGYAHRPI
    }

    // O(n) - time, O(n) - space
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        boolean goDown = true;
        int currRow = 0;
        for (int i = 0; i < s.length(); i++) {
            list.get(currRow).append(s.charAt(i));
            if (currRow == numRows - 1) goDown = false;
            if (currRow == 0) goDown = true;
            currRow += goDown ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb: list) {
            result.append(sb.toString());
        }

        return result.toString();
    }
}
