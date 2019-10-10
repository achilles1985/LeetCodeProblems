package linkedlist;

public class LinkedListUtils {

    public static void print(ListNode head) {
        if (head == null) {
            return;
        }

        ListNode current = head;
        while(current.next != null) {
            System.out.print(current.val + "->");
            current = current.next;
        }
        System.out.print(current.val);
        System.out.println();
    }
}
