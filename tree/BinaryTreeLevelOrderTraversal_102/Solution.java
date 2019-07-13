package tree.BinaryTreeLevelOrderTraversal_102;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 For example:
 Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7

 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]
 */
public class Solution {

    // O(n) - time, O(n) - space, where n - number of nodes
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> levels = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                size--;
            }
            levels.add(level);
        }

        return levels;
    }
}
