package tree.easy;

import java.util.concurrent.atomic.AtomicInteger;
import utils.TreeNode;

/** E
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

    // O(h) - time, where h - height of the tree, h=log(n) - for balanced, n - skewed one.
    // O(1) - space
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }
        int closest = root.val;
        double minDiff = Math.abs(root.val - target);
        while (root != null) {
            double curDiff = Math.abs(root.val - target);
            if (curDiff < minDiff) {
                minDiff = curDiff;
                closest = root.val;
            }
            root = root.val <= target ? root.right : root.left;
        }

        return closest;
    }

}
