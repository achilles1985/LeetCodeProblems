package linkedlist.medium;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** M
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse
 * order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers_2 {

    public static void main(String[] args) {
        AddTwoNumbers_2 s = new AddTwoNumbers_2();
        ListNode num1 = new ListNode(1);
        num1.next = new ListNode(4);
        num1.next.next = new ListNode(3);
        num1.next.next.next = new ListNode(2);
        num1.next.next.next.next = new ListNode(1);

        ListNode num2 = new ListNode(4);
        num2.next = new ListNode(7);
        num2.next.next = new ListNode(9);

        LinkedListUtils.print(s.addTwoNumbers(num1, num2));
    }

    // O(n+m) - time, space
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res  = null;
        ListNode resIter  = null;
        ListNode l1Iter = l1;
        ListNode l2Iter = l2;
        int carry = 0;
        while (l1Iter != null || l2Iter != null || carry > 0) {
            int num1 = l1Iter != null ? l1Iter.val : 0;
            int num2 = l2Iter != null ? l2Iter.val : 0;
            int sum  = num1 + num2 + carry;
            int val = sum%10;
            carry = sum/10;
            ListNode node = new ListNode(val);
            if (res == null) {
                res = node;
                resIter = res;
            } else {
                resIter.next = node;
                resIter = resIter.next;
            }
            if (l1Iter != null) {
                l1Iter = l1Iter.next;
            }
            if (l2Iter != null) {
                l2Iter = l2Iter.next;
            }
        }
        return res;
    }
}
