package linkedlist.medium;

import java.util.HashSet;
import java.util.Set;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** M [MARKED]
 * Given two singly linked lists there may be list nodes that are common to both.
 * Write a program that takes two cycle-free singly linked lists, and determines if there exists a node that is common to both lists.
 */
public class OverlappingNoCycleLists {

    public static void main(String[] args) {
        OverlappingNoCycleLists s = new OverlappingNoCycleLists();
        ListNode n1 = new ListNode(7);
        ListNode n2 = new ListNode(8);
        ListNode n3 = new ListNode(9);

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = n1;
        head1.next.next.next.next = n2;
        head1.next.next.next.next.next = n3;

        ListNode head2 = new ListNode(4);
        head2.next = new ListNode(5);
        head2.next.next = new ListNode(6);
        head2.next.next.next = n1;
        head2.next.next.next.next = n2;
        head2.next.next.next.next.next = n3;

        LinkedListUtils.print(s.overlappingNoCycleLists(head1, head2)); // 7->8->9
        LinkedListUtils.print(s.overlappingNoCycleLists2(head1, head2)); // 7->8->9
    }

    // O(n) - time, O(n) - space, n - total number of nodes
    public ListNode overlappingNoCycleLists(ListNode L1, ListNode L2) {
        Set<ListNode> set = new HashSet<>();
        ListNode curr1 = L1;
        while (curr1 != null) {
            set.add(curr1);
            curr1 = curr1.next;
        }
        ListNode curr2 = L2;
        while (curr2 != null) {
            if (set.contains(curr2)) {
                return curr2;
            }
            curr2 = curr2.next;
        }
        return null;
    }

    // O(n) - time, O(1) - space, n - total number of nodes
    public ListNode overlappingNoCycleLists2(ListNode L1, ListNode L2) {
        int length1 = calculateLength(L1);
        int length2 = calculateLength(L2);
        if (length1 > length2) {
            L1 = advanceByK(L1, length1 - length2);
         } else {
            L2 = advanceByK(L2, length2 - length1);
        }
        while (L1 != null && L2 != null) {
            if (L1 == L2) {
                return L1;
            }
            L1 = L1.next;
            L2 = L2.next;
        }
        return null;
    }

    private int calculateLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    private ListNode advanceByK(ListNode head, int k) {
        while (k-- > 0) {
            head = head.next;
        }
        return head;
    }
}
