package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import utils.TreeNode;

/**M
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see
 * ordered from top to bottom.
 *
 * Example:
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class BinaryTreeRightSideView_199 {

    public static void main(String[] args) {
        BinaryTreeRightSideView_199 s = new BinaryTreeRightSideView_199();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        System.out.println(s.rightSideView(root)); //[1,3,4]
    }

    // O(n) - time, O(h) - space
    // The idea is to take the last element on each level
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            Stack<Integer> stack = new Stack<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                stack.push(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(stack.pop());
        }
        return result;
    }
}
