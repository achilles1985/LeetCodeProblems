package linkedlist.AddTwoNumbersII_445;

import linkedlist.ListNode;

import java.util.Stack;

/**
 You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:
 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7
 */
public class Solution {

    // O(n+m) - time, O(n+m) - space (because except result linked list we use no extra memory)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resHead = null;
        ListNode cur1 = l1;
        ListNode cur2 = l2;

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while(cur1 != null) {
            stack1.push(cur1.val);
            cur1 = cur1.next;
        }
        while(cur2 != null) {
            stack2.push(cur2.val);
            cur2 = cur2.next;
        }

        int decimal = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int d1 = stack1.pop();
            int d2 = stack2.pop();
            int[] pair = calculate(d1, d2, decimal);
            int sum = pair[0];
            decimal = pair[1];
            resHead = addToHead(resHead, sum);
        }

        while (!stack1.isEmpty()) {
            Integer data = stack1.pop();
            resHead = addToHead(resHead, data);
        }
        while (!stack2.isEmpty()) {
            Integer data = stack2.pop();
            resHead = addToHead(resHead, data);
        }

        return resHead;
    }

    private ListNode addToHead(ListNode head, int data) {
        ListNode node = new ListNode(data);
        if (head == null) {
            head = node;
            return head;
        }
        node.next = head;
        head = node;

        return head;
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
