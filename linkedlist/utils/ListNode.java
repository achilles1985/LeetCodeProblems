package linkedlist.utils;

public class ListNode {
    public int val;
    public ListNode next;

    public int getVal() {
        return val;
    }

    public ListNode getNext() {
        return next;
    }

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}
