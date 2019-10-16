package linkedlist;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

// Element of Programming interview in Java, p 116
public class ReverseSublist {

    /*
    Mistake: Make sure what the result should looks like before creating an algorithms.
     */
    public static void main(String[] args) {
        ReverseSublist s = new ReverseSublist();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next = new ListNode(9);

        LinkedListUtils.print(s.reverseSublist(head, 4, 7)); // 1->2->3->7->6->5->4->8->9
    }

    // O(n) - time, O(1) - space
    public ListNode reverseSublist (ListNode head, int start, int finish) {
        if (start == finish) { // No need to reverse since start == finish.
            return head;
        }
        int count = 1;
        ListNode beforeStartNode = head;
        ListNode curr = head;
        while (curr != null && count++ < start) {
            beforeStartNode = curr;
            curr = curr.next;
        }

        ListNode startNode = curr;
        while (count++ < finish) {
            ListNode temp = startNode.next;
            startNode.next = temp.next;
            temp.next = beforeStartNode.next;
            beforeStartNode.next = temp;
        }

        return head;
    }
}
