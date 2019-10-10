package linkedlist;

import java.util.HashSet;
import java.util.Set;

/** M
 * We are given head, the head node of a linked list containing unique integer values.
 * We are also given the list G, a subset of the values in the linked list.
 * Return the number of connected components in G, where two values are connected if they appear consecutively in the
 * linked list.
 *
 * Example 1:
 * Input:
 * head: 0->1->2->3
 * G = [0, 1, 3]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, so [0, 1] and [3] are the two connected components.
 *
 * Example 2:
 * Input:
 * head: 0->1->2->3->4
 * G = [0, 3, 1, 4]
 * Output: 2
 * Explanation:
 * 0 and 1 are connected, 3 and 4 are connected, so [0, 1] and [3, 4] are the two connected components.
 *
 * Note:
 *     If N is the length of the linked list given by head, 1 <= N <= 10000.
 *     The value of each node in the linked list will be in the range [0, N - 1].
 *     1 <= G.length <= 10000.
 *     G is a subset of all values in the linked list.
 */
public class LinkedListComponents_817 {

    public static void main(String[] args) {
        LinkedListComponents_817 s = new LinkedListComponents_817();
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);

        System.out.println(s.numComponents(head, new int[]{5,6})); //0
        System.out.println(s.numComponents(head, new int[]{0,3,1,4})); //2
    }

    // O(n) - time, O(m) - space, m - G length
    public int numComponents(ListNode head, int[] G) {
        if (head == null || G == null || G.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        ListNode curr = head;
        for (int i = 0; i < G.length; i++) {
            set.add(G[i]);
        }

        int count = 0;
        curr = head;
        while (curr != null) {
            if (!set.contains(curr.val)) {
                curr = curr.next;
            } else {
                while (curr != null && set.contains(curr.val)) {
                    curr = curr.next;
                }
                count++;
            }
        }
        return count;
    }
}
