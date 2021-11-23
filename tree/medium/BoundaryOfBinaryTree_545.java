package tree.medium;

import utils.TreeNode;

import java.util.*;

/**
 *       1
 *    /    \
 *   2      3
 *  /\     /
 * 4  5    6
 *   /\   / \
 *  3  8 9   10
 * The boundary of a binary tree is the concatenation of the root, the left boundary, the leaves ordered from left-to-right,
 * and the reverse order of the right boundary.
 * The left boundary is the set of nodes defined by the following:
 *     The root node's left child is in the left boundary. If the root does not have a left child, then the left boundary is empty.
 *     If a node in the left boundary and has a left child, then the left child is in the left boundary.
 *     If a node is in the left boundary, has no left child, but has a right child, then the right child is in the left boundary.
 *     The leftmost leaf is not in the left boundary.
 *
 * The right boundary is similar to the left boundary, except it is the right side of the root's right subtree.
 * Again, the leaf is not part of the right boundary, and the right boundary is empty if the root does not have a right child.
 * The leaves are nodes that do not have any children. For this problem, the root is not a leaf.
 * Given the root of a binary tree, return the values of its boundary.
 */
public class BoundaryOfBinaryTree_545 {

    public static void main(String[] args) {
        BoundaryOfBinaryTree_545 s = new BoundaryOfBinaryTree_545();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.left.left.left.left = new TreeNode(8);
        root.right.right.right.right = new TreeNode(9);

        System.out.println(s.boundaryOfBinaryTree(root));
    }

    // O(n) - time
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        TreeNode t = root.left;
        while (t != null) { // left boundary
            if (!isLeaf(t)) {
                res.add(t.val);
            }
            if (t.left != null) {
                t = t.left;
            } else {
                t = t.right;
            }

        }
        addLeaves(res, root); // leaves
        Stack<Integer> s = new Stack<>();
        t = root.right;
        while (t != null) { // right boundary
            if (!isLeaf(t)) {
                s.push(t.val);
            }
            if (t.right != null) {
                t = t.right;
            } else {
                t = t.left;
            }
        }
        while (!s.empty()) {
            res.add(s.pop());
        }
        return res;
    }

    private boolean isLeaf(TreeNode t) {
        return t.left == null && t.right == null;
    }

    private void addLeaves(List<Integer> res, TreeNode root) {
        if (isLeaf(root)) {
            res.add(root.val);
        } else {
            if (root.left != null) {
                addLeaves(res, root.left);
            }
            if (root.right != null) {
                addLeaves(res, root.right);
            }
        }
    }
}
