package binarySearch.hard;

import java.util.HashSet;
import java.util.Set;

/**
 * H [marked]
 * Given a string S, consider all duplicated substrings: (contiguous) substrings of S that occur 2 or more times.  (The occurrences may overlap.)
 * Return any duplicated substring that has the longest possible length.  (If S does not have a duplicated substring, the answer is "".)
 * <p>
 * Example 1:
 * Input: "banana"
 * Output: "ana"
 * <p>
 * Example 2:
 * Input: "abcd"
 * Output: ""
 * <p>
 * Note:
 * 2 <= S.length <= 10^5
 * S consists of lowercase English letters.
 */
public class LongestDuplicateSubstring_1044 {

    public static void main(String[] args) {
        LongestDuplicateSubstring_1044 s = new LongestDuplicateSubstring_1044();
        System.out.println(s.longestDupSubstringRK("moplvidmaagmsiyyrkchbyhivlqwqsjcgtumqscmxrxrvwsnjjvygrelcbjgbpounhuyealllginkitfaiviraqcycjmskrozcdqylbuejrgfnquercvghppljmojfvylcxakyjxnampmakyjbqgwbyokaybcuklkaqzawageypfqhhasetugatdaxpvtevrigynxbqodiyioapgxqkndujeranxgebnpgsukybyowbxhgpkwjfdywfkpufcxzzqiuglkakibbkobonunnzwbjktykebfcbobxdflnyzngheatpcvnhdwkkhnlwnjdnrmjaevqopvinnzgacjkbhvsdsvuuwwhwesgtdzuctshytyfugdqswvxisyxcxoihfgzxnidnfadphwumtgdfmhjkaryjxvfquucltmuoosamjwqqzeleaiplwcbbxjxxvgsnonoivbnmiwbnijkzgoenohqncjqnckxbhpvreasdyvffrolobxzrmrbvwkpdbfvbwwyibydhndmpvqyfmqjwosclwxhgxmwjiksjvsnwupraojuatksjfqkvvfroqxsraskbdbgtppjrnzpfzabmcczlwynwomebvrihxugvjmtrkzdwuafozjcfqacenabmmxzcueyqwvbtslhjeiopgbrbvfbnpmvlnyexopoahgmwplwxnxqzhucdieyvbgtkfmdeocamzenecqlbhqmdfrvpsqyxvkkyfrbyolzvcpcbkdprttijkzcrgciidavsmrczbollxbkytqjwbiupvsorvkorfriajdtsowenhpmdtvamkoqacwwlkqfdzorjtepwlemunyrghwlvjgaxbzawmikfhtaniwviqiaeinbsqidetfsdbgsydkxgwoqyztaqmyeefaihmgrbxzyheoegawthcsyyrpyvnhysynoaikwtvmwathsomddhltxpeuxettpbeftmmyrqclnzwljlpxazrzzdosemwmthcvgwtxtinffopqxbufjwsvhqamxpydcnpekqhsovvqugqhbgweaiheeicmkdtxltkalexbeftuxvwnxmqqjeyourvbdfikqnzdipmmmiltjapovlhkpunxljeutwhenrxyfeufmzipqvergdkwptkilwzdxlydxbjoxjzxwcfmznfqgoaemrrxuwpfkftwejubxkgjlizljoynvidqwxnvhngqakmmehtvykbjwrrrjvwnrteeoxmtygiiygynedvfzwkvmffghuduspyyrnftyvsvjstfohwwyxhmlfmwguxxzgwdzwlnnltpjvnzswhmbzgdwzhvbgkiddhirgljbflgvyksxgnsvztcywpvutqryzdeerlildbzmtsgnebvsjetdnfgikrbsktbrdamfccvcptfaaklmcaqmglneebpdxkvcwwpndrjqnpqgbgihsfeotgggkdbvcdwfjanvafvxsvvhzyncwlmqqsmledzfnxxfyvcmhtjreykqlrfiqlsqzraqgtmocijejneeezqxbtomkwugapwesrinfiaxwxradnuvbyssqkznwwpsbgatlsxfhpcidfgzrc")); // "akyj"
        System.out.println(s.longestDupSubstringRK("banana")); // "ana"
        System.out.println(s.longestDupSubstringRK("abcd")); // ""
        System.out.println(s.longestDupSubstringRK("abcbd")); // ""

    }

