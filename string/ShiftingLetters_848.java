package string;

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
public class ShiftingLetters_848 {

    public static void main(String[] args) {
        char c1 = 101;
        char c2 = (char)101;
        StringBuilder sb = new StringBuilder();
        sb.append(c1).append(c2);
        System.out.println(sb.toString());

        ShiftingLetters_848 s = new ShiftingLetters_848();
        System.out.println(s.shiftingLetters("bad", new int[]{10,20,30})); // jyh
        System.out.println(s.shiftingLetters("abc", new int[]{3,5,9})); // rpl
    }

    public String shiftingLetters(String S, int[] shifts) {
        StringBuilder sb = new StringBuilder();
        int sum = 0;
        for (int i = 0; i < shifts.length; i++) {
            sum = (sum + shifts[i])%26;
        }
        for (int i = 0; i < S.length(); i++) {
            int idx = S.charAt(i) - 'a';
            char newChar = (char) ((idx + sum)%26 + 97);
            sb.append(newChar);
            sum = Math.floorMod(sum - shifts[i], 26);
        }
        return sb.toString();
    }
}
