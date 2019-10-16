package linkedlist;

/**M
 * A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element
 * in the list.
 * With each iteration one element (red) is removed from the input data and inserted in-place into the sorted list
 *
 * Algorithm of Insertion Sort:
 *     Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 *     At each iteration, insertion sort removes one element from the input data, finds the location it belongs
 *     within the sorted list, and inserts it there.
 *     It repeats until no input elements remain.
 */
public class InsertionSortList_147 {

    public static void main(String[] args) {
        InsertionSortList_147 s = new InsertionSortList_147();
        ListNode head = new ListNode(6);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(5);

        LinkedListUtils.print(s.insertionSortList(head));
    }

    // O(n^2) - time, O(1) - space
    public ListNode insertionSortList(ListNode head) {
        ListNode result = null;
        ListNode curr = head;
        ListNode prevCurr = null;
        while(curr != null){
            prevCurr = curr;
            curr = curr.next;
            prevCurr.next = null;
            result = insert(result,prevCurr);
        }
        return result;
    }

    private ListNode insert(ListNode head, ListNode curr) {
        if(head == null){
            return curr;
        }
        ListNode prev = null;
        ListNode start = head;
        while(start != null && curr.val >= start.val){
            prev = start;
            start = start.next;
        }
        if(prev == null){
            curr.next = head;
            head = curr;
        }else{
            prev.next = curr;
            curr.next = start;
        }
        return head;
    }
}
