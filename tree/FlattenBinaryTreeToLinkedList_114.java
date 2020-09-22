package tree;

import tree.utils.TreeUtils;
import utils.TreeNode;

/** M
 * Given a binary tree, flatten it to a linked list in-place.
 * For example, given the following tree:
 *     1
 *    / \
 *   2    5
 *  / \   / \
 * 3   4  6  7
 *
 * The flattened tree should look like:
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *            \
 *             7
 */
public class FlattenBinaryTreeToLinkedList_114 {

    public static void main(String[] args) {
        FlattenBinaryTreeToLinkedList_114 s = new FlattenBinaryTreeToLinkedList_114();
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);

        s.flattenBF(root1);
        TreeUtils.print(root1);
    }

    // O(n) - time, O(h) - space
    public void flattenBF(TreeNode root) {
        helper(root);
    }

    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        if (rightLast != null) {
            return rightLast;
        }
        if (leftLast != null) {
            return leftLast;
        }
        return root;
    }

    // O(n) - time, O(1) - space
    public void flatten(TreeNode root) {
        // Handle the null scenario
        if (root == null) {
            return;
        }

        TreeNode node = root;
        while (node != null) {
            // If the node has a left child
            if (node.left != null) {
                // Find the rightmost node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            // move on to the right side of the tree
            node = node.right;
        }
    }
}
