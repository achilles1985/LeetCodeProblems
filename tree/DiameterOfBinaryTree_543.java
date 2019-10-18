package tree;

import utils.TreeNode;

/** M
 *  Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is
 *  the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
 *
 * Example:
 * Given a binary tree
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
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

        System.out.println(s.diameterOfBinaryTree(root)); //4
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int leftDiameter = diameterOfBinaryTree(root.left);
        int rightDiameter = diameterOfBinaryTree(root.right);
        return Math.max((leftHeight + rightHeight), Math.max(leftDiameter, rightDiameter));
    }

    public int diameterOfBinaryTree2(TreeNode root) {
        int[] depth = new int[1];
        depth[0] = 0;
        diameterOfBinaryTree2Helper(root, depth);
        return depth[0];
    }

    int diameterOfBinaryTree2Helper(TreeNode root, int[] depth) {
        if(root == null) {
            return 0;
        }
        int lD = diameterOfBinaryTree2Helper(root.left, depth);
        int rD = diameterOfBinaryTree2Helper(root.right, depth);
        // Maximum between max maxDiameter  depth[0] and current diameter (lD+rD))
        depth[0] = Math.max(depth[0], lD+rD);
        // returns height
        return Math.max(lD, rD)+1;
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = height(root.left);
        int right = height(root.right);

        return Math.max(left, right) + 1;
    }

}
