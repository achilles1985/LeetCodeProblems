package linkedlist.RemoveLinkedListElements_203;

import linkedlist.ListNode;

/**
 Remove all elements from a linked list of integers that have value val.

 Example:
 Input:  1->2->6->3->4->5->6, val = 6
 Output: 1->2->3->4->5
 */
public class Solution {

    public ListNode removeElements(ListNode head, int val) {
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
