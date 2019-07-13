package linkedlist.AddTwoNumbers_2;

import linkedlist.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:
 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 */
public class Solution {

    // O(n+m) - time, O(1) - space (because except result linked list we use no extra memory)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resHead = null;
        ListNode resTail = null;
        int decimal = 0;
        while (l1 != null && l2 != null) {
            int[] pair = calculate(l1.val, l2.val, decimal);
            int sum = pair[0];
            decimal = pair[1];
            resTail = addToTail(resTail, sum);
            if (resHead == null) {
                resHead = resTail;
            }
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            resTail.next = l1;
            l1 = l1.next;
            resTail = resTail.next;
        }
        while (l2 != null) {
            resTail.next = l2;
            l2 = l2.next;
            resTail = resTail.next;
        }

        return resHead;
    }

    private ListNode addToTail(ListNode tail, int data) {
        ListNode node = new ListNode(data);
        if (tail == null) {
            tail = node;
            return tail;
        }
        tail.next = node;
        tail = tail.next;

        return tail;
    }

    private int[] calculate(int data1, int data2, int decimal) {
        int sum = data1 + data2 + decimal;
        if (sum/10 > 0) {
            int remainder = sum%10;
            decimal = sum/10;
            sum = remainder;
            return new int[] {sum, decimal};
        }
        return new int[] {sum, 0};
    }
}
