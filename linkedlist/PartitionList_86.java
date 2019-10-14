package linkedlist;

/** M
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or
 * equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */
public class PartitionList_86 {

    public static void main(String[] args) {
        PartitionList_86 s = new PartitionList_86();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);

        LinkedListUtils.print(s.partition2(head, 3)); // 1->2->2->4->3->5
    }

    // O(n) - time, O(1) - space
    public ListNode partition2(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode less = new ListNode(0);
        ListNode greater = new ListNode(0);
        ListNode lessIter = less;
        ListNode greaterIter = greater;
        while (head != null) {
            if (head.val < x) {
                lessIter.next = head;
                lessIter = lessIter.next;
            }  else {
                greaterIter.next = head;
                greaterIter = greaterIter.next;
            }
            head = head.next;
        }
        greaterIter.next = null;
        lessIter.next = greater.next;

        return less.next;
    }

    // O(n) - time, O(n) - space
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode less = new ListNode(0);
        ListNode greater = new ListNode(0);
        ListNode lessIter = less;
        ListNode greaterIter = greater;
        ListNode curr = head;
        while (curr != null) {
            if (curr.val < x) {
                lessIter.next = new ListNode(curr.val);
                lessIter = lessIter.next;
            }  else {
                greaterIter.next = new ListNode(curr.val);
                greaterIter = greaterIter.next;
            }
            curr = curr.next;
        }
/*        if (less.next == null) { // redundant, even though it's null, we connect it with not null greater list.
            return head;
        }*/
        lessIter.next = greater.next;
        return less.next;
    }

}
