package tree;

import utils.TreeNode;

/** M
 * Given a binary tree, find size of largest binary search subtree in this
 * binary tree.
 *
 * Traverse tree in post order fashion. Left and right nodes return 4 piece
 * of information to root which isBST, size of max BST, min and max in those
 * subtree.
 * If both left and right subtree are BST and this node data is greater than max
 * of left and less than min of right then it returns to above level left size +
 * right size + 1 and new min will be min of left side and new max will be max of
 * right side.
 *
 * Example
 *       5
 *     /  \
 *    2    4
 *  /  \
 * 1    3
 * Answer: 3 (2-1-3)
 * References:
 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 * https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTsubtree {

    public static void main(String[] args) {
        LargestBSTsubtree s = new LargestBSTsubtree();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        System.out.println(s.largestBST(root)); //3
    }

    // O(n^2) - time for skewed tree
    public int largestBST(TreeNode root){
        if (isBST(root)) {
            return size(root);
        }
        return Math.max(largestBST(root.left), largestBST(root.right));
    }

    private int size(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.left) + 1;
    }

    private boolean isBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min) || (max != null && root.val > max)) {
            return false;
        }
        if (!isValidBST(root.left, min, root.val) || !isValidBST(root.right, root.val, max)) {
            return false;
        }
        return true;
    }
}
