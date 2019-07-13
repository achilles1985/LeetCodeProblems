package tree.SizeOfBinaryTree;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        Solution s = new Solution();
        System.out.println(s.sizeOfBinaryTree(root));
    }
}
