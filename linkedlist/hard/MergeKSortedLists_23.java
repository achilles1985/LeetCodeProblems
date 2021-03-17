package linkedlist.hard;

import java.util.PriorityQueue;
import java.util.Queue;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/**H
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 *
 * Example:
 * Input:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLists_23 {

    public static void main(String[] args) {
        MergeKSortedLists_23 s = new MergeKSortedLists_23();
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(4);
        head1.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        ListNode head4 = new ListNode(-2);
        head4.next = new ListNode(-1);
        head4.next.next = new ListNode(-1);
        head4.next.next.next = new ListNode(-1);

        //ListNode result = s.mergeKLists(new ListNode[]{head1, head2, head3});
        //ListNode result = s.mergeKLists(new ListNode[]{head4, null});
        ListNode result = s.mergeKLists(new ListNode[]{null});
        LinkedListUtils.print(result);
    }

    // O(n*log(n)) - time, O(n) - space
    public ListNode mergeKListsBF(ListNode[] lists) {
        // Convert to an array, sort it and create a linked list from it.
        return null;
    }

    // O(n*log(k)) - time, O(k) - space, where k - number of lists, n - total nodes
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Queue<ListNode> queue = new PriorityQueue<>((n1,n2) -> n1.val - n2.val);
        for (ListNode head: lists) {
            if (head != null) {
                queue.add(head);
            }
        }
        ListNode head = new ListNode(0);
        ListNode it = head;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            it.next = node;
            if (node.next != null) {
                queue.add(node.next);
            }
            it = it.next;
        }

        return head.next;
    }

}
