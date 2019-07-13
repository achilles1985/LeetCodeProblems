package linkedlist.OddEvenLinkedList_328;

import linkedlist.ListNode;

/**
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 * <p>
 * Example 1:
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * <p>
 * Example 2:
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * <p>
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class Solution {

    // My solution
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // find list size and tail
        ListNode tail = head;
        int size = 1;
        while (tail.next != null) {
            size++;
            tail = tail.next;
        }

        ListNode curr = head;
        ListNode prev = null;
        int counter = 1;
        while (counter <= size) {
            if (counter % 2 == 0) {
                tail.next = curr;
                tail = tail.next;
                prev.next = curr.next;
            }
            counter++;
            prev = curr;
            curr = curr.next;
            tail.next = null;
        }
        tail.next = null;

        return head;
    }

    // Good solution
    // https://discuss.leetcode.com/topic/34292/simple-o-n-time-o-1-space-java-solution
    // Time complexity O(n), space complexity O(1).
    public ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode evenHead = head.next;
        ListNode current = head;
        ListNode lastOdd = head;
        boolean isOdd = true;
        while (current.next != null) {
            ListNode next = current.next;
            current.next = current.next.next;
            current = next;
            isOdd = !isOdd;
            lastOdd = (isOdd) ? current : lastOdd;
        }
        lastOdd.next = evenHead;
        return head;
    }

}
