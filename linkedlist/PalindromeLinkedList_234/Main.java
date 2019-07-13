package linkedlist.PalindromeLinkedList_234;

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
        System.out.println(s.isPalindrome2(a1));

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        ListNode b3 = new ListNode(3);
        ListNode b4 = new ListNode(2);
        ListNode b5 = new ListNode(1);
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;
        b4.next = b5;
        System.out.println(s.isPalindrome2(b1));

        ListNode c1 = new ListNode(1);
        ListNode c2 = new ListNode(2);
        ListNode c3 = new ListNode(2);
        ListNode c4 = new ListNode(1);
        c1.next = c2;
        c2.next = c3;
        c3.next = c4;
        System.out.println(s.isPalindrome2(c1));
    }
}
