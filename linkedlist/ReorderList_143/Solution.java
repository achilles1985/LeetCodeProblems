package linkedlist.ReorderList_143;

import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
 reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
 You may not modify the values in the list's nodes, only nodes itself may be changed.

 Example 1:
 Given 1->2->3->4, reorder it to 1->4->2->3.

 Example 2:
 Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
 */
public class Solution {

    public void reorderList(ListNode head) {
        List<ListNode> pointers = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            pointers.add(curr);
            curr = curr.next;
        }

        curr = head;
        int size = pointers.size() - 1;
        while (curr != null && curr.next != null) {
            ListNode tail = pointers.get(size);
            ListNode beforeTail = pointers.get(size-1);
            beforeTail.next = null;
            tail.next = curr.next;
            curr.next = tail;
            curr = tail.next;
            size--;
        }
    }

}
