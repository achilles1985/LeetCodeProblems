package dynamic.medium;

/**M
 * Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
 * A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.
 *
 * Example 1:
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 *
 * Example 2:
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 *
 * Example 3:
 * Input: n = 33
 * Output: 66045
 *
 * Constraints:
 *     1 <= n <= 50
 */
public class CountSortedVowelStrings_1641 {

    public static void main(String[] args) {
        CountSortedVowelStrings_1641 s = new CountSortedVowelStrings_1641();
        System.out.println(s.countVowelStringsBF(2)); //15

        System.out.println(s.countVowelStringsDP(2)); //15
        System.out.println(s.countVowelStringsDP(33)); //66045
    }

    // O(n^5) - time, O(n) - space
    public int countVowelStringsBF(int n) {
        return countVowelStringUtil(n, 5);
    }

    // O(n) - time, space
    public int countVowelStringsDP(int n) {
        int[][] cache = new int[n+1][6];

        return helper(n,5,cache);
    }

    private int helper(int n, int vowels, int[][] cache) {
        if (cache[n][vowels] != 0) {
            return cache[n][vowels];
        }
        if (n == 1) {
            return vowels;
        }
        if (vowels == 1) {
            return 1;
        }
        int sum = helper(n - 1, vowels, cache) + helper(n, vowels - 1, cache);
        cache[n][vowels] = sum;

        return sum;
    }

    private int countVowelStringUtil(int n, int vowels) {
        if (n == 1)
            return vowels;
        if (vowels == 1)
            return 1;
        return countVowelStringUtil(n - 1, vowels) +
                countVowelStringUtil(n, vowels - 1);
    }
}
