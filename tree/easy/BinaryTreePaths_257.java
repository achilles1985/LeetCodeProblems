package tree.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.util.Queue;
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
/*
    Mistakes:
        1. Remember about mutable/immutable method's args like path. If we use mutable StringBuilder, each recursion call will be having common, latest instance of the string.
        2. Make sure when doing root.val that root is not null.
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
        System.out.println(s.binaryTreePathsIterative(root));
    }

    // O(n) - time, space.
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        helper(root, "", result);

        return result;
    }

    public List<String> binaryTreePathsIterative(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        Queue<String> paths = new LinkedList<>();
        nodes.add(root);
        paths.add(String.valueOf(root.val));
        List<String> result = new ArrayList<>();
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            String path = paths.poll();
            if (node.left == null && node.right == null) {
                result.add(path);
            }
            if (node.left != null) {
                nodes.add(node.left);
                paths.add(path + "->" + node.left.val);
            }
            if (node.right != null) {
                nodes.add(node.right);
                paths.add(path + "->" + node.right.val);
            }
        }

        return result;
    }

    private void helper(TreeNode root, String path, List<String> result) {
        if (root == null) {
            return;
        }
        path += path.isEmpty() ? root.val : "->" + root.val;
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        helper(root.left, path, result);
        helper(root.right, path, result);
    }
}
