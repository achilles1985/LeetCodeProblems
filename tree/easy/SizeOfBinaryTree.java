package tree.easy;

import utils.TreeNode;

public class SizeOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        SizeOfBinaryTree s = new SizeOfBinaryTree();
        System.out.println(s.sizeOfBinaryTree(root));
    }

    // O(n) time, O(n) - for skew tree
    public int sizeOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sizeOfBinaryTree(root.left) + sizeOfBinaryTree(root.right) + 1;
    }
}
