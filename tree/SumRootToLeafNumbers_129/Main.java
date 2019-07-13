package tree.SumRootToLeafNumbers_129;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);

        TreeNode root2 = new TreeNode(0);
        root2.left = new TreeNode(1);

        Solution s = new Solution();
        //System.out.println(s.sumNumbers(root1)); // 1799
        System.out.println(s.sumNumbers(root2)); // 1
    }
}
