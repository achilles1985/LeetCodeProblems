package tree.MinimumDepthOfBinaryTree_111;

import utils.TreeNode;

/** E
 Given a binary tree, find its minimum depth.
 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 Note: A leaf is a node with no children.
 Example:
 Given binary tree [3,9,20,null,null,15,7],

     3
    / \
   9  20
  / \
 15  7

 return its minimum depth = 2.
 */
public class Solution {

    public int minDepth(TreeNode root) {
        return minDepthOfNode(root);
    }

    // recursive method

    private int minDepthOfNode(TreeNode n) {
        if (n == null) {
            return 0;
        }

        if (n.left != null && n.right != null) {
            // has both children
            int l = minDepthOfNode(n.left) + 1;
            int r = minDepthOfNode(n.right) + 1;
            return Math.min(l, r);
        } else if (n.left != null) {
            // only has left child, then ignore right child
            return minDepthOfNode(n.left) + 1;
        } else if (n.right != null) {
            // only has right child
            return minDepthOfNode(n.right) + 1;
        } else {
            // it is a leaf node, no child
            return 1;
        }
    }

    public int minDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
