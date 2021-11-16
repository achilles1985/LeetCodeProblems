package tree.medium;

import utils.TreeNode;

/** M [marked]
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 * Example 1:
 * Input:
 *
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 *
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 *
 * Example 2:
 * Input:
 *
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 *
 * Output: 2
 * Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.
 */
public class BinaryTreeLongestConsecutiveSequence_298 {

    private int maxLength;

    public static void main(String[] args) {
        BinaryTreeLongestConsecutiveSequence_298 s = new BinaryTreeLongestConsecutiveSequence_298();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);

        System.out.println(s.longestConsecutive2(root)); //3
    }

    // O(n) - time, space
    // At each step we decide: continue with sequence or start a new one.
    public int longestConsecutive(TreeNode root) {
        return dfsTopDown(root, null, 0);
    }

    public int longestConsecutive2(TreeNode root) {
        dfsButtonUp(root);
        return maxLength;
    }

    private int dfsTopDown(TreeNode p, TreeNode parent, int length) {
        if (p == null) {
            return length;
        }
        length = (parent != null && p.val == parent.val + 1) ? length + 1 : 1;
        int left = dfsTopDown(p.left, p, length);
        int right = dfsTopDown(p.right, p, length);

        return Math.max(length, Math.max(left, right));
    }

    private int dfsButtonUp(TreeNode p) {
        if (p == null) {
            return 0;
        }
        int L = dfsButtonUp(p.left) + 1;
        int R = dfsButtonUp(p.right) + 1;
        if (p.left != null && p.val + 1 != p.left.val) {
            L = 1;
        }
        if (p.right != null && p.val + 1 != p.right.val) {
            R = 1;
        }
        int length = Math.max(L, R);
        maxLength = Math.max(maxLength, length);

        return length;
    }
}
