package tree;

import utils.TreeNode;

/** E
 * Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with
 * the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in
 * binary, which is 13.
 * For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.
 * Return the sum of these numbers.
 *
 * Example 1:
 * Input: [1,0,1,0,1,0,1]
 *       1
        / \
       0    1
      /\   / \
     0  1 0   1
 * Output: 22
 * Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
 *
 * Note:
 *     The number of nodes in the tree is between 1 and 1000.
 *     node.val is 0 or 1.
 *     The answer will not exceed 2^31 - 1.
 */
public class SumOfRootToLeafBinaryNumbers_1022 {

    public static void main(String[] args) {
        SumOfRootToLeafBinaryNumbers_1022 s = new SumOfRootToLeafBinaryNumbers_1022();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        System.out.println(s.sumRootToLeaf(root)); //22
    }

    // O(n) - time, space
    public int sumRootToLeaf(TreeNode root) {
        return sumRootToLeaf(root, 0);
    }

    private int sumRootToLeaf(TreeNode root, Integer sum) {
        if (root == null) {
            return 0;
        }
        sum  = sum*2 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        }
        int leftSum = sumRootToLeaf(root.left, sum);
        int rightSum =  sumRootToLeaf(root.right, sum);

        return leftSum + rightSum;
    }

}
