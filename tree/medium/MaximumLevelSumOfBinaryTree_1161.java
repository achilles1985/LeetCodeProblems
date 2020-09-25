package tree.medium;

import java.util.LinkedList;
import java.util.Queue;

import utils.TreeNode;

/**M
 * Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.
 * Return the smallest level X such that the sum of all the values of nodes at level X is maximal.
 *
 * Example 1:
 *      1
 *    / \
 *   7   0
 *  / \
 * 7   -8
 * Input: [1,7,0,7,-8,null,null]
 * Output: 2
 * Explanation:
 * Level 1 sum = 1.
 * Level 2 sum = 7 + 0 = 7.
 * Level 3 sum = 7 + -8 = -1.
 * So we return the level with the maximum sum which is level 2.
 *
 * Note:
 *     The number of nodes in the given tree is between 1 and 10^4.
 *     -10^5 <= node.val <= 10^5
 */
public class MaximumLevelSumOfBinaryTree_1161 {

    public static void main(String[] args) {
        MaximumLevelSumOfBinaryTree_1161 s = new MaximumLevelSumOfBinaryTree_1161();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(7);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(-8);
        root.right = new TreeNode(0);

        System.out.println(s.maxLevelSum(root)); //2
    }

    // O(n) - time, space
    public int maxLevelSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxSum = 0, minLevel = 1, level  = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;
            while (size-- > 0) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (sum > maxSum) {
                maxSum = sum;
                minLevel = level;
            }
            level++;
        }
        return minLevel;
    }
}
