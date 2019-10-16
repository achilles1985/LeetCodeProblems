package linkedlist;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** M
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 * reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 *
 * Example 1:
 * Given 1->2->3->4, reorder it to 1->4->2->3.
 *
 * Example 2:
 * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class ReorderList_143 {

    public static void main(String[] args) {
        ReorderList_143 s = new ReorderList_143();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);

        s.reorderList(head);
        s.reorderList(head2);
        LinkedListUtils.print(head);
        LinkedListUtils.print(head2);
    }

    // O(n) - time, O(1) - space
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode middle = middle(head);
        reverseFrom(middle);
        ListNode p1 = head;
        ListNode p2 = middle.next;
        while (p1 != middle) {
            ListNode n1 = p1.next;
            ListNode n2 = p2.next;
            middle.next = n2;
            p1.next = p2;
            p2.next = n1;
            p2 = n2;
            p1 = n1;
        }
    }

    private ListNode middle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private void reverseFrom(ListNode middle) {
        ListNode curr = middle.next;
        ListNode prev = null;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        middle.next = prev;
    }
}
