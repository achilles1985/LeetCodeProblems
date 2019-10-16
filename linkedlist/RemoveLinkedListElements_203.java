package linkedlist;

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

    // O(n) - time, O(1) - space
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        while(head != null && head.val == val) {
            head = head.next;
        }
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            if (curr.val == val) {
               prev.next = curr.next;
               curr = curr.next;
               continue;
            }
            prev = curr;
            curr = curr.next;
        }
        return head;
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
