package tree.SizeOfBinaryTree;

import utils.TreeNode;

public class Solution {

    public int sizeOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftCount = sizeOfBinaryTree(root.left);
        int rightCount = sizeOfBinaryTree(root.right);

        return leftCount + rightCount + 1;
    }
}
