package string.medium;

/**
 * M
 * Given two integers A and B, return any string S such that:
 * <p>
 * S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
 * The substring 'aaa' does not occur in S;
 * The substring 'bbb' does not occur in S.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: A = 1, B = 2
 * Output: "abb"
 * Explanation: "abb", "bab" and "bba" are all correct answers.
 * Example 2:
 * <p>
 * Input: A = 4, B = 1
 * Output: "aabaa"
 * <p>
 * <p>
 * Note:
 * <p>
 * 0 <= A <= 100
 * 0 <= B <= 100
 * It is guaranteed such an S exists for the given A and B.
 */
public class StringWithoutAAAorBBB_984 {

    public static void main(String[] args) {

    }

    public String strWithout3a3b(int A, int B) {
        StringBuilder sb = new StringBuilder(A + B);
        while (A + B > 0) {
            String s = sb.toString();
            if (s.endsWith("aa")) {
                sb.append("b");
                --B;
            } else if (s.endsWith("bb")) {
                sb.append("a");
                --A;
            } else if (A > B) {
                sb.append("a");
                --A;
            } else {
                sb.append("b");
                --B;
            }
        }
        return sb.toString();
    }
}
