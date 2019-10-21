package tree;

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
        root.right.right = new TreeNode(15);

        System.out.println(s.rangeSumBST(root, 7, 15));
    }

    //O(n) - time, space
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }
        int sum = (root.val <= R && root.val >= L)? root.val : 0;
        if (root.val > L) {
            sum += rangeSumBST(root.left, L, R);
        }
        if (root.val < R) {
            sum += rangeSumBST(root.right, L, R);
        }
        return sum;
    }

    //O(n) - time, space
    public int rangeSumBSTIterative(TreeNode root, int L, int R) {
        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != null) {
                if (L <= node.val && node.val <= R) {
                    count += node.val;
                }
                if (L < node.val) {
                    stack.push(node.left);
                }
                if (node.val < R) {
                    stack.push(node.right);
                }
            }
        }

        return count;
    }}
