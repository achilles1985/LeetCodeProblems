package string;

/** E
 The count-and-say sequence is the sequence of integers with the first five terms as following:
 1, 11, 21, 1211, 111221, 312211, 13112221, 1113213211

 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 1211 - one 1, then 1 two, then 2 ones or 111221

 Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.
 Note: Each term of the sequence of integers will be represented as a string.

 Example 1:
 Input: 1
 Output: "1"

 Example 2:
 Input: 4
 Output: "1211"
 */
public class CountAndSay_38 {

    public static void main(String[] args) {
        CountAndSay_38 s = new CountAndSay_38();
        //System.out.println(s.countAndSay2(1)); //1
        //System.out.println(s.countAndSay2(4)); //1211
        System.out.println(s.countAndSay2(10)); //1113213211
    }

    // O(2^n) - time, space, since each step in worth case scenario we double the length of each entry.
    public String countAndSay2(int n) {
        if (n == 1) {
            return "1";
        }
        String temp = "1";
        System.out.println(temp);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            int fast = 0;
            int slow = 0;
            sb = new StringBuilder();
            while (fast < temp.length()) {
                while (fast < temp.length() && temp.charAt(slow) == temp.charAt(fast)) {
                    fast++;
                }
                sb.append(fast - slow).append(temp.charAt(slow));
                slow = fast;
            }
            temp = sb.toString();
            System.out.println(temp);
        }
        return sb.toString();
    }

}
