package tree;

import java.util.LinkedList;
import java.util.Stack;

import utils.TreeNode;

/** E
 * Given two binary trees, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical and the nodes have the same value.
 *
 * Example 1:
 * Input:     1         1
 *           / \       / \
 *          2   3     2   3
 *
 *         [1,2,3],   [1,2,3]
 * Output: true
 *
 * Example 2:
 * Input:     1         1
 *           /           \
 *          2             2
 *
 *         [1,2],     [1,null,2]
 * Output: false
 *
 * Example 3:
 * Input:     1         1
 *           / \       / \
 *          2   1     1   2
 *
 *         [1,2,1],   [1,1,2]
 * Output: false
 */
public class SameTree_100 {

    public static void main(String[] args) {
        SameTree_100 s = new SameTree_100();
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.left.left = new TreeNode(8);
        root1.left.right = new TreeNode(10);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(3);
        root2.right = new TreeNode(9);
        root2.left.left = new TreeNode(8);
        root2.left.right = new TreeNode(10);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        System.out.println(s.isSameTree(root1, root2));
        System.out.println(s.isSameTreeIterative(root1, root2));
    }

    // O(n) - time, O(n) space if tree is completely unbalanced, O(log(n)) - is balanced
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        boolean left = isSameTree(p.left, q.left);
        boolean right = isSameTree(p.right, q.right);
        return left && right;
    }

    // O(n) - time, O(n) space if tree is completely unbalanced, O(log(n)) - is balanced
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (!check(p, q)) {
            return false;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(p);
        s2.push(q);
        while (!s1.isEmpty()) {
            TreeNode n1 = s1.pop();
            TreeNode n2 = s2.pop();
            if (!check(n1, n2)) {
                return false;
            }
            if (n1 != null) {
                s1.push(n1.left);
                s1.push(n1.right);
            }
            if (n2 != null) {
                s2.push(n2.left);
                s2.push(n2.right);
            }
        }
        return true;
    }

    private boolean check(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        return n1.val == n2.val;
    }
}
