package tree;

import tree.utils.TreeUtils;
import utils.TreeNode;

/** M
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into
 * the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist
 * in the original BST.
 * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 *
 * For example,
 * Given the tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 * And the value to insert: 5
 * You can return this binary search tree:
 *          4
 *        /   \
 *       2     7
 *      / \   /
 *     1   3 5
 *
 * This tree is also valid:
 *          5
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *          \
 *           4
 */
public class InsertIntoBinarySearchTree_701 {

    public static void main(String[] args) {
        InsertIntoBinarySearchTree_701 s = new InsertIntoBinarySearchTree_701();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeUtils.print(s.insertIntoBSTIterative(root, 5));
        //TreeUtils.print(s.insertIntoBST(root, 5));
    }

    // O(n) - time, O(n) - space for skewed tree and O(log(n)) - balanced one
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    // O(n) - time, O(n) - space for skewed tree and O(log(n)) - balanced one
    public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        if (root == null) {
            return  new TreeNode(val);
        }
        TreeNode prev = null; TreeNode curr = root;
        while (curr != null) {
            prev = curr;
            if (val < curr.val) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        if (val < prev.val) {
            prev.left = new TreeNode(val);
        } else {
            prev.right = new TreeNode(val);
        }
        return root;
    }
}
