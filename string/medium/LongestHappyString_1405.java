package string.medium;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
 * A string s is called happy if it satisfies the following conditions:
 *     s only contains the letters 'a', 'b', and 'c'.
 *     s does not contain any of "aaa", "bbb", or "ccc" as a substring.
 *     s contains at most a occurrences of the letter 'a'.
 *     s contains at most b occurrences of the letter 'b'.
 *     s contains at most c occurrences of the letter 'c'.
 * Given three integers a, b, and c, return the longest possible happy string.
 * If there are multiple longest happy strings, return any of them. If there is no such string, return the empty string "".
 * A substring is a contiguous sequence of characters within a string.

 * Example 1
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 *
 * Example 2:
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It is the only correct answer in this case.

 * Constraints:
 *     0 <= a, b, c <= 100
 *     a + b + c > 0
 */
public class LongestHappyString_1405 {

    public static void main(String[] args) {
        LongestHappyString_1405 s = new LongestHappyString_1405();
        System.out.println(s.longestDiverseString2(0, 8, 11)); //ccbccbbccbbccbbccbc - fail
        System.out.println(s.longestDiverseString2(2, 2, 1)); //aabbc
        System.out.println(s.longestDiverseString2(7, 1, 0)); //aabaa
        System.out.println(s.longestDiverseString2(1, 1, 10)); //ccaccbcc
    }

    // Incorrect
    // O(a+b+c) - time, O(1) - space
    public String longestDiverseString2(int a, int b, int c) {
        Queue<Item> maxheap = new PriorityQueue<>(Comparator.comparing(Item::getCount).reversed());
        int[] nums = new int[]{a,b,c};
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                maxheap.add(new Item((char) (i + 'a'), nums[i]));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!maxheap.isEmpty()) {
            Item curr = maxheap.poll();
            if (!canBeAdded(sb, curr.c) && !maxheap.isEmpty()) {
                Item next = maxheap.poll();
                next.count--;
                sb.append(next.c);
                maxheap.add(curr);
                curr = next;
            }
            if (curr.count > 0 && canBeAdded(sb, curr.c)) {
                curr.count--;
                sb.append(curr.c);
                maxheap.add(curr);
            }
        }

        return sb.toString();
    }

    private boolean canBeAdded(StringBuilder sb, char c) {
        int size = sb.length();

        return size < 2 || sb.charAt(size - 1) != c || sb.charAt(size - 2) != c;
    }

    private static class Item {
        char c;
        int count;

        Item(char c, int count) {
            this.c = c;
            this.count = count;
        }

        int getCount() {
            return count;
        }
    }
}
