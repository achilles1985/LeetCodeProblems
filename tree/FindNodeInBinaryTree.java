package tree;

import utils.TreeNode;

public class FindNodeInBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);

        System.out.println(findNode(root, 9).val);
        System.out.println(findNode(root, 99));
    }

    private static TreeNode findNode(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        if (root.val == target) {
            return root;
        }
        TreeNode left = findNode(root.left, target);
        TreeNode right = findNode(root.right, target);

        return left != null ? left : right;
    }
}
