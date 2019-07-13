package linkedlist.SwapNodesInPairs_24;

import linkedlist.ListNode;

/**
 Given a linked list, swap every two adjacent nodes and return its head.
 You may not modify the values in the list's nodes, only nodes itself may be changed.

 Example:
 Given 1->2->3->4, you should return the list as 2->1->4->3.
 */
public class Solution {

    // O(n) - time, O(1) - space
    public ListNode swapPairs2(ListNode head) {
        ListNode fakeHead = null;
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode left = curr;
            ListNode right = curr.next;
            left.next = right.next;
            right.next = left;
            if (fakeHead == null) {
                fakeHead = right;
            }
            if (prev != null) {
                prev.next = right;
            }
            prev = curr;
            curr = curr.next;
        }

        return fakeHead;
    }

    // Solution based on dummy node: https://leetcode.com/problems/swap-nodes-in-pairs/discuss/11046/my-simple-java-solution-for-share
    public ListNode swapPairs3(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            first.next = second.next;
            current.next = second;
            current.next.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }
}
