package tree.BinaryTreePostorderTraversal_145;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/** H
 * Given a binary tree, return the postorder traversal of its nodes' values.
 Example:
 Input: [1,null,2,3]
    1
     \
      2
     /
    3
 Output: [3,2,1]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversal(root, result);

        return result;
    }

    private void postorderTraversal(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        postorderTraversal(node.left, result);
        postorderTraversal(node.right, result);
        result.add(node.val);
    }

    public List<Integer> postorderTraversalIterative(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> valStack = new Stack<>();
        nodeStack.push(root);
        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            valStack.push(node.val);
            if (node.left != null) nodeStack.push(node.left);
            if (node.right != null) nodeStack.push(node.right);
        }

        List<Integer> result = new ArrayList<>();
        while (!valStack.isEmpty()) {
            result.add(valStack.pop());
        }

        return result;
    }
}
