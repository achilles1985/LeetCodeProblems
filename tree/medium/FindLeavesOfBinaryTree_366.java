package tree.medium;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**M [marked]
 * Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.
 *
 * Example:
 * Input: [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * Output: [[4,5,3],[2],[1]]
 */
public class FindLeavesOfBinaryTree_366 {

    public static void main(String[] args) {
        FindLeavesOfBinaryTree_366 s = new FindLeavesOfBinaryTree_366();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        System.out.println(s.findLeavesBF(root));
        System.out.println(s.findLeaves(root));
    }

    // O(n^2) - time, O(n) - space
    public List<List<Integer>> findLeavesBF(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        while (root != null) {
            List<Integer> leaves = new ArrayList<>();
            root = findLeaves(root, leaves);
            result.add(leaves);
        }
        return result;
    }

    // O(n) - time, space
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, result);

        return result;
    }

    private int helper(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return -1;
        }
        int leftHeight = helper(root.left, result);
        int rightHeight = helper(root.right, result);
        int height = Math.max(leftHeight, rightHeight) + 1;
        if (result.size() == height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(root.val);

        return height;
    }

    private TreeNode findLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return null;
        }
        root.left = findLeaves(root.left, leaves);
        root.right = findLeaves(root.right, leaves);

        return root;
    }

}
