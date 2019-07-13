package tree.SumOfLeftLeaves_404;

import utils.TreeNode;

public class Solution {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left != null && (root.left.left == null && root.left.right == null)) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }

        int leftCount = sumOfLeftLeaves(root.left);
        int rightCount = sumOfLeftLeaves(root.right);

        return leftCount + rightCount;
    }
}
