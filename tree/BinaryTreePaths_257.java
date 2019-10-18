package tree;

import java.util.ArrayList;
import java.util.List;

import utils.TreeNode;

/** E
 * Given a binary tree, return all root-to-leaf paths.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */
public class BinaryTreePaths_257 {

    public static void main(String[] args) {
        BinaryTreePaths_257 s = new BinaryTreePaths_257();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println(s.binaryTreePaths(root));
    }

    // O(n) - time, space. Mistakes: remember about mutable/immutable method's args like path. If we use mutable StringBuilder, each recursion call will be having common, latest instance of the string.
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        binaryTreePathsUtils(root, "", result);

        return result;
    }

    private void binaryTreePathsUtils(TreeNode root, String path, List<String> result) {
        path += path.isEmpty() ? root.val : "->" + root.val;
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        if (root.left != null) {
            binaryTreePathsUtils(root.left, path, result);
        }
        if (root.right != null) {
            binaryTreePathsUtils(root.right, path, result);
        }
    }
}
