package linkedlist.InsertionSortList_147;

import linkedlist.ListNode;

/**
 * Sort a linked list using insertion sort.
 A graphical example of insertion sort. The partial sorted list (black) initially contains only the first element in the list.
 With each iteration one element (red) is removed from the input val and inserted in-place into the sorted list

 Algorithm of Insertion Sort:
 Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 At each iteration, insertion sort removes one element from the input val, finds the location it belongs within the sorted list, and inserts it there.
 It repeats until no input elements remain.

 Example 1:
 Input: 4->2->1->3
 Output: 1->2->3->4

 Example 2:
 Input: -1->5->3->4->0
 Output: -1->0->3->4->5
 */
public class Solution {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head.next;
        while (current != null) {
            ListNode fromHead = head;
            while (current != fromHead) {
                if (current.val < fromHead.val) {
                    swap(current, fromHead);
                    break;
                }
                fromHead = fromHead.next;
            }
            current = current.next;
        }
        return head;
    }

    private void swap(ListNode from, ListNode to) {
        ListNode temp = from.next;
        from.next = to.next;
        to.next = temp;
    }

    public void insertionsSortArray(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    swap(i, j, arr);
                    i = i-1;
                }
            }
        }
    }

    private void swap(int from, int to, int[] arr) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
