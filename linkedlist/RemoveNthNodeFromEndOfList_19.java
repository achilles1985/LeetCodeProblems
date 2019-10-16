package linkedlist;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** M
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 *
 * Note:
 * Given n will always be valid.
 *
 * Follow up:
 * Could you do this in one pass?
 */
public class RemoveNthNodeFromEndOfList_19 {

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList_19 s = new RemoveNthNodeFromEndOfList_19();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LinkedListUtils.print(s.removeNthFromEnd3(head, 3));
    }

    // O(n) - time, O(1) - space, one path, dummy head
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode first = dummyHead.next;
        while (n-- > 0) {
            first = first.next;
        }
        ListNode second = dummyHead;
        while (first != null) {
            second = second. next;
            first = first.next;
        }
        // second points to the (k + l)-th last node, deletes its successor.
        second.next = second.next.next;
        return dummyHead.next;
    }

    // O(n) - time, O(1) - space, one path
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode first = head;
        while (--n > 0) {
            first = first.next;
        }
        ListNode prev = null;
        ListNode second = head;
        while (first.next != null) {
            first = first.next;
            prev = second;
            second = second.next;
        }
        if (prev == null) {
            return head.next;
        }
        prev.next = second.next;
        return head;
    }

    // O(n) - time, O(1) - space. Two paths throw the list.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n == 0) {
            return head;
        }
        int len = length(head);
        int pos = len - n;
        if (pos == 0) {
            return head.next;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (pos-- > 0) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        return head;
    }

    private int length(ListNode head) {
        int len = 0;
        while(head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
