package string.medium;

/** M (Aziz, p.98)
 * Consider the following two rules that are to be applied to an array of characters.
 • Replace each 'a' by two 'd's.
 • Delete each entry containing a 'b'.

 For example, applying these rules to the array (a,c,d,b,b,c,a) results in the array (d,d,c,d,c,d,d).
 Write a program which takes as input an array of characters, and removes each 'b' and
 replaces each 'a' by two 'd's. Specifically, along with the array, you are provided is integer-valued size.
 Size denotes the number of entries of the array that the operation is to be applied to.
 You do not have to worry preserving about subsequent entries.
 For example, if the array is {a,b,a,c) and the size is 4, then you can return (d,d,d,d,c).
 You can assume there is enough space in the array to hold the final result.
 */
public class FindAndReplaceString_I {

    public static void main(String[] args) {
        FindAndReplaceString_I s = new FindAndReplaceString_I();
        System.out.println(s.replaceAndRemove("acdbbca")); // ddcdcdd
    }

    // O(n) - time, O(1) - space
    public String replaceAndRemove(String str) {
        char[] chars = str.toCharArray();
        int i = 0;
        int j = 0;
        // remove all 'b' by copping over all not 'b' chars
        while (i < chars.length && j < chars.length) {
            if (chars[j] != 'b') {
                chars[i++] = chars[j++];
            } else {
                j++;
            }
        }
        j = j - 1;
        i = i - 1;
        // Replace all 'a' with 'd'
        while (i >= 0) {
            if (chars[i] == 'a') {
                chars[j--] = 'd';
                chars[j--] = 'd';
            } else {
                chars[j--] = chars[i];
            }
            i--;
        }

        return new String(chars);
    }

    // Solution from a book
    public String replaceAndRemove2(String input) {
        char[] chars = input.toCharArray();
        int writeIdx = 0;
        int aCount = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 'b') {
                chars[writeIdx++] = chars[i];
            }
            if (chars[i] == 'a') {
                aCount++;
            }
        }

        int curIdx = writeIdx - 1;
        writeIdx = curIdx + aCount;
        while (curIdx >= 0) {
            if (chars[curIdx] == 'a') {
                chars[writeIdx--] = 'd';
                chars[writeIdx--] = 'd';
            } else {
                chars[writeIdx--] = chars[curIdx];
            }
            curIdx--;
        }

        return String.valueOf(chars);
    }
}
