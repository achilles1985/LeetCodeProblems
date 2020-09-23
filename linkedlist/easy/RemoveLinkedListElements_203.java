package linkedlist.easy;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** E
 * Remove all elements from a linked list of integers that have value val.
 *
 * Example:
 * Input:  1->2->6->3->4->5->6, val = 6
 * Output: 1->2->3->4->5
 */
public class RemoveLinkedListElements_203 {

    public static void main(String[] args) {
        RemoveLinkedListElements_203 s = new RemoveLinkedListElements_203();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);

        LinkedListUtils.print(s.removeElements(head, 6)); // 1-2-3-4-5
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (head != null) {
            if (head.val != val) {
                curr.next = head;
                curr = curr.next;
            }
            head = head.next;
        }
        curr.next = null;

        return dummy.next;
    }

    // O(n) - time, O(1) - space
    public ListNode removeElements2(ListNode head, int val) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            if (head.val == val) {
                head = head.next;
            } else if (curr.val == val) {
                prev.next = curr.next;
            } else {
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }


}
