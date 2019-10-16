package linkedlist;

import java.util.HashSet;
import java.util.Set;

import linkedlist.utils.ListNode;

/** E
 * Given a linked list, determine if it has a cycle in it.
 * To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in
 * the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
 *
 * Example 1:
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 *
 * Example 2:
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 *
 * Example 3:
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 *
 * Follow up:
 * Can you solve it using O(1) (i.e. constant) memory?
 */
public class LinkedListCycle_141 {

    public static void main(String[] args) {
        LinkedListCycle_141 s = new LinkedListCycle_141();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next;

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);

        System.out.println(s.hasCycle3(head)); // true
        System.out.println(s.hasCycle3(head2)); //false
    }

    // O(n) - time, O(1) - space
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
           fast = fast.next.next;
           slow = slow.next;
           if (fast == slow) {
               return true;
           }
        }
        return false;
    }

    // O(n) - time, O(n) - space
    public boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (set.contains(curr)) {
                return true;
            }
            set.add(curr);
            curr = curr.next;
        }
        return false;
    }

    public boolean hasCycle3(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode cur1 = head;
        while (cur1 != null) {
            ListNode cur2 = head;
            while (cur2 != cur1) {
                if (cur1.next == cur2) {
                    return true;
                }
                cur2 = cur2.next;
            }
            cur1 = cur1.next;
        }
        return false;
    }
}
