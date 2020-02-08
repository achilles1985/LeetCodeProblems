package tree;

import utils.TreeNode;

/**M
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child
 * connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 * Example 1:
 * Input:
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * Output:3
 * Explanation:
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *
 * Example 2:
 * Input:
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 * Output:2
 * Explanation:
 * Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
 */
/*
Questions:
    1. Duplicates exists?
    2. Strictly consecutive 1-2-3, not 2-1-3?
 */
/*
Mistakes:
    1. Consider not only left/right max of subtrees, but also current max.
 */
public class BinaryTreeLongestConsecutiveSequence_595 {

    public static void main(String[] args) {
        BinaryTreeLongestConsecutiveSequence_595 s = new BinaryTreeLongestConsecutiveSequence_595();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        root.right.left = new TreeNode(2);

        System.out.println(s.longestConsecutive(root)); //3
    }

    // O(n) - time, O(h) - space
    public int longestConsecutive(TreeNode root) {
        return longestConsecutiveHelper(root, null, 0);
    }

    private int longestConsecutiveHelper(TreeNode current, TreeNode parent, int max) {
        if (current == null) {
            return 0;
        }
        int localMax = (parent != null && (parent.val == current.val - 1)) ? max + 1 : 1;
        int leftMax = longestConsecutiveHelper(current.left, current, localMax);
        int rightMax = longestConsecutiveHelper(current.right, current, localMax);

        return Math.max(localMax, Math.max(leftMax, rightMax));
    }

}
