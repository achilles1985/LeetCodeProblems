package linkedlist.NextGreaterNodeInLinkedList_1019;

import linkedlist.ListNode;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        // 2,1,3
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;

        // 2,7,4,3,5
        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(7);
        ListNode a3 = new ListNode(4);
        ListNode a4 = new ListNode(3);
        ListNode a5 = new ListNode(5);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        // 1,7,5,1,9,2,5,1
        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(7);
        ListNode b3 = new ListNode(5);
        ListNode b4 = new ListNode(1);
        ListNode b5 = new ListNode(9);
        ListNode b6 = new ListNode(2);
        ListNode b7 = new ListNode(5);
        ListNode b8 = new ListNode(1);
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
        b4.next = b5;
        b5.next = b6;
        b6.next = b7;
        b7.next = b8;

        Solution s = new Solution();
        System.out.println(Arrays.toString(s.nextLargerNodes(n1)));
        System.out.println(Arrays.toString(s.nextLargerNodes(a1)));
        System.out.println(Arrays.toString(s.nextLargerNodes(b1)));
    }
}
