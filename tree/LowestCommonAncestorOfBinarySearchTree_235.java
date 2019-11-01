package tree;

import utils.TreeNode;

/** E
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q
 * as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 * Example 1:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 *            6
 *         /    \
 *        2      8
 *       / \    /  \
 *      0   4  7    9
 *         / \
 *        3   5
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * Example 2:
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA
 * definition.
 *
 * Note:
 *     All of the nodes' values will be unique.
 *     p and q are different and both values will exist in the BST.
 */
public class LowestCommonAncestorOfBinarySearchTree_235 {

    public static void main(String[] args) {
        LowestCommonAncestorOfBinarySearchTree_235 s = new LowestCommonAncestorOfBinarySearchTree_235();
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.right = new TreeNode(9);
        root.right.left = new TreeNode(7);

        System.out.println(s.lowestCommonAncestor(root, new TreeNode(2), new TreeNode(8))); //6
        System.out.println(s.lowestCommonAncestorIterative(root, new TreeNode(2), new TreeNode(8))); //6
    }

    // O(n) - time, space
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < Math.min(p.val, q.val)) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (root.val > Math.max(p.val, q.val)) {
            return lowestCommonAncestor(root.left, p, q);
        }
        return root;
    }

    // O(n) - time, space
    public TreeNode lowestCommonAncestorIterative(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (root.val < Math.min(p.val, q.val)) {
                root = root.right;
            } else if (root.val > Math.max(p.val, q.val)) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }
}
