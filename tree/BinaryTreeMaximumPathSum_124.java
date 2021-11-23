package tree;

import utils.TreeNode;

/**H
 * Given a non-empty binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the root.
 *
 * Example 1:
 * Input: [1,2,3]
 *        1
 *       / \
 *      2   3
 *
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * Example 2:
 * Input: [-10,9,20,null,null,15,7]
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
public class BinaryTreeMaximumPathSum_124 {

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum_124 s = new BinaryTreeMaximumPathSum_124();
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(-1);

        TreeNode root2 = new TreeNode(-2);
        root2.left = new TreeNode(1);

        System.out.println(s.maxPathSum(new TreeNode(-3))); //0
        System.out.println(s.maxPathSum(root)); //42
        System.out.println(s.maxPathSum(root1)); //2
        System.out.println(s.maxPathSum(root2)); //1
    }

    // O(n) - time, O(log(n)) - space
    public int maxPathSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int[] globalMaxHolder = new int[]{Integer.MIN_VALUE};
        helper(root, globalMaxHolder);

        return globalMaxHolder[0];
    }

    private int helper(TreeNode root, int[] globalMaxHolder) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(helper(root.left, globalMaxHolder), 0);
        int right = Math.max(helper(root.right, globalMaxHolder), 0);

        // the price to start a new path where `node` is a highest node
        int newPrice = left + root.val + right;
        // update max_sum if it's better to start a new path
        globalMaxHolder[0] = Math.max(globalMaxHolder[0], newPrice);

        // return the max gain if continue the same path
        return root.val + Math.max(left, right);
    }

}
