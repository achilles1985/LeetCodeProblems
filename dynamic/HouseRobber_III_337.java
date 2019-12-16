package dynamic;

// https://leetcode.com/problems/house-robber-iii/

import utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** M
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root."
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that
 * "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:
 Input: [3,2,3,null,3,null,1]
   3
  / \
 2   3
 \   \
  3   1
 Output: 7
 Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.

 Example 2:
 Input: [3,4,5,1,3,null,1]
     3
    / \
   4   5
 / \   \
1   3   1
 Output: 9
 Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobber_III_337 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(1);

        root.left = n1;
        root.right = n2;
        root.left.left = n3;
        root.left.right = n4;
        root.right.right = n5;

        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);

        HouseRobber_III_337 s = new HouseRobber_III_337();
        System.out.println(s.rob(root)); // 9
        System.out.println(s.rob(root1)); // 1
        System.out.println(s.rob(root2)); // 2
    }

    // O(2^n) - time, O(n) - space
    // http://buttercola.blogspot.com/2016/06/leetcode-house-robber-iii.html
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }


        // case1: rob the root
        int leftMax = 0;
        int rightMax = 0;

        if (root.left != null) {
            leftMax = rob(root.left.left) + rob(root.left.right);
        }

        if (root.right != null) {
            rightMax = rob(root.right.left) + rob(root.right.right);
        }

        int maxRoot = root.val + leftMax + rightMax;

        // case 2: not rob the root
        leftMax = rob(root.left);
        rightMax = rob(root.right);

        int maxNoRoot = leftMax + rightMax;

        return Math.max(maxRoot, maxNoRoot);
    }

    // O(n) - time, space
    // http://buttercola.blogspot.com/2016/06/leetcode-house-robber-iii.html
    public int robDynamic(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int[] result = robHelper(root);
        return Math.max(result[0], result[1]);
    }

    private int[] robHelper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }

        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);

        int[] curr = new int[2];
        curr[0] = root.val + left[1] + right[1];
        curr[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return curr;
    }
}
