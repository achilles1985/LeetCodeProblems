package linkedlist;

import java.util.Arrays;
import java.util.Stack;

import utils.SolutionUtils;

/** M
 * We are given a linked list with head as the first node.  Let's number the nodes in the list: node_1, node_2,
 * node_3, ... etc.
 * Each node may have a next larger value: for node_i, next_larger(node_i) is the node_j.val such that j > i, node_j
 * .val > node_i.val, and j is the smallest possible choice.  If such a j does not exist, the next larger value is 0.
 * Return an array of integers answer, where answer[i] = next_larger(node_{i+1}).
 * Note that in the example inputs (not outputs) below, arrays such as [2,1,5] represent the serialization of a
 * linked list with a head node value of 2, second node value of 1, and third node value of 5.
 *
 * Example 1:
 * Input: [2,1,5]
 * Output: [5,5,0]
 *
 * Example 2:
 * Input: [2,7,4,3,5]
 * Output: [7,0,5,5,0]
 *
 * Example 3:
 * Input: [1,7,5,1,9,2,5,1]
 * Output: [7,9,9,9,0,5,0,0]
 *
 * Note:
 *     1 <= node.val <= 10^9 for each node in the linked list.
 *     The given list has length in the range [0, 10000].
 */
public class NextGreaterNodeInLinkedList_1019 {

    public static void main(String[] args) {
        NextGreaterNodeInLinkedList_1019 s = new NextGreaterNodeInLinkedList_1019();
        ListNode head = new ListNode(2);
        head.next = new ListNode(7);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(5);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(7);
        head2.next.next = new ListNode(5);
        head2.next.next.next = new ListNode(1);
        head2.next.next.next.next = new ListNode(9);
        head2.next.next.next.next.next = new ListNode(2);
        head2.next.next.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next.next.next = new ListNode(1);

        SolutionUtils.print(s.nextLargerNodes(head)); //[7,0,5,5,0]
        SolutionUtils.print(s.nextLargerNodes(head2)); //[7,9,9,9,0,5,0,0]
    }

    // O(n) - time, O(n) - space
    public int[] nextLargerNodes(ListNode head) {
        if (head == null || head.next == null) {
            return new int[]{0};
        }
        ListNode curr = head;
        int[] res = new int[10000];
        Stack<int[]> stack = new Stack<>();
        int count = 0;
        while (curr != null) {
            while (!stack.isEmpty() && curr.val > stack.peek()[0]) {
                int[] item = stack.pop();
                res[item[1]] = curr.val;
            }
            stack.push(new int[] {curr.val, count++});
            curr = curr.next;
        }
        return Arrays.copyOf(res, count);
    }
}
