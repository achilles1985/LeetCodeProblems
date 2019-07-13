package linkedlist.RemoveNthNodeFromEndOfList_19;

import linkedlist.ListNode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 Example:
 Given linked list: 1->2->3->4->5, and n = 2.
 After removing the second node from the end, the linked list becomes 1->2->3->5.

 Note:
 Given n will always be valid.
 Follow up:
 Could you do this in one pass?
 */
public class Solution {

    // My incorrect solution
    // O(n)- time, O(1) - space
    public ListNode removeNthFromEnd(ListNode head, int k) {
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        ListNode prev = null;
        while (fast != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = slow.next;

        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int k) {
        ListNode curr1 = head;
        ListNode curr2 = head;
        ListNode curr3 = head;

        ListNode prev1 = null;
        ListNode prev2 = null;

        int counter = 1;
        while (curr1 != null) {
            if (counter++ % k == 0) {
                curr2 = curr1;
                prev2 = prev1;
            } else if (counter % (k+1) == 0) {
                curr3 = curr1;
            }
            prev1 = curr1;
            curr1 = curr1.next;
        }
        if (counter%2 == 0) {
            curr2.next = curr3.next;
            curr3.next = null;
        } else {
            prev2.next = curr2.next;
            curr2.next = null;
        }

        return head;
    }
}
