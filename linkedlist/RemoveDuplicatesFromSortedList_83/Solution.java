package linkedlist.RemoveDuplicatesFromSortedList_83;

import linkedlist.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 Example 1:
 Input: 1->1->2
 Output: 1->2

 Example 2:
 Input: 1->1->2->3->3
 Output: 1->2->3
 */
public class Solution {

    // O(n) - time, O(1) - space
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while(curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            }
            curr = curr.next;
        }
        return head;
    }
}
