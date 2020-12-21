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
        List<List<String>> result = new ArrayList<>();
        helper(s, 0, 0, new ArrayList<>(), result);

        return buildResult(result);
    }

    private void helper(String s, int start, int k, List<String> list, List<List<String>> result) {
        if (k == 4 && start == s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (k == 4 && start < s.length()) {
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i+1);
            if (isValid(sub)) {
                list.add(sub);
                helper(s, i+1, k+1, list, result);
                list.remove(list.size()-1);
            }
        }
    }

    private boolean isValid(String str) {
        if (str.length() > 3 || (str.length() > 1 && str.startsWith("0"))){
            return false;
        }
        int num = Integer.parseInt(str);
        if (num < 0 || num > 255) {
            return false;
        }
        return true;
    }

    private List<String> buildResult(List<List<String>> list) {
        List<String> result = new ArrayList<>();
        for (List<String> outer: list) {
            StringBuilder sb = new StringBuilder();
            for (String inner: outer) {
                sb.append(inner).append(".");
            }
            sb.deleteCharAt(sb.length()-1);
            result.add(sb.toString());
        }

        return result;
    }
}
