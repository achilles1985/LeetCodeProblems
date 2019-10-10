package linkedlist;

/** E
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 * Input: 1->1->2
 * Output: 1->2
 *
 * Example 2:
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */
public class RemoveDuplicatesFromSortedList_83 {

    public static void main(String[] args) {
        RemoveDuplicatesFromSortedList_83 s = new RemoveDuplicatesFromSortedList_83();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);

        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(2);
        head2.next.next.next = new ListNode(2);
        head2.next.next.next.next = new ListNode(2);

        LinkedListUtils.print(s.deleteDuplicates(head));
        LinkedListUtils.print(s.deleteDuplicates(head2));
    }

    // O(n) - time, O(1) - space
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            ListNode temp = curr;
            while (temp != null && temp.next != null && temp.val == temp.next.val) {
                temp = temp.next;
            }
            curr.next = temp.next;
            curr = curr.next;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return head;
    }
}
