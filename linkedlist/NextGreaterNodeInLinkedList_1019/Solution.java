package linkedlist.NextGreaterNodeInLinkedList_1019;

import linkedlist.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2, node_3, ... etc.
 Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j.val > node_i.val, and j is the smallest possible choice.
 If such a j does not exist, the next larger value is 0.
 Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a linked list with a head node value of 2, second node value of 1, and third node value of 5.

 Example 1:
 Input: [2,1,5]
 Output: [5,5,0]

 Example 2:
 Input: [2,7,4,3,5]
 Output: [7,0,5,5,0]

 Example 3:
 Input: [1,7,5,1,9,2,5,1]
 Output: [7,9,9,9,0,5,0,0]
 */
public class Solution {

    // O(n) - time, O(1) - space
    public int[] nextLargerNodes(ListNode head) {
        if (head == null || head.next == null) {
            return new int[]{};
        }

        //int i = 0;
        // int[] res = new int[10000];
        List<Integer> res = new ArrayList<>();

        ListNode slow = head;
        ListNode fast = head;
        while (slow != null) {
            if (fast == null) {
                //res[i++] = 0;
                res.add(0);
                slow = slow.next;
                fast = slow;
            } else if (fast.val > slow.val) {
                //res[i++] = fast.val;
                res.add(fast.val);
                slow = slow.next;
                fast = slow;
            } else {
                fast = fast.next;
            }
        }

        //return Arrays.copyOf(res, i);
        return res.stream()
                .mapToInt(x -> x)
                .toArray();
    }
}
