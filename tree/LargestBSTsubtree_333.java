package tree;

import utils.TreeNode;

/** M
 * Given a binary tree, find size of largest binary search subtree in this
 * binary tree.
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

        System.out.println(s.largestBST2(root1)); //3
    }

    // O(n^2) - time for skewed tree
    public int largestBST(TreeNode root){
        if (isBST(root)) {
            return size(root);
        }
        return Math.max(largestBST(root.left), largestBST(root.right));
    }

    // O(n) - time for skewed tree
    public int largestBST2(TreeNode root){
        MinMax m = largest(root);
        return m.size;
    }

    private MinMax largest(TreeNode root){
        //if root is null return min as Integer.MAX and max as Integer.MIN
        if(root == null){
            return new MinMax();
        }

        //postorder traversal of tree. First visit left and right then
        //use information of left and right to calculate largest BST.
        MinMax leftMinMax = largest(root.left);
        MinMax rightMinMax = largest(root.right);

        MinMax m = new MinMax();

        //if either of left or right subtree says its not BST or the data
        //of this node is not greater/equal than max of left and less than min of right
        //then subtree with this node as root will not be BST.
        //Return false and max size of left and right subtree to parent
        if(leftMinMax.isBST == false || rightMinMax.isBST == false || (leftMinMax.max < root.val || rightMinMax.min > root.val)){
            m.isBST = false;
            m.size = Math.max(leftMinMax.size, rightMinMax.size);
            return m;
        }

        //if we reach this point means subtree with this node as root is BST.
        //Set isBST as true. Then set size as size of left + size of right + 1.
        //Set min and max to be returned to parent.
        m.isBST = true;
        m.size = leftMinMax.size + rightMinMax.size + 1;

        //if root.left is null then set root.data as min else
        //take min of left side as min
        m.min = root.left != null ? leftMinMax.min : root.val;

        //if root.right is null then set root.data as max else
        //take max of right side as max.
        m.max = root.right != null ? rightMinMax.max : root.val;

        return m;
    }

    private static class MinMax {
        private int size = 0;
        private int min = Integer.MIN_VALUE;
        private int max = Integer.MAX_VALUE;
        private boolean isBST = true;
    }

    private int size(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return size(root.left) + size(root.left) + 1;
    }

    private boolean isBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if ((min != null && root.val <= min) || (max != null && root.val > max)) {
            return false;
        }
        if (!isValidBST(root.left, min, root.val) || !isValidBST(root.right, root.val, max)) {
            return false;
        }
        return true;
    }
}
