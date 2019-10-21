package tree;

import utils.TreeNode;

/** E
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as:
 *     a binary tree in which the left and right subtrees of every node differ in height by no more than 1.
 *
 * Example 1:
 * Given the following tree [3,9,20,null,null,15,7]:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * Return true.
 *
 * Example 2:
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 */
public class BalancedBinaryTree_110 {

    public static void main(String[] args) {
        BalancedBinaryTree_110 s = new BalancedBinaryTree_110();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        //root.right.right.right = new TreeNode(8);

        System.out.println(s.isBalanced(root));
    }

    // O(n*log(n)) - time, O(n) - space
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        int leftH = height(root.left);
        int rightH = height(root.right);
        if (Math.abs(leftH - rightH) > 1) {
            return false;
        }
        boolean leftBalanced = isBalanced(root.left);
        boolean rightBalanced = isBalanced(root.right);
        if (!leftBalanced || !rightBalanced) {
            return false;
        }
        return true;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }
}
