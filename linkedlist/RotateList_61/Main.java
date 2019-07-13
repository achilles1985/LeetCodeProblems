package linkedlist.RotateList_61;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        b1.next = b2;
        b2.next = b3;

        ListNode res1 = s.rotateRight(n1, 5);
        ListNode res2 = s.rotateRight(b1, 6);
        LinkedListUtils.print(res1);
        LinkedListUtils.print(res2);
    }
}
