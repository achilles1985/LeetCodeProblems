package linkedlist.PartitionList_86;

import linkedlist.ListNode;

import java.util.Stack;

/**
 Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 You should preserve the original relative order of the nodes in each of the two partitions.

 Example:
 Input: head = 1->4->3->2->5->2, x = 3
 Output: 1->2->2->4->3->5
 */
public class Solution {

    // O(n) - time, O(1) - space
    public ListNode partition(ListNode head, int x) {
        ListNode curr = head;
        ListNode tail = null;
        int counter = 0;
        // determine list tail
        while (curr.next != null) {
            counter++;
            curr = curr.next;
        }
        tail = curr;

        curr = head;
        ListNode prev = null;
        while(counter-- >= 0) {
            if (curr.val >= x) {
                // move current to the tail
                tail.next = curr;
                tail = tail.next;

                if (prev == null) {
                    head = curr.next;
                } else {
                    prev.next = curr.next;
                }
                curr = curr.next;
                tail.next = null;
            } else {
                prev = curr;
                curr = curr.next;
            }
        }

        return head;
    }
}
