package backtracking.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** M [marked]
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 Example:
 Input: "25525511135"
 Output: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIPAddresses_93 {

    public static void main(String[] args) {
        RestoreIPAddresses_93 s = new RestoreIPAddresses_93();
        System.out.println(s.restoreIpAddresses("25523412343")); //[255.234.123.43]
        System.out.println(s.restoreIpAddresses("2552341234312312334512312312312312312341231214")); //[]
        System.out.println(s.restoreIpAddresses("010010")); //[0.10.0.10, 0.100.1.0]
        System.out.println(s.restoreIpAddresses("25525511135")); //["255.255.11.135", "255.255.111.35"]
    }

    // O(2^32)->O(1) - time, space
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        helper(s, new ArrayList<>(), result, 0, 0);

        return result;
    }

    private void helper(String input, List<String> temp, List<String> result, int start, int k) {
        if (k == 4 && start == input.length()) {
            result.add(build(temp));
            return;
        }
        if (k == 4 && start < input.length()) {
            return;
        }
        for (int i = start; i < input.length(); i++) {
            String sub = input.substring(start, i+1);
            if (isValid(sub)) {
                temp.add(sub);
                helper(input, temp, result, i+1, k+1);
                temp.remove(temp.size()-1);
            } else {
                break;
            }
        }
    }

    private boolean isValid(String str) {
        if (str.length() > 3 || (str.length() > 1 && str.startsWith("0"))) {
            return false;
        }
        int num = Integer.parseInt(str);
        if (num < 0 || num > 255) {
            return false;
        }
        return true;
    }

    private String build(List<String> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            sb.append(".").append(list.get(i));
        }

        return sb.toString();
    }
}
