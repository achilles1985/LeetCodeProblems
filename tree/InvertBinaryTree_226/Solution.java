package tree.InvertBinaryTree_226;

import utils.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/** E
 * Invert a binary tree.
 Example:
 Input:

    4
   /   \
  2     7
 / \   / \
1   3 6   9

 Output:
     4
   /   \
  7     2
 / \   / \
9   6 3   1
 */
public class Solution {

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;

        return root;
    }

    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (! queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null) queue.add(node.left);
            if (node.right != null)  queue.add(node.right);
        }

        return root;
    }
}
