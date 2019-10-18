package tree;

import utils.TreeNode;

/** E
 *  * Example:
 *  * Given a binary tree
 *  *
 *  *           1
 *  *          / \
 *  *         2   3
 *  *        / \
 *  *       4   5
 */
public class HeightOfBinaryTree {

    public static void main(String[] args) {
        HeightOfBinaryTree s = new HeightOfBinaryTree();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(s.height(root));
    }

    // O(n) - time, O(n) - space if tree is sparing and O(log(n)) - for full, complete, perfect ones
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }
}
