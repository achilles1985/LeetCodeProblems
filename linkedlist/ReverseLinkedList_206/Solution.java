package linkedlist.ReverseLinkedList_206;

import linkedlist.ListNode;

/**
 Reverse a singly linked list.
 Example:
 Input: 1->2->3->4->5->NULL
 Output: 5->4->3->2->1->NULL

 Follow up:
 A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class Solution {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

}
