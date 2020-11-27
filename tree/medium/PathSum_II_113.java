package tree.medium;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/** M
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * Note: A leaf is a node with no children.
 * Example:
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 *
 * Return:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
public class PathSum_II_113 {

    public static void main(String[] args) {
        PathSum_II_113 s = new PathSum_II_113();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.left.left = new TreeNode(11);
        root.right.right = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.right.left = new TreeNode(5);
        root.right.right.right= new TreeNode(1);

        System.out.println(s.pathSum(root, 22));
    }

    // O(n^2) - time (because of copying temp list into the result one in case of complete BT number of leaves = N/2),
    // O(n) - space (in case of a span tree)
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        pathSumHelper(root, sum, new ArrayList<>(), result);
        return result;
    }

    private void pathSumHelper(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                result.add(new ArrayList<>(path));
            }
            path.remove(path.size()-1);
            return;
        }
        pathSumHelper(root.left, sum - root.val, path, result);
        pathSumHelper(root.right, sum - root.val, path, result);
        path.remove(path.size()-1);
    }
}
