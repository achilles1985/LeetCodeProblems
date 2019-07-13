package linkedlist.MergeTwoSortedLists_21;

import linkedlist.ListNode;

public class Solution {

    // O(n+m) - time, O(n+m) - space
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode res;
        if (l1.val <= l2.val) {
            res = new ListNode(l1.val);
            l1 = l1.next;
        } else {
            res = new ListNode(l2.val);
            l2 = l2.next;
        }
        ListNode rc = res;
        ListNode c1 = l1;
        ListNode c2 = l2;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                rc.next = new ListNode(c1.val);
                c1 = c1.next;
            } else {
                rc.next = new ListNode(c2.val);
                c2 = c2.next;
            }
            rc = rc.next;
        }

        while (c1 != null) {
            rc.next = new ListNode(c1.val);
            c1 = c1.next;
            rc = rc.next;
        }
        while (c2 != null) {
            rc.next = new ListNode(c2.val);
            c2 = c2.next;
            rc = rc.next;
        }

        return res;
    }
}
