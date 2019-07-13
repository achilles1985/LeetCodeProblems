package linkedlist.InsertionSortList_147;

import linkedlist.LinkedListUtils;
import linkedlist.ListNode;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        Solution s = new Solution();
        s.insertionSortList(n1);
        LinkedListUtils.print(n1);

        int[] arr = {4, 2, 1, 3, 7, 5, 11, 10};
        s.insertionsSortArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
