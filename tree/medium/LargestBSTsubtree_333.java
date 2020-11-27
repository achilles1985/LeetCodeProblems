package tree.medium;

import utils.TreeNode;

/** M
 Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
 Note:
 A subtree must include all of its descendants.

 Example:
 Input: [10,5,15,1,8,null,7]

      10
     / \
    5  15
   / \   \
  1   8   7

 Output: 3 [1-5-8]
 Explanation: The Largest BST Subtree in this case is the highlighted one.
 The return value is the subtree's size, which is 3.
 Follow up:
 Can you figure out ways to solve it with O(n) time complexity?

 *
 * Traverse tree in post order fashion. Left and right nodes return 4 piece
 * of information to root which isBST, size of max BST, min and max in those
 * subtree.
 * If both left and right subtree are BST and this node data is greater than max
 * of left and less than min of right then it returns to above level left size +
 * right size + 1 and new min will be min of left side and new max will be max of
 * right side.
 *
 * Example
 *       5
 *     /  \
 *    2    4
 *  /  \
 * 1    3
 * Answer: 3 (2-1-3)
 * References:
 * http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
 * https://leetcode.com/problems/largest-bst-subtree/
 */
public class LargestBSTsubtree_333 {

    public static void main(String[] args) {
        LargestBSTsubtree_333 s = new LargestBSTsubtree_333();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(14);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(8);
        root1.right.left = new TreeNode(12);
        root1.right.right = new TreeNode(16);
        root1.left.left.left = new TreeNode(4);
        root1.left.left.right = new TreeNode(6);
        root1.right.left.left = new TreeNode(10);
        root1.right.left.right = new TreeNode(13);

        System.out.println(s.largestBST_BF(root)); //3
        System.out.println(s.largestBST(root)); //3
    }

    // O(n^2) - time for skewed tree
    public int largestBST_BF(TreeNode root){
        if (isBST(root)) {
            return size(root);
        }
        return Math.max(largestBST_BF(root.left), largestBST_BF(root.right));
    }

    // O(n) - time for skewed tree
    public int largestBST(TreeNode root){
        return largest(root).size;
    }

    private MinMax largest(TreeNode root){
        if(root == null){
            return new MinMax();
        }
        MinMax leftMinMax = largest(root.left);
        MinMax rightMinMax = largest(root.right);

        MinMax m = new MinMax();
        if(!leftMinMax.isBST || !rightMinMax.isBST || ((leftMinMax.max != null && root.val >= leftMinMax.max) || (rightMinMax.min != null && root.val <= rightMinMax.min))){
            m.isBST = false;
            m.size = Math.max(leftMinMax.size, rightMinMax.size);
            return m;
        }
        m.isBST = true;
        m.size = leftMinMax.size + rightMinMax.size + 1;
        m.min = root.left != null ? leftMinMax.min : root.val;
        m.max = root.right != null ? rightMinMax.max : root.val;
        return m;
    }

    private static class MinMax {
        private int size;
        private Integer min;
        private Integer max;
        private boolean isBST = true;
    }

    private int size(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    private boolean isBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
            return false;
        }
        if (!isValidBST(root.left, min, root.val) || !isValidBST(root.right, root.val, max)) {
            return false;
        }
        return true;
    }
}
