package tree.PathSum_112;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/** E
 Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
 Note: A leaf is a node with no children.

 Example:
 Given the below binary tree and sum = 22,

        5
       / \
      4   8
     /   / \
    11  13  4
   /  \      \
  7    2      1

 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class Solution {

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            } else {
                return false;
            }
        }

        if (hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val)) {
            return true;
        }
        return false;
    }

    public boolean hasPathSumIterative(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackSum = new Stack<>();
        stackNode.push(root);
        stackSum.push(sum - root.val);

        while (!stackNode.isEmpty()) {
            TreeNode node = stackNode.pop();
            Integer currSum = stackSum.pop();
            if (node.left == null && node.right == null && currSum == 0) {
                return true;
            }
            if (node.left != null) {
                stackNode.push(node.left);
                stackSum.push(currSum - node.left.val);
            }
            if (node.right != null) {
                stackNode.push(node.right);
                stackSum.push(currSum - node.right.val);
            }
        }

        return false;
    }
}
