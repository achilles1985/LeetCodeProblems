package tree.PathSum_112;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(1);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);

        TreeNode root2 = new TreeNode(0);
        root2.left = new TreeNode(1);

        Solution s = new Solution();
        System.out.println(s.hasPathSumIterative(root1, 22)); // true
        System.out.println(s.hasPathSumIterative(root2, 23)); // false
    }
}
