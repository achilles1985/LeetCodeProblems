package linkedlist.medium;

import linkedlist.utils.LinkedListUtils;
import linkedlist.utils.ListNode;

/** M [MARKED]
 * Given a linked list, rotate the list to the right by k places, where k is non-negative.
 *
 * Example 1:
 * Input: 1->2->3->4->5->NULL, k = 2
 * Output: 4->5->1->2->3->NULL
 * Explanation:
 * rotate 1 steps to the right: 5->1->2->3->4->NULL
 * rotate 2 steps to the right: 4->5->1->2->3->NULL
 *
 * Example 2:
 * Input: 0->1->2->NULL, k = 4
 * Output: 2->0->1->NULL
 * Explanation:
 * rotate 1 steps to the right: 2->0->1->NULL
 * rotate 2 steps to the right: 1->2->0->NULL
 * rotate 3 steps to the right: 0->1->2->NULL
 * rotate 4 steps to the right: 2->0->1->NULL
 */
public class RotateList_61 {

    public static void main(String[] args) {
        RotateList_61 s = new RotateList_61();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);

        LinkedListUtils.print(s.rotateRight(head, 2)); //4-5-1-2-3
        LinkedListUtils.print(s.rotateRight(head2, 5)); //2-3-1
    }

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        int len = length(head);
        int pos = k%len;
        if (len == k || pos == 0) {
            return head;
        }
        ListNode fast = head;
        while (--pos > 0) {
            fast = fast.next;
        }
        ListNode prev = null;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }
        fast.next = head;
        head = slow;
        prev.next = null;
        return head;
    }

    private int length(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
