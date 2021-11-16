package tree.medium;

import java.util.concurrent.atomic.AtomicInteger;
import utils.TreeNode;

/** M
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input: [1,2,3]
 *     1
 *    / \
 *   2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 *
 * Example 2:
 * Input: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
 */
/*
    1. May I assume the result fits Integer?
 */
public class SumRootToLeafNumbers_129 {

    public static void main(String[] args) {
        SumRootToLeafNumbers_129 s = new SumRootToLeafNumbers_129();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(9);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(1);

        System.out.println(s.sumNumbers(root)); //1026
    }

    // O(n) - time, O(h) - space, h - height of the tree
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        AtomicInteger sum = new AtomicInteger(0); // or int[] sum = new int[1];
        helper(root, 0, sum);

        return sum.intValue();
    }

    // O(n) - time, O(h) - space, h - height of the tree
    public int sumNumbers2(TreeNode root) {
        return helper2(root, 0);
    }

    private void helper(TreeNode root, int num, AtomicInteger sum) {
        if (root == null) {
            return;
        }
        num = num*10 + root.val;
        if (root.left == null && root.right == null) {
            sum.addAndGet(num);
            return;
        }
        helper(root.left, num, sum);
        helper(root.right, num, sum);
    }

    private int helper2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        sum = sum*10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        int left = helper2(root.left, sum);
        int right = helper2(root.right, sum);

        return left + right;
    }
}
