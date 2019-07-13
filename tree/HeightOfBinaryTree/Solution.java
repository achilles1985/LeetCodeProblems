package tree.HeightOfBinaryTree;

import utils.TreeNode;

public class Solution {

    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        return Math.max(leftHeight, rightHeight) +1;
    }
}
