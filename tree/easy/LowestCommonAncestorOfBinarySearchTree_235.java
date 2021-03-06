package tree.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
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
        if (root == null) {
            return null;
        }
        TreeNode min = p;
        TreeNode max = q;
        if (p.val > q.val) {
            min = q;
            max = p;
        }

        return lca(root, min, max);
    }

    private TreeNode lca(TreeNode root, TreeNode min, TreeNode max) {
        if (root.val >= min.val && root.val <= max.val) {
            return root;
        }
        if (root.val < min.val) {
            return lca(root.right, min, max);
        }
        return lca(root.left, min, max);
    }

    // O(n) - time, O(1) - space
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

    public TreeNode lowestCommonAncestorIterative2(TreeNode root, TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return null;
        }
        TreeNode left = p.val < q.val ? p : q;
        TreeNode right = p.val > q.val ? p : q;

        Stack<Integer> s = new Stack<>();
        s.push(1);
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val < left.val) {
                stack.push(node.right);
            } else if (node.val > right.val) {
                stack.push(node.left);
            } else if (node.val >= left.val && node.val <= right.val) {
                return node;
            }
        }
        return null;
    }
}
