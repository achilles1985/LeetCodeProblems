package tree.easy;

import utils.TreeNode;

/** E [MARKED]
 *  Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
 *  the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *
 *             1
 *          /   \
 *         2     3
 *        / \   /
 *       4   5 6
 *
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * Note: The length of path between two nodes is represented by the number of edges between them.
 */
public class DiameterOfBinaryTree_543 {

    public static void main(String[] args) {
        DiameterOfBinaryTree_543 s = new DiameterOfBinaryTree_543();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println(s.diameterOfBinaryTreeBF(root)); //4
        System.out.println(s.diameterOfBinaryTree2(root)); //4
        System.out.println(s.diameterOfBinaryTree3(root)); //4
    }

    // O(n) - time, O(h) - space
    public int diameterOfBinaryTree3(TreeNode root) {
        return  diameterOfBinaryTree3Helper(root).diameter;
    }

    private BinaryTreeInfo diameterOfBinaryTree3Helper(TreeNode root) {
        if (root == null) {
            return new BinaryTreeInfo(0, 0);
        }
        BinaryTreeInfo leftInfo = diameterOfBinaryTree3Helper(root.left);
        BinaryTreeInfo rightInfo = diameterOfBinaryTree3Helper(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int diameter = leftInfo.height + rightInfo.height;
        BinaryTreeInfo currentInfo = new BinaryTreeInfo(height, diameter);

        return BinaryTreeInfoUtils.max(currentInfo, BinaryTreeInfoUtils.max(leftInfo, rightInfo));
    }

    // O(n^2) - time, O(h^2) - space
    public int diameterOfBinaryTreeBF(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int leftDiameter = diameterOfBinaryTreeBF(root.left);
        int rightDiameter = diameterOfBinaryTreeBF(root.right);
        return Math.max((leftHeight + rightHeight), Math.max(leftDiameter, rightDiameter));
    }

    // O(n) - time, O(h) - space
    public int diameterOfBinaryTree2(TreeNode root) {
        Height height = new Height();
        return diameterOfBinaryTree2Helper(root, height);
    }

    private int diameterOfBinaryTree2Helper(TreeNode root, Height height) {
        if (root == null) {
            return 0;
        }
        Height leftHeight = new Height();
        Height rightHeight = new Height();
        int maxLeftDiameter = diameterOfBinaryTree2Helper(root.left, leftHeight);
        int maxRightDiameter = diameterOfBinaryTree2Helper(root.right, rightHeight);
        height.height = Math.max(leftHeight.height, rightHeight.height) + 1;
        int diameter = leftHeight.height + rightHeight.height;

        return Math.max(Math.max(maxLeftDiameter, maxRightDiameter), diameter);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);
        return Math.max(left, right) + 1;
    }

    private static class Height {
        private int height;
    }

    private static class BinaryTreeInfo {
        private int height;
        private int diameter;

        public BinaryTreeInfo(int height, int diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    public static final class BinaryTreeInfoUtils {
        public static BinaryTreeInfo max(BinaryTreeInfo i1, BinaryTreeInfo i2) {
            if (i1.diameter == i2.diameter) {
                return i1;
            }
            return i1.diameter > i2.diameter ? i1 : i2;
        }
    }
}
