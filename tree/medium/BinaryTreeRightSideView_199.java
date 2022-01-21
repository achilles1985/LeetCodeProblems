package tree.medium;

import java.util.*;

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
 *
 *      1            <---
 *    /   \
 *   2     3         <---
 *  /
 * 4                 <---
 */
public class BinaryTreeRightSideView_199 {

    public static void main(String[] args) {
        BinaryTreeRightSideView_199 s = new BinaryTreeRightSideView_199();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        System.out.println(s.rightSideViewBFS(root)); //[1,3,4]
    }

    // O(n) - time, O(h) - space
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(root, res, 0);

        return res;
    }

    // O(n) - time, O(D) - space, D - diameter of the tree, D=N/2 for complete binary tree
    // The idea is to take the last element on each level
    public List<Integer> rightSideViewBFS(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res.add(queue.peekLast().val);
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return res;
    }

    private void helper(TreeNode root, List<Integer> list, int level) {
        if (level == list.size()) {
            list.add(root.val);
        }
        if (root.right != null) {
            helper(root.right, list, level+1);
        }
        if (root.left != null) {
            helper(root.left, list, level+1);
        }
    }
}
