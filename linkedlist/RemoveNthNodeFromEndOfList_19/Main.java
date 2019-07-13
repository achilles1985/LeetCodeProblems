package linkedlist.RemoveNthNodeFromEndOfList_19;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        // 1-2-3-4-5,k=2, 4, res=1-2-3-5
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        ListNode a4 = new ListNode(4);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;
        LinkedListUtils.print(s.removeNthFromEnd2(a1, 2));

        // 1-2-3-4,k=2, 3, res=1-2-4
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        ListNode b4 = new ListNode(4);
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
        LinkedListUtils.print(s.removeNthFromEnd2(b1, 2));
    }
}
