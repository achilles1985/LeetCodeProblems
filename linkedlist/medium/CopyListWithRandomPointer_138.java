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

    // O(n) - time, O(1) space
    public ListNode copyRandomList(ListNode head) {
        if (head == null) {
            return null;
        }
        // Creating a new weaved list of original and copied nodes.
        ListNode ptr = head;
        while (ptr != null) {
            // Cloned node
            ListNode newNode = new ListNode(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;
        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        ListNode ptr_old_list = head; // A->B->C
        ListNode ptr_new_list = head.next; // A'->B'->C'
        ListNode head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }

        return head_old;
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
