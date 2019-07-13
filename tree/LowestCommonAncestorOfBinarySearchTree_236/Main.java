package tree.LowestCommonAncestorOfBinarySearchTree_236;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);

        Solution s = new Solution();
        System.out.println(s.lowestCommonAncestor(root1, root1.left, root1.right).val); // 6
        System.out.println(s.lowestCommonAncestor(root1, root1.left, root1.left.right).val); // 2
        System.out.println(s.lowestCommonAncestorIterative(root2, root2, root2.left).val); // 2
    }
}
