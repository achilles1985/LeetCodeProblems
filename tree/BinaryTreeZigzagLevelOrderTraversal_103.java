package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import utils.TreeNode;

/**M
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then
 * right to left for the next level and alternate between).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal_103 {

    public static void main(String[] args) {
        BinaryTreeZigzagLevelOrderTraversal_103 s = new BinaryTreeZigzagLevelOrderTraversal_103();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        root.left.left.left = new TreeNode(6);
        root.right.right.right = new TreeNode(7);
        root.left.left.left.left = new TreeNode(8);
        root.right.right.right.right = new TreeNode(9);

        System.out.println(s.zigzagLevelOrder(root));
    }

    // O(n) - time, space
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        boolean oddLevel = true;
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            if (oddLevel) {
                while (size-- > 0) {
                    TreeNode popped = queue.poll();
                    level.add(popped.val);
                    if (popped.left != null) queue.add(popped.left);
                    if (popped.right != null) queue.add(popped.right);
                }
                oddLevel = false;
            } else {
                while (size-- > 0) {
                    TreeNode polled = queue.poll();
                    stack.push(polled);
                    if (polled.left != null) queue.add(polled.left);
                    if (polled.right != null) queue.add(polled.right);
                }
                while (!stack.isEmpty()) {
                    level.add(stack.pop().val);
                }
                oddLevel = true;
            }
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
