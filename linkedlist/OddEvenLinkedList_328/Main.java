package linkedlist.OddEvenLinkedList_328;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        ListNode b4 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;

        ListNode res1 = s.oddEvenList2(a1);
        ListNode res2 = s.oddEvenList(b1);
        LinkedListUtils.print(res1);
        LinkedListUtils.print(res2);
    }
}
