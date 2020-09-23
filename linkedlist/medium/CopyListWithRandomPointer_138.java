package linkedlist.medium;

import java.util.HashMap;
import java.util.Map;

/** M
 A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 Return a deep copy of the list.

 Example 1:
 Input:
 {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

 Explanation:
 Node 1's value is 1, both of its next and random pointer points to Node 2.
 Node 2's value is 2, its next pointer points to null and its random pointer points to itself.

 Note:
 You must return the copy of the given head as a reference to the cloned list.
 */
public class CopyListWithRandomPointer_138 {

    public static void main(String[] args) {
        CopyListWithRandomPointer_138 s = new CopyListWithRandomPointer_138();
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        head.next = n1;
        head.random = n3;
        n1.next = n2;
        n1.random = n1;
        n2.next = n3;

        ListNode clone = s.copyRandomList2(head);
    }

    // O(n) - time, space
    public ListNode copyRandomList2(ListNode head) {
        if (head == null) {
            return null;
        }
        Map<ListNode, ListNode> map = new HashMap();
        ListNode curr = head;
        while (curr != null) {
            map.put(curr, new ListNode(curr.val));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            map.get(curr).next = map.get(curr.next);
            map.get(curr).random = map.get(curr.random);
            curr = curr.next;
        }
        return map.get(head);
    }

    // O(n) - time, space
    public ListNode copyRandomList(ListNode head) {
        ListNode curr = head;
        ListNode dummy = new ListNode(0);
        ListNode dummyIter = dummy;
        Map<ListNode, ListNode> map = new HashMap();
        while (curr != null) {
            ListNode clone = new ListNode(curr.val);
            map.put(curr, clone);
            dummyIter.next = clone;
            dummyIter = dummyIter.next;
            curr = curr.next;
        }

        ListNode cloneIter = dummy.next;
        while (head != null) {
            ListNode clone = map.get(head.random);
            cloneIter.random = clone;
            cloneIter = cloneIter.next;
            head = head.next;
        }

        return dummy.next;
    }

    public static class ListNode {
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
}
