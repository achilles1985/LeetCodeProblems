package tree;

import java.util.LinkedList;
import java.util.Queue;

import utils.TreeNode;

/**
 * E
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * 1
 * /  \
 * 2    2
 * / \   / \
 * 3  4  4   3
 * <p>
 * But the following [1,2,2,null,3,null,3] is not:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
public class SymmetricTree_101 {

    public static void main(String[] args) {
        SymmetricTree_101 s = new SymmetricTree_101();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        System.out.println(s.isSymmetric(root)); // false
    }

    // O(n) - time, space
    public boolean isSymmetric(TreeNode root) {
        return root == null || checkSymmetric(root.left, root.right);
    }

    // O(n) - time, space
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.add(root.left);
        q1.add(root.right);
        while (!q1.isEmpty()) {
            TreeNode n1 = q1.poll();
            TreeNode n2 = q1.poll();
            if (n1 == null & n2 == null) {
                continue;
            }
            if (n1 == null || n2 == null) {
                return false;
            }
            if (n1.val != n2.val) {
                return false;
            }
            q1.add(n1.left);
            q1.add(n2.right);
            q1.add(n1.right);
            q1.add(n2.left);
        }
        return q1.isEmpty();
    }

    private boolean checkSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        if (r1 != null && r2 != null) { // do not have to check r1.left.val == r2.right.val, just compare roots values
            return r1.val == r2.val && checkSymmetric(r1.left, r2.right) && checkSymmetric(r1.right, r2.left);
        }
        return false;
    }
}
