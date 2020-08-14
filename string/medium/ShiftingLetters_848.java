package string.medium;

/** M
 We have a string S of lowercase letters, and an integer array shifts.
 Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
  * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
  * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
  * Return the final string after all such shifts to S are applied.
 *
 * Example 1:
  * Input: S = "abc", shifts = [3,5,9]
 * Output: "rpl"

 * Explanation:
 * We start with "abc".
 * After shifting the first 1 letters of S by 3, we have "dbc".
 * After shifting the first 2 letters of S by 5, we have "igc".
 * After shifting the first 3 letters of S by 9, we have "rpl", the answer.
 *
 * Note:
  *     1 <= S.length = shifts.length <= 20000
 *     0 <= shifts[i] <= 10 ^ 9
 */
/*
    Questions:
    1. Max value for shift[i]?
    2. Max number of elements in S? Is S.length == shift.length?
 */
public class ShiftingLetters_848 {

    public static void main(String[] args) {
        char c1 = 101;
        char c2 = (char)101;
        StringBuilder sb = new StringBuilder();
        sb.append(c1).append(c2);
        System.out.println(sb.toString());

        ShiftingLetters_848 s = new ShiftingLetters_848();
        System.out.println(s.shiftingLetters2("abc", new int[]{3,5,9})); // rpl
        System.out.println(s.shiftingLetters2("bad", new int[]{10,20,30})); // jyh
    }

    // O(n) - time, space
    public String shiftingLetters2(String S, int[] shifts) {
        if (shifts == null || shifts.length == 0) {
            return S;
        }
        int[] sums = new int[shifts.length];
        sums[0] = shifts[shifts.length - 1];
        int i = 1;
        int j = shifts.length - 2;
        while (j >= 0) {
            sums[i] = (sums[i-1] + shifts[j])%26; // because (a + b) > Integer.MAX
            j--;
            i++;
        }
        StringBuilder sb = new StringBuilder(S.length());
        for (int k = 0; k < S.length(); k++) {
            int c = S.charAt(k) - 'a';
            char res = (char) ((c + sums[sums.length - 1 - k])%26 + 'a');
            sb.append(res);
        }
        return sb.toString();
    }

}
