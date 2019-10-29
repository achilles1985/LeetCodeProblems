package tree;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**
 156. Binary Tree Upside Down
 https://leetcode.com/problems/binary-tree-upside-down/#/description
 Given a binary tree where all the right nodes are either leaf nodes
 with a sibling (a left node that shares the same parent node) or empty,
 flip it upside down and turn it into a tree where the original right
 nodes turned into left leaf nodes. Return the new root.
 For example:
 Given a binary tree {1,2,3,4,5},
    1
   / \
  2   3
 / \
4   5
 return the root of the binary tree [4,5,2,#,#,3,1].
      4
     / \
    5   2
   / \
  3   1
 */

public class BinaryTreeUpsideDown_156 {

    public static void main(String[] args) {
        BinaryTreeUpsideDown_156 s = new BinaryTreeUpsideDown_156();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        TreeUtils.print(s.upsideDownBinaryTree(root1));
    }

    // O(n) - time, O(h) - space
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode left = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return left;
    }
}
