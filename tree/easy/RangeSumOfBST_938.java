package tree.easy;

import java.util.Stack;

import utils.TreeNode;

/** E
 * Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R
 * (inclusive).
 * The binary search tree is guaranteed to have unique values.
 *
 * Example 1:
 * Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
 * Output: 32
 *
 * Example 2:
 * Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
 * Output: 23
 *
 * Note:
 *     The number of nodes in the tree is at most 10000.
 *     The final answer is guaranteed to be less than 2^31.
 */
public class RangeSumOfBST_938 {

    public static void main(String[] args) {
        RangeSumOfBST_938 s = new RangeSumOfBST_938();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        System.out.println(s.rangeSumBST(root, 7, 15)); //32
        System.out.println(s.rangeSumBSTIterative(root, 7, 15)); //32
    }

    // O(n) - time, space for not balanced tree
    public int rangeSumBSTIterative(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int sum = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val >= L && node.val <= R) {
                sum += node.val;
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return sum;
    }

    //O(n) - time, space
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        if (root.val >= L && root.val <= R) {
            return root.val + rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        }
        if (root.val < L) {
            return rangeSumBST(root.right, L, R);
        }
        return rangeSumBST(root.left, L, R);
    }

}
