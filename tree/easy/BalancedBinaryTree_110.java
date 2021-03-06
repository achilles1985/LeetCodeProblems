package tree.easy;

import utils.TreeNode;

/** E [marked]
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
 * Return true.
 *
 * Example 2:
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
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

        System.out.println(s.isBalancedBF(root));
        System.out.println(s.isBalanced(root));
    }

    // O(n*log(n)) - time (height() called repeatedly on the same nodes), O(n) - space
    public boolean isBalancedBF(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }
        return isBalancedBF(root.left) && isBalancedBF(root.right);
    }

    // O(n) - time, O(n) - space (recursion stack may go up to O(n) if the tree is unbalanced)
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root).isBalanced;
    }

    private BalanceHolder checkBalance(TreeNode root) {
        if (root == null) {
            return new BalanceHolder(0, true);
        }
        BalanceHolder leftBalance = checkBalance(root.left);
        if (!leftBalance.isBalanced) {
            return leftBalance;
        }
        BalanceHolder rightBalance = checkBalance(root.right);
        if(!rightBalance.isBalanced) {
            return rightBalance;
        }
        int height = Math.max(leftBalance.height, rightBalance.height) + 1;
        boolean isBalanced = Math.abs(leftBalance.height - rightBalance.height) <= 1;

        return new BalanceHolder(height, isBalanced);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    private static class BalanceHolder {
        private int height;
        private boolean isBalanced;

        public BalanceHolder(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }
    }
}
