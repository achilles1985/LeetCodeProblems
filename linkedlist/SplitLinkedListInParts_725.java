package linkedlist;

import java.util.Arrays;

import jdk.internal.org.objectweb.asm.util.CheckAnnotationAdapter;
import utils.SolutionUtils;

/** M
 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive
 * linked list "parts".
 *
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1.
 * This may lead to some parts being null.
 *
 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a
 * size greater than or equal parts occurring later.
 *
 * Return a List of ListNode's representing the linked list parts that are formed.
 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 *
 * Example 1:
 * Input:
 * root = [1, 2, 3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The input and each element of the output are ListNodes, not arrays.
 * For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next
 * = null.
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but it's string representation as a ListNode is [].
 *
 * Example 2:
 * Input:
 * root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 * Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger
 * size than the later parts.
 *
 * Note:
 * The length of root will be in the range [0, 1000].
 * Each value of a node in the input will be an integer in the range [0, 999].
 * k will be an integer in the range [1, 50].
 */
public class SplitLinkedListInParts_725 {

    public static void main(String[] args) {
        SplitLinkedListInParts_725 s = new SplitLinkedListInParts_725();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);
        head2.next.next.next.next.next.next = new ListNode(7);

        ListNode[] res2 = s.splitListToParts(head2, 3);
        ListNode[] res1 = s.splitListToParts(head, 5);
    }

    // O(n) - time, O(n) - space (no rearangement of input list)
    public ListNode[] splitListToParts(ListNode root, int k) {
        if (root == null) {
            return new ListNode[k];
        }
        int length = calculateLength(root);
        ListNode[] res = new ListNode[k];
        int i = 0;
        if (length <= k) {
            while (root != null) {
                res[i++] = new ListNode(root.val);
                root = root.next;
            }
        } else {
            int partSize = length/k;
            int addition = length%k;
            while (root != null) {
                ListNode currList = new ListNode(root.val);
                ListNode curr = currList;
                for (int j = 1; j < partSize + (i < addition ? 1 : 0); j++) { // triky
                    curr.next = new ListNode(root.next.val);
                    curr = curr.next;
                    root = root.next;
                }
                root = root.next;
                res[i++] = currList;
            }
        }
        return res;
    }

    private int calculateLength(ListNode root) {
        int len = 0;
        while (root != null) {
            len++;
            root = root.next;
        }
        return len;
    }
}
