package string.easy;

/** E
 Given a string, write a function to check if it is a permutation of a palindrome.
 A palindrome is a word or phrase that is the same forwards and backwards.
 A permutation is a rearrangement of letters. The palindrome does not need to be limited to just dictionary words.

 EXAMPLE
 Input: Tact Coa
 Output: True (permutations:"taco cat'; "atco cta'; etc.)
 */
public class PalindromePermutation {

    public static void main(String[] args) {
        PalindromePermutation s = new PalindromePermutation();
        System.out.println(s.isPolidromPermutation("Tact Coa")); //true
        System.out.println(s.isPolidromPermutation("Tact Coak")); //false

        System.out.println(s.isPolidromPermutation2("Tact Coa")); //true
        System.out.println(s.isPolidromPermutation2("Tact Coak")); //false
    }

    // O(n) - time, O(1) - space
    // Assumed, no leading or trailing spaces; Upper/lower cases considered to be equal; no more then 2 words; ASCII.
    public boolean isPolidromPermutation(String str) {
        int[] counter = new int[26];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                continue;
            }
            int val = Character.toLowerCase(str.charAt(i)) - 'a';
            counter[val]++;
        }
        int countOdd = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] == 0) {
                continue;
            }
            if (counter[i] != 2) {
                countOdd++;
            }
        }
        if (countOdd > 1 && str.length()/2 != 0) {
            return false;
        }
        return (countOdd > 1 && str.length()/2 == 0) ? false : true;
    }

    // O(n) - time, O(1) - space, no [26] arr
    public boolean isPolidromPermutation2(String str) {
        int bitVector = 0;
        for (int i = 0; i < str.length(); i++) {
            int index = Character.toLowerCase(str.charAt(i)) - 'a';
            bitVector = toggle(bitVector, index);
        }

        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }

    private int toggle(int bitVector, int val) {
        int mask = 1 << val;
        if ((bitVector & mask) == 0) {
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }

    private boolean checkExactlyOneBitSet(int bitVector) {
        return (bitVector & (bitVector-1)) == 0;
    }
}
