package tree.medium;

import utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/** M
 * Given a binary tree root, a node X in the tree is named good if in the path from root to X there are no nodes with a value greater than X.
 * Return the number of good nodes in the binary tree.
 *
 * Example 1:
 * Input: root = [3,1,4,3,null,1,5]
 * Output: 4
 * Explanation: Nodes in blue are good.
 * Root Node (3) is always a good node.
 * Node 4 -> (3,4) is the maximum value in the path starting from the root.
 * Node 5 -> (3,4,5) is the maximum value in the path
 * Node 3 -> (3,1,3) is the maximum value in the path.
 *
 * Example 2:
 * Input: root = [3,3,null,4,2]
 * Output: 3
 * Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
 *
 * Example 3:
 * Input: root = [1]
 * Output: 1
 * Explanation: Root is considered as good.
 *
 * Constraints:
 *     The number of nodes in the binary tree is in the range [1, 10^5].
 *     Each node's value is between [-10^4, 10^4].
 */
public class CountGoodNodesInBinaryTree_1448 {

    public static void main(String[] args) {
        CountGoodNodesInBinaryTree_1448 s = new CountGoodNodesInBinaryTree_1448();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);

        System.out.println(s.goodNodesIterative(root)); //4
    }

    // O(n) - time, O(h) - space
    public int goodNodes(TreeNode root) {
        int[] count = new int[1];
        helper(root, root.val, count);

        return count[0];
    }

    // O(n) - time, O(h) - space
    public int goodNodesIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<Pair> stack = new ArrayDeque<>();
        int count = 1;
        stack.push(new Pair(root, root.val));
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            if (pair.node.val >= pair.maxSoFar) {
                count++;
            }
            if (pair.node.left != null) {
                stack.push(new Pair(pair.node.left, Math.max(pair.maxSoFar, pair.node.val)));
            } if (pair.node.right != null) {
                stack.push(new Pair(pair.node.right, Math.max(pair.maxSoFar, pair.node.val)));
            }
        }

        return count;
    }

    private void helper(TreeNode root, Integer max, int[] count) {
        if (root == null) {
            return;
        }
        if (root.val >= max) {
            count[0] += 1;
        }
        helper(root.left, Math.max(max, root.val), count);
        helper(root.right, Math.max(max, root.val), count);
    }

    private static class Pair {
        TreeNode node;
        int maxSoFar;

        Pair(TreeNode node, int maxSoFar) {
            this.node = node;
            this.maxSoFar = maxSoFar;
        }
    }
}
