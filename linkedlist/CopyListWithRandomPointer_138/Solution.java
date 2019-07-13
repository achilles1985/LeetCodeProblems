package linkedlist.CopyListWithRandomPointer_138;

import java.util.HashMap;
import java.util.Map;

/**
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
public class Solution {

    // O(n) - time, O(1) - space
    public ListNode copyRandomList(ListNode head) {
        ListNode curr1 = head;
        Map<ListNode, ListNode> randoms = new HashMap<>();
        // populate the map of val->random
        while(curr1 != null) {
            randoms.put(curr1, curr1.random);
            curr1 = curr1.next;
        }

        // clone list of (value, next)
        ListNode cloneHead = cloneSimpleList(head);

        // update cloned list with random
        ListNode curr2 = cloneHead;
        curr1 = head;
        while (curr1 != null) {
            curr2.random = randoms.get(curr1);
            curr2 = curr2.next;
            curr1 = curr1.next;
        }

        return cloneHead;
    }

    public ListNode cloneSimpleList(ListNode head) {
        ListNode curr1 = head;

        ListNode head2 = null;
        ListNode curr2 = null;

        while (curr1 != null) {
            if (head2 == null) {
                head2 = new ListNode(curr1.val);
                curr2 = head2;
            } else {
                curr2.next = new ListNode(curr1.val);
                curr2 = curr2.next;
            }
            curr1 = curr1.next;
        }

        return head2;
    }
}
