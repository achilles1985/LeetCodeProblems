package tree.BinaryTreeZigzagLevelOrderTraversal_103;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Solution {

    // O(n*k) - time, where k - number of levels, n - number of nodes, O(n) - space to use queue
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            while (size > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
                size--;
            }
            if (reverse) {
                Collections.reverse(level);
            }
            reverse = !reverse;
            result.add(level);
        }

        return result;
    }

    // O(n) - time, O(n) - space
    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        leftStack.push(root);
        while (!leftStack.isEmpty() || !rightStack.isEmpty()) {
            List<Integer> level1 = new ArrayList<>();
            while (!leftStack.isEmpty()) {
                TreeNode current = leftStack.pop();
                level1.add(current.val);
                if (current.left != null) rightStack.push(current.left);
                if (current.right != null) rightStack.push(current.right);
            }
            List<Integer> level2 = new ArrayList<>();
            while (!rightStack.isEmpty()) {
                TreeNode current = rightStack.pop();
                level2.add(current.val);
                if (current.right != null) leftStack.push(current.right);
                if (current.left != null) leftStack.push(current.left);
            }
            if (!level1.isEmpty()) {
                result.add(level1);
            }
            if (!level2.isEmpty()) {
                result.add(level2);
            }
        }

        return result;
    }
}
