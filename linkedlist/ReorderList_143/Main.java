package linkedlist.ReorderList_143;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

public class Main {

    public static void main(String[] args) {
        // 1-2-3-4-5-6, res=1-6-2-5-3-4
        ListNode head1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        head1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        // 1-2-3-4-5-6, res=1-6-2-5-3-4
        ListNode head2 = new ListNode(1);
        ListNode m2 = new ListNode(2);
        ListNode m3 = new ListNode(3);
        ListNode m4 = new ListNode(4);
        ListNode m5 = new ListNode(5);
        head2.next = m2;
        m2.next = m3;
        m3.next = m4;
        m4.next = m5;

        Solution s = new Solution();
        s.reorderList(head1);
        LinkedListUtils.print(head1);

        s.reorderList(head2);
        LinkedListUtils.print(head2);
    }
}
