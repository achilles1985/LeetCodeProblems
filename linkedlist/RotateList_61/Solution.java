package linkedlist.RotateList_61;

import linkedlist.ListNode;

import java.nio.file.LinkOption;

/**
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 Example 1:
 Input: 1->2->3->4->5->NULL, k = 2
 Output: 4->5->1->2->3->NULL
 Explanation:
 rotate 1 steps to the right: 5->1->2->3->4->NULL
 rotate 2 steps to the right: 4->5->1->2->3->NULL

 Example 2:
 Input: 0->1->2->NULL, k = 4
 Output: 2->0->1->NULL
 Explanation:
 rotate 1 steps to the right: 2->0->1->NULL
 rotate 2 steps to the right: 1->2->0->NULL
 rotate 3 steps to the right: 0->1->2->NULL
 rotate 4 steps to the right: 2->0->1->NULL
 */
public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        ListNode curr = head;
        int counter = 1;
        // count number of elements and find tail
        while (curr.next != null) {
            counter++;
            curr = curr.next;
        }
        ListNode tail = curr;

        // find new head
        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k % counter; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        if (slow == null) {
            return head;
        }
        ListNode newHead = slow;

        // move element to the tail till head=newHead
        curr = head;
        while (curr != newHead) {
            tail.next = curr;
            tail = tail.next;
            curr = curr.next;
        }
        tail.next = null;

        return newHead;
    }
}
