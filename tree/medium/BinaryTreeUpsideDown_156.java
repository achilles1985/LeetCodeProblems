package tree.medium;

import tree.utils.TreeUtils;
import utils.TreeNode;

/** M
 Given the root of a binary tree, turn the tree upside down and return the new root.
 You can turn a binary tree upside down with the following steps:

 The original left child becomes the new root.
 The original root becomes the new right child.
 The original right child becomes the new left child.

 The mentioned steps are done level by level, it is guaranteed that every node in the given tree has either 0 or 2 children.

 Example 1:
 Input: root = [1,2,3,4,5]
 Output: [4,5,2,null,null,3,1]
 Example 2:

 Input: root = []
 Output: []
 Example 3:

 Input: root = [1]
 Output: [1]
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
