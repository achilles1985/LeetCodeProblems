package tree.BinaryTreePostorderTraversal_145;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        root1.left.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(15);
        root2.right = new TreeNode(30);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(6);
        root2.left.left.left = new TreeNode(5);
        root2.right.right = new TreeNode(2);
        root2.right.right.left = new TreeNode(9);
        root2.right.right.right = new TreeNode(8);

        Solution s = new Solution();
        System.out.println(s.postorderTraversalIterative(root1)); // [4, 8, 5, 2, 6, 7, 3, 1]
        System.out.println(s.postorderTraversalIterative(root2)); // [5, 3, 6, 15, 9, 8, 2, 30, 10]
    }
}
