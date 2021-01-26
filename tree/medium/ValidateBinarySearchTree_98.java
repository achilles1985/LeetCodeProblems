package tree.medium;

import utils.TreeNode;

/** M [marked]
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 *     The left subtree of a node contains only nodes with keys less than the node's key.
 *     The right subtree of a node contains only nodes with keys greater than the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 * Example 1:
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 *
 * Example 2:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 */
/*
    if root > root.left && root < root.right is not enough to tell the tree is valid.
    root must be < all nodes of the left subtree and > of all nodes on the right subtree.
 */
public class ValidateBinarySearchTree_98 {

    public static void main(String[] args) {
        ValidateBinarySearchTree_98 s = new ValidateBinarySearchTree_98();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(10);
        root1.right = new TreeNode(15);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(20);

        System.out.println(s.isValidBST2(root1));
    }

    // O(n) - time, O(h) - space
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }

    private boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min || max != null && root.val >= max) {
            return false;
        }
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    // incorrect
    // The problem with that solution is that if root is equal to MIN or MAX, we return false, instead of true
    public boolean isValidBST2(TreeNode root) {
        return isValidBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isValidBST2(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBST2(root.left, min, root.val) && isValidBST2(root.right, root.val, max);
    }


}
