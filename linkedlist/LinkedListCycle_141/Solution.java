package linkedlist.LinkedListCycle_141;

import linkedlist.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a linked list, determine if it has a cycle in it.
 To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

 Example 1:
 Input: head = [3,2,0,-4], pos = 1
 Output: true
 Explanation: There is a cycle in the linked list, where tail connects to the second node.
 Can you solve it using O(1) (i.e. constant) memory?
 */
public class Solution {

    // O(n) time, add one more field to ListNode O(n)
    public boolean hasCycle(ListNodeVisit head) {
        // the list is empty or only one element in it
        if (head == null || head.next == null) {
            return false;
        }

        ListNodeVisit curr = head;

        while (curr.next != null) {
            if (curr.visited) {
                return true;
            }
            curr.visited = true;
            curr = curr.next;
        }

        return false;
    }

    // https://leetcode.com/problems/linked-list-cycle/solution/ (O(n) - memory)
    public boolean hasCycle11(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    // Two pointers: fast and slow, O(1) - space
    public boolean hasCycle2(ListNodeVisit head) {
        // the list is empty or only one element in it
        if (head == null || head.next == null) {
            return false;
        }
        ListNodeVisit slow = head;
        ListNodeVisit fast = head;
        while (fast!= null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.val == fast.val) {
                return true;
            }
        }

        return false;
    }

    // https://leetcode.com/problems/linked-list-cycle/solution/
    public boolean hasCycle22(ListNodeVisit head) {
        // the list is empty or only one element in it
        if (head == null || head.next == null) {
            return false;
        }

        ListNodeVisit slow = head;
        ListNodeVisit fast = head.next;
        while (fast!= slow) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
