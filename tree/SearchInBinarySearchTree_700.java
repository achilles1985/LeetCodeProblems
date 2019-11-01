package tree;

import java.util.Stack;

import tree.utils.TreeUtils;
import utils.TreeNode;

/** E
 * Given the root node of a binary search tree (BST) and a value. You need to find the node in the BST that the
 * node's value equals the given value. Return the subtree rooted with that node. If such node doesn't exist, you
 * should return NULL.
 * For example,
 * Given the tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 * And the value to search: 2
 * You should return this subtree:
 *       2
 *      / \
 *     1   3
 * In the example above, if we want to search the value 5, since there is no node with value 5, we should return NULL.
 * Note that an empty tree is represented by NULL, therefore you would see the expected output (serialized tree
 * format) as [], not null.
 */
public class SearchInBinarySearchTree_700 {

    public static void main(String[] args) {
        SearchInBinarySearchTree_700 s = new SearchInBinarySearchTree_700();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        TreeUtils.print(s.searchBST(root, 2)); //
        TreeUtils.print(s.searchBSTIterative(root, 7)); //
    }

    // O(n) - time, space
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val < val) {
            return searchBST(root.right, val);
        }
        return searchBST(root.left, val);
    }

    // O(n) - time, space
    public TreeNode searchBSTIterative(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == val) {
                return node;
            }
            if (val < node.val && node.left != null) {
                stack.push(node.left);
            } else if (val > node.val && node.right != null) {
                stack.push(node.right);
            }
        }
        return null;
    }
}
