package tree.easy;

import utils.TreeNode;

/** E [marked]
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves_404 {

    public static void main(String[] args) {
        SumOfLeftLeaves_404 s = new SumOfLeftLeaves_404();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(s.sumOfLeftLeaves(root)); //24
    }

    // O(n) - time, space
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null){
            return 0;
        }
        if (isNodeALeafNode(root.left)){
            sum += root.left.val;
        }
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);
        return sum + left + right;
    }

    private boolean isNodeALeafNode(TreeNode node){
        return node != null && node.left == null && node.right == null;
    }

}
