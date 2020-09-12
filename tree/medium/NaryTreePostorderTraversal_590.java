package tree.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import tree.utils.Node;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/** E
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 *
 * Follow up:
 *
 * Recursive solution is trivial, could you do it iteratively?
 *
 * Example 1:
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [5,6,3,2,4,1]
 *
 * Example 2:
 * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * Output: [2,6,14,11,7,3,12,8,4,13,9,10,5,1]
 *
 * Constraints:
 *     The height of the n-ary tree is less than or equal to 1000
 *     The total number of nodes is between [0, 10^4]
 */
public class NaryTreePostorderTraversal_590 {

    // O(n) - time, space
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);

        return result;
    }

    // O(n) - time, space
    public List<Integer> postorderIterative(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        LinkedList<Integer> result = new LinkedList<>();
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.pollLast();
            result.addFirst(node.val);
            for (Node n: node.children) {
                queue.add(n);
            }
        }

        return result;
    }

    private void helper(Node root, List<Integer> result) {
        if (root == null) {
            return;
        }
        for (Node c: root.children) {
            helper(c, result);
        }
        result.add(root.val);
    }
}
