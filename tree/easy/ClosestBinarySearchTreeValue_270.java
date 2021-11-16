package tree.easy;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** E [marked]
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 * Note:
 *     Given target value is a floating point.
 *     You are guaranteed to have only one unique value in the BST that is closest to the target.
 *
 * Example:
 * Input: root = [4,2,5,1,3], target = 3.714286
 *
 *     4
 *    / \
 *   2   5
 *  / \
 * 1   3
 *
 * Output: 4
 */
/*
    Remember it is BST
 */
public class ClosestBinarySearchTreeValue_270 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        ClosestBinarySearchTreeValue_270 s = new ClosestBinarySearchTreeValue_270();
        System.out.println(s.closestValue(root, 3.714286));
    }

    // O(n) - time, space
    public int closestValueBF(TreeNode root, double target) {
        List<Integer> nums = new ArrayList<>();
        inorder(root, nums);
        return Collections.min(nums, (n1,n2) ->  Math.abs(n1 - target) < Math.abs(n2 - target) ? -1 : 1);
    }

    // O(h) - time, where h - height of the tree, h=log(n) - for balanced, n - skewed one.
    // O(1) - space
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        double min = Math.abs(root.val - target);
        int ans = root.val;
        while (root != null) {
            int val = root.val;
            double diff = Math.abs(val - target);
            if (diff < min) {
                min = diff;
                ans = val;
            }
            root = val < target ? root.right : root.left;
        }
        return ans;
    }

    private void inorder(TreeNode root, List<Integer> nums) {
        if (root == null) return;
        inorder(root.left, nums);
        nums.add(root.val);
        inorder(root.right, nums);
    }
}
