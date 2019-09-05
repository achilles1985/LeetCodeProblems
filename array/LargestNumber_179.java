package array;

import java.util.Arrays;
import java.util.stream.Collectors;

/** M
 * Given a list of non negative integers, arrange them such that they form the largest number.

 Example 1:
 Input: [10,2]
 Output: "210"

 Example 2:
 Input: [3,30,34,5,9]
 Output: "9534330"

 Note: The result may be very large, so you need to return a string instead of an integer.
 */
public class LargestNumber_179 {

    public static void main(String[] args) {
        LargestNumber_179 s = new LargestNumber_179();

        System.out.println(s.largestNumber(new int[] {0,0})); // 0
        System.out.println(s.largestNumber(new int[] {10,2})); // 210
        System.out.println(s.largestNumber(new int[] {3,30,34,5,9})); // 9534330
    }

    // O(n*log(n)) - time, O(n) - space
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String res = Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((a, b) -> {
                    int p = 0;
                    int p1L = a.length();
                    int p2L = b.length();
                    while (p < p1L + p2L) {
                        if (a.charAt(p%p1L) > b.charAt(p%p2L)) {
                            return -1;
                        }
                        if (a.charAt(p%p1L) < b.charAt(p%p2L)) {
                            return 1;
                        }
                        p++;
                    }

                    return 0;

//                    String order1 = a + b;
//                    String order2 = b + a;
//                    return order2.compareTo(order1);
                })
                .collect(Collectors.joining());

        return res.startsWith("0") ? "0" : res;
    }
}
