package linkedlist.PartitionList_86;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

public class Main {

    public static void main(String[] args) {
        // 1-4-3-2-5-2, x=3, res=1-2-2-4-3-5
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        int x = 3;

        Solution s = new Solution();
        ListNode result1 = s.partition(n1, x);
        LinkedListUtils.print(result1);
    }
}
