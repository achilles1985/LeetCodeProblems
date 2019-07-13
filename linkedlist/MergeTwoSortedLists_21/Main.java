package linkedlist.MergeTwoSortedLists_21;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

public class Main {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;

        ListNode n11 = new ListNode(1);
        ListNode n22 = new ListNode(3);
        ListNode n33 = new ListNode(4);
        n11.next = n22;
        n22.next = n33;

        Solution s = new Solution();
        ListNode mergedList = s.mergeTwoLists(n1, n11);
        LinkedListUtils.print(mergedList);
    }
}