    // O(n^2) - time, O(n) - space
    public String longestDupSubstringBF(String S) {
        int max = 1;
        String result = "";
        for (int i = 2; i < S.length(); i++) {
            Set<String> unique = new HashSet<>();
            for (int j = 0; j + i <= S.length(); j++) {
                String sub = S.substring(j, j + i);
                if (unique.contains(sub)) {
                    max = Math.max(max, i + j);
                    result = sub;
                }
                unique.add(sub);
            }
        }
        return result;
    }

    // O(log(n)*n) - time, O(n) - space
    public String longestDupSubstring(String S) {
        int left = 0, right = S.length() - 1;
        String result = "";
        while (left < right) {
            int mid = left + (right - left) / 2;
            Wrapper pair = exists(S, mid);
            if (pair.exist) {
                left = mid + 1;
                result = pair.substring;
            } else {
                right = mid;
            }
        }

        return result.length() < 2 ? "" : result;
    }

    // O(log(n)*n) - time, O(n) - space, to keep hashes
    public String longestDupSubstringRK(String S) {
        int left = 0, right = S.length() - 1;
        String result = "";
        while (left < right) {
            int mid = left + (right - left) / 2;
            Wrapper pair = existsRK(S, mid);
            if (pair.exist) {
                left = mid + 1;
                result = pair.substring;
            } else {
                right = mid;
            }
        }

        return result.length() < 2 ? "" : result;
    }

    // TODO: Implement it correctly
    private Wrapper existsRK(String s, int length) {
        Set<Long> hashes = new HashSet<>();
        long hash = 0;
        long MODULUS = (long) Math.pow(2, 32);
        for (int i = 0; i < length; i++) {
            long n1 = (long) Math.pow(3.0, i) % MODULUS;
            long n2 = ((s.charAt(i) - 'a') * n1) % MODULUS;
            hash = (hash + n2) % MODULUS;
        }
        hashes.add(hash);
        for (int i = 1; i <= s.length() - length; i++) {
            //String sub = s.substring(i, i + length);
            long n1 = (s.charAt(i - 1) - 'a');
            long n2 = (s.charAt(i + length - 1) - 'a');
            long n3 = (long) Math.pow(3.0, length) % MODULUS;
            long temp = hash - (n1 + (n2 * n3) % MODULUS) % MODULUS;
            hash = temp / 3;
            if (hashes.contains(hash)) {
                return new Wrapper(true, s.substring(i, i + length));
            }
            hashes.add(hash);
        }
        return new Wrapper(false, "");
    }

    private Wrapper exists(String str, int length) {
        Set<String> unique = new HashSet<>();
        for (int i = 0; i <= str.length() - length; i++) {
            String sub = str.substring(i, i + length);
            if (unique.contains(sub)) {
                return new Wrapper(true, sub);
            }
            unique.add(sub);
        }

        return new Wrapper(false, "");
    }

    private static class Wrapper {
        boolean exist;
        String substring;

        Wrapper(boolean exists, String substring) {
            this.exist = exists;
            this.substring = substring;
        }
    }

    ////
    public String longestDupSubstring2(String S) {
        int n = S.length();
        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = (int) S.charAt(i) - (int) 'a';
        }
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long) Math.pow(2, 32);

        // binary search, L = repeating string length
        int left = 1, right = n;
        int L;
        while (left <= right) {
            L = left + (right - left) / 2;
            if (search(L, a, modulus, n, nums) != -1) {
                left = L + 1;
            } else {
                right = L - 1;
            }
        }

        int start = search(left - 1, a, modulus, n, nums);
        return S.substring(start, start + left - 1);
    }

    private int search(int L, int a, long modulus, int n, int[] nums) {
        // compute the hash of string S[:L]
        long h = 0;
        for (int i = 0; i < L; ++i) {
            h = (h * a + nums[i]) % modulus;
        }

        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet();
        seen.add(h);
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) {
            aL = (aL * a) % modulus;
        }

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.contains(h)) {
                return start;
            }
            seen.add(h);
        }
        return -1;
    }
}
