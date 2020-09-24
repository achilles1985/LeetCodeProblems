package linkedlist.medium;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** M
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 *
 * Example 2:
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */
public class SortList_148 {

    public static void main(String[] args) {
        SortList_148 s = new SortList_148();
        ListNode head = new ListNode(2); //2-3-1-4-5
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LinkedListUtils.print(s.sortList(head));
    }

    // O(n*log(n)) - time, O(log(n)) - space, to store the recursive call stack. The maximum depth of the recursion tree is log(n).
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = middle(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return merge(left, right);
    }

    private ListNode middle(ListNode head) { // actually splits the list into 2 parts and returns the head of the first part
        ListNode prevSlow = null;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prevSlow = slow;
            slow = slow.next;
        }
        if (prevSlow != null) {
            prevSlow.next = null;
        }
        return slow;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }
        curr.next = (left != null) ? left : right;

        return head.next;
    }

}
