package tree.easy;

import java.util.LinkedList;
import java.util.Queue;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**E
 * Invert a binary tree.
 *
 * Example:
 * Input:
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * Output:
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * Trivia:
 * This problem was inspired by this original tweet by Max Howell:
 *     Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a
 *     whiteboard so f*** off.
 */
public class InvertBinaryTree_226 {

    public static void main(String[] args) {
        InvertBinaryTree_226 s = new InvertBinaryTree_226();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        TreeUtils.print(s.invertTreeIterative(root));
        TreeUtils.print(s.invertTree(root));
    }

    // O(n) - time, space
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

    // O(n) - time, space
    public TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = null;
            TreeNode right = null;
            if (node.left != null) {
                left = node.left;
                queue.add(node.left);
            }
            if (node.right != null) {
                right = node.right;
                queue.add(node.right);
            }
            node.left = right;
            node.right = left;
        }
        return root;
    }
}
