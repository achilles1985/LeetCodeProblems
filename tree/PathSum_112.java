package tree;

import java.util.Stack;

import utils.TreeNode;

/** E
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values
 * along the path equals the given sum.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 *
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */
public class PathSum_112 {

    public static void main(String[] args) {
        PathSum_112 s = new PathSum_112();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.left.left = new TreeNode(11);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.right = new TreeNode(1);

        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);

        System.out.println(s.hasPathSum2(root, 22)); // true
        System.out.println(s.hasPathSum2(root, 1)); // false

        System.out.println(s.hasPathSumIterative(root, 22)); // true
        System.out.println(s.hasPathSumIterative(root, 1)); // false
    }

    // O(n) - time, space
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                return true;
            }
            return false;
        }

        boolean left = hasPathSum2(root.left, sum - root.val);
        boolean right = hasPathSum2(root.right, sum - root.val);
        return left || right;
    }

    // O(n) - time, space
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return hasPathSumUtils(root, sum, 0);
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

    private boolean hasPathSumUtils(TreeNode root, int sum, int currSum) {
        if (root == null) {
            if (currSum == sum) {
                return true;
            }
            return false;
        }
        currSum += root.val;
        boolean left = hasPathSumUtils(root.left, sum, currSum);
        boolean right = hasPathSumUtils(root.right, sum , currSum);

        return left || right;
    }
}
