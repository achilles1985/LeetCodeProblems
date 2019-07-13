package tree.BinaryTreePaths_257;

import utils.TreeNode;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        int i1 = Integer.parseInt("801");
        int i2 = Integer.parseInt("1128");

        System.out.println(s.binaryTreePaths(root)); // 1->2->4, 1->2->5, 1->3->6
    }
}
