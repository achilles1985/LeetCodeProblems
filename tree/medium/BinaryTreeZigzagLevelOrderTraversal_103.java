package tree.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
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

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);

        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(9);
        root3.right = new TreeNode(20);
        root3.right.left = new TreeNode(15);
        root3.right.right = new TreeNode(7);

        System.out.println(s.zigzagLevelOrder3(root3)); //[[3],[20,9],[15,7]]
        System.out.println(s.zigzagLevelOrder3(root2)); //[[1],[3,2],[4,5]]
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

    public List<List<Integer>> zigzagLevelOrder3(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean left = true;
        List<List<Integer>> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = null;
                if (left) {
                    node = queue.pollFirst();
                } else {
                    node = queue.pollLast();
                }
                temp.add(node.val);
                if (node.left != null) {
                    if (left) {
                        queue.addLast(node.left);
                    } else {
                        queue.addFirst(node.left);
                    }
                }
                if (node.right != null) {
                    if (left) {
                        queue.addLast(node.right);
                    } else {
                        queue.addFirst(node.right);
                    }
                }
            }
            result.add(temp);
            left = !left;
        }

        return result;
    }
}
