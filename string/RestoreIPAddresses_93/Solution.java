package string.RestoreIPAddresses_93;

import java.util.ArrayList;
import java.util.List;

/** Medium
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 Example:
 Input: "25525511135"
 Output: ["255.255.11.135", "255.255.111.35"]
 */
public class Solution {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i < 4 && i < s.length(); i++) {
            String first = s.substring(0, i);
            if (isValid(first)) {
                for (int j = 1; i + j < s.length() && j < 4; j++) {
                    String second = s.substring(i, i+j);
                    if (isValid(second)) {
                        for (int k = 1; i + j + k < s.length() && k < 4; k++) {
                            String third = s.substring(i+j, i+j+k);
                            String fourth = s.substring(i+j+k);
                            if (isValid(third) && isValid(fourth)) {
                                res.add(String.format("%s.%s.%s.%s", first, second, third, fourth));
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

    private boolean isValid(String str) {
        if (str.length() > 3) {
            return false;
        }
        if (str.startsWith("0") && str.length() > 1) {
            return false;
        }
        int num = Integer.parseInt(str);
        return num >= 0 && num <= 255;
    }
}
