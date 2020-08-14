package string.easy;

import java.util.HashSet;
import java.util.Set;

/** E
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 * Input: "hello"
 * Output: "holle"
 *
 * Example 2:
 * Input: "leetcode"
 * Output: "leotcede"
 *
 * Note:
 * The vowels does not include the letter "y".
 */
public class ReverseVowelsOfString_345 {

    public static void main(String[] args) {
        ReverseVowelsOfString_345 s = new ReverseVowelsOfString_345();
        System.out.println(s.reverseVowels("hello")); // holle
        System.out.println(s.reverseVowels("leetcode")); // leotcede
        System.out.println(s.reverseVowels("bcd")); // bcd
        System.out.println(s.reverseVowels("aeo")); // oea
    }

    // O(n) - time, O(1) - space
    public String reverseVowels(String s) {
        int i = 0;
        int j = s.length()-1;
        char[] arr = s.toCharArray();
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('y');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('Y');
        set.add('O');
        set.add('U');
        while (i < j) {
            while (i < j && !set.contains(arr[i])) {
                i++;
            }
            while (i < j && !set.contains(arr[j])) {
                j--;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        return new String(arr);
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
