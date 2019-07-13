package linkedlist.PalindromeLinkedList_234;

import linkedlist.ListNode;

import java.util.Stack;

/**
 * Given a singly linked list, determine if it is a palindrome.
 Example 1:
 Input: 1->2
 Output: false

 Example 2:
 Input: 1->2->2->1
 Output: true

 Follow up:
 Could you do it in O(n) time and O(1) space?
 */
public class Solution {

    // O(n) - time, O(n/2) space to keep stack
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // find the middle of the list
        ListNode slow = head;
        ListNode fast = head;
        Stack<Integer> stack = new Stack<>();
        while (fast != null && fast.next != null) {
            stack.push(slow.val);
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        if (fast != null) {
            mid = slow.next;
        }
        while (mid != null) {
            if (stack.pop() != mid.val) {
                return false;
            }
            mid = mid.next;
        }

        return true;
    }

    // O(n) - time, O(1) - space
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // find the middle of the list
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;

        // revert pointers in the second half of the list, 1->2->3<-2<-1
        ListNode cur = mid;
        ListNode pre = null;
        if (fast != null) {
            cur = mid.next;
        }
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        ListNode head1 = head;
        ListNode head2 = pre;
        while (head1 != mid) {
            if (head1.val != head2.val) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }

        return true;
    }
}
