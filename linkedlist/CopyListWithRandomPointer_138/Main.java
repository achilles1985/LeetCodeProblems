package linkedlist.CopyListWithRandomPointer_138;

public class Main {

    public static void main(String[] args) {
        // 1-2-3-4-5-6, res=1-6-2-5-3-4
        ListNode head1 = new ListNode();
        ListNode a1 = new ListNode();
        ListNode a2 = new ListNode();
        ListNode a3 = new ListNode();
        ListNode a4 = new ListNode();
        // head
        head1.val = 1;
        head1.next = a1;
        head1.random = a2;
        // a1
        a1.val = 2;
        a1.next = a2;
        a1.random = a1;
        // a2
        a2.val = 3;
        a2.next = a3;
        a2.random = a4;
        // a3
        a3.val = 4;
        a3.next = a4;
        a3.random = a1;
        // a4
        a4.val = 5;
        a4.next = null;
        a4.random = a3;


        Solution s = new Solution();
        ListNode head2 = s.copyRandomList(head1);
    }
}
