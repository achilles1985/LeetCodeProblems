package tree.medium;

import utils.TreeNode;

import java.util.*;

/**M
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
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        if (!isLeaf(root)) {
            res.add(root.val);
        }
        TreeNode curr = root.left; // start left bound from root's left child, not root!
        while (curr != null && !isLeaf(curr)) { // left bound
            res.add(curr.val);
            if (curr.left != null) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        addLeaves(root, res); // leaves
        Deque<Integer> stack = new ArrayDeque<>();
        curr = root.right; // start right bound from root's right child, not root!
        while (curr != null && !isLeaf(curr)) { // right bound (counterclockwise)
            stack.push(curr.val);
            if (curr.right != null) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private void addLeaves(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {
            list.add(root.val);
            return;
        }
        addLeaves(root.left, list);
        addLeaves(root.right, list);
    }
}
