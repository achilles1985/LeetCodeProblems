package linkedlist.easy;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** E
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 *
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesFromSortedList_83 {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList_83 s = new RemoveDuplicatesFromSortedList_83();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(2);
        head2.next.next.next.next = new ListNode(2);

        LinkedListUtils.print(s.deleteDuplicates(head));
        LinkedListUtils.print(s.deleteDuplicates(head2));
    }

    // O(n) - time, O(1) - space
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        ListNode dummy = head;
        ListNode cur = head;
        while (cur != null) {
            if (prev.val != cur.val) {
                prev.next = cur;
                prev = prev.next;
            }
            cur = cur.next;
        }
        prev.next = null;

        return dummy;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}
