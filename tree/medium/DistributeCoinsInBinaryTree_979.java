package tree.medium;

import utils.TreeNode;

/** M
 * You are given the root of a binary tree with n nodes where each node in the tree has node.val coins. There are n coins in total throughout the whole tree.
 * In one move, we may choose two adjacent nodes and move one coin from one node to another. A move may be from parent to child, or from child to parent.
 * Return the minimum number of moves required to make every node have exactly one coin.

 *
 * Example 1:
 * Input: root = [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
 *
 * Example 2:
 * Input: root = [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root [taking two moves]. Then, we move one coin from the root of the tree to the right child.
 *
 * Constraints:
 *     The number of nodes in the tree is n.
 *     1 <= n <= 100
 *     0 <= Node.val <= n
 *     The sum of all Node.val is n.
 */
// https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/221939/C%2B%2B-with-picture-post-order-traversal
// https://leetcode.com/problems/distribute-coins-in-binary-tree/discuss/222026/Java-Post-order-Traversal-with-Explanation
public class DistributeCoinsInBinaryTree_979 {

    public static void main(String[] args) {
        DistributeCoinsInBinaryTree_979 s = new DistributeCoinsInBinaryTree_979();
    }

    // O(n) - time, O(h) - space
    public int distributeCoins(TreeNode root) {
        int[] steps = new int[1];
        helper(root, steps);

        return steps[0];
    }

    public int helper(TreeNode root, int[] steps) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, steps);
        int right = helper(root.right, steps);
        steps[0] += Math.abs(left) + Math.abs(right);

        return root.val + left + right - 1;
    }
}
