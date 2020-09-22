package linkedlist.medium;

import java.util.Stack;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** M
 * You are given two non-empty linked lists representing two non-negative integers.
 * The most significant digit comes
 * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
 *
 * Example:
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
 */
public class AddTwoNumbers_II_445 {

    public static void main(String[] args) {
        AddTwoNumbers_II_445 s = new AddTwoNumbers_II_445();
        ListNode num1 = new ListNode(7);
        num1.next = new ListNode(2);
        num1.next.next = new ListNode(4);
        num1.next.next.next = new ListNode(3);

        ListNode num2 = new ListNode(5);
        num2.next = new ListNode(6);
        num2.next.next = new ListNode(4);

        LinkedListUtils.print(s.addTwoNumbers(num1, num2));
    }

    // O(n+m) - time, O(n+m+max(n,m)) - space
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> s1 = new Stack<>();
        Stack<ListNode> s2 = new Stack<>();
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                s1.push(l1);
                l1 = l1.next;
            }
            if (l2 != null) {
                s2.push(l2);
                l2 = l2.next;
            }
        }
        int carry = 0;
        Stack<ListNode> s3 = new Stack<>();
        while (!s1.isEmpty() || !s2.isEmpty() || carry > 0) {
            int v1 = !s1.isEmpty() ? s1.pop().val : 0;
            int v2 = !s2.isEmpty() ? s2.pop().val : 0;
            int sum = v1 + v2 + carry;
            s3.push(new ListNode(sum%10));
            carry = sum/10;
        }

        ListNode res = new ListNode(0);
        ListNode resIter = res;
        while (!s3.isEmpty()) {
            resIter.next = s3.pop();
            resIter = resIter.next;
        }
        return res.next;
    }
}
