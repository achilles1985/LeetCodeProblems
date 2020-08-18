package string.medium;

import java.util.ArrayList;
import java.util.List;

/** M [MARKED]
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.
Отсчет с 17.07.2020
 Example:
 Input: "25525511135"
 Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIPAddresses_93 {

    public static void main(String[] args) {
        RestoreIPAddresses_93 s = new RestoreIPAddresses_93();
        System.out.println(s.restoreIpAddresses("25525511135")); //["255.255.11.135", "255.255.111.35"]
        System.out.println(s.restoreIpAddresses("1232342341223")); //[] - too large string
        System.out.println(s.restoreIpAddresses("0000")); //["0.0.0.0"]
    }

    // O(1) - time (no more then 3*3*3 iterations), O(1) - space
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.isEmpty() || s.length() > 12) {
            return new ArrayList<>();
        }
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
