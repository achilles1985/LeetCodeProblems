package tree.BinaryTreePaths_257;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * E
 * Given a binary tree, return all root-to-leaf paths.
 * Note: A leaf is a node with no children.
 * Example:
 * Input:
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * Output: ["1->2->5", "1->3"]
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */

public class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<String> paths = new ArrayList<>();
        binaryTreePaths(root, "", paths);

        return paths;
    }

    private void binaryTreePaths(TreeNode node, String path, List<String> paths) {
        path += path.isEmpty() ? node.val : "->" + node.val;
        if (node.left == null && node.right == null) {
            paths.add(path);
            return;
        }

        if (node.left != null) binaryTreePaths(node.left, path, paths);
        if (node.right != null) binaryTreePaths(node.right, path, paths);
    }
}
