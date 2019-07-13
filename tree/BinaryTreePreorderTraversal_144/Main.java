package tree.BinaryTreePreorderTraversal_144;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.right = new TreeNode(8);

        Solution s = new Solution();
        System.out.println(s.preorderTraversal(root)); // 1,2,4,5,8,3,6,7
        System.out.println(s.preorderTraversalIterative(root)); // 1,2,4,5,8,3,6,7
    }
}
