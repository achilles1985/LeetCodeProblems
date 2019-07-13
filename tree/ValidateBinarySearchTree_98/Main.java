package tree.ValidateBinarySearchTree_98;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root0 = new TreeNode(2);
        root0.left = new TreeNode(1);
        root0.right = new TreeNode(3);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        root1.right.right.left = new TreeNode(10);
        root1.left.right.right = new TreeNode(8);

        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);

        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);

        Solution s = new Solution();
        System.out.println(s.isValidBST(root0)); // true
        //System.out.println(s.isValidBST(root1)); // false
        //System.out.println(s.isValidBST(root2)); // false
        //System.out.println(s.isValidBST(root3)); // false
    }
}
