package linkedlist.CopyListWithRandomPointer_138;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode random;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int _val, ListNode _next, ListNode _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}
