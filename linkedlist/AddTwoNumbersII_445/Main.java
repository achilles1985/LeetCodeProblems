package linkedlist.AddTwoNumbersII_445;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

public class Main {

    public static void main(String[] args) {
        // (2->4->3) + (5->6->4), 243 + 564 = 807, res = 8-0-7
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;

        ListNode m1 = new ListNode(5);
        ListNode m2 = new ListNode(6);
        ListNode m3 = new ListNode(4);
        m1.next = m2;
        m2.next = m3;

        // (2->4->3->7->8) + (5->6->4), 24378+564=24942, 2-4-9-4-2
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(7);
        ListNode a5 = new ListNode(8);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;

        Solution s = new Solution();
        ListNode result1 = s.addTwoNumbers(n1, m1);
        ListNode result2 = s.addTwoNumbers(a1, b1);
        LinkedListUtils.print(result1);
        LinkedListUtils.print(result2);
    }
}
