package tree.easy;

import utils.TreeNode;

/** E [marked]
 * Find the sum of all left leaves in a given binary tree.
 * Example:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves_404 {

    public static void main(String[] args) {
        SumOfLeftLeaves_404 s = new SumOfLeftLeaves_404();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println(s.sumOfLeftLeavesBF2(root)); //24
    }

    // O(n) - time, O(h) - space
    public int sumOfLeftLeavesBF(TreeNode root) {
        int sum = 0;
        if (root == null){
            return 0;
        }
        if (isNodeALeafNode(root.left)){
            sum += root.left.val;
        }
        int left = sumOfLeftLeavesBF(root.left);
        int right = sumOfLeftLeavesBF(root.right);
        return sum + left + right;
    }

    // O(n) - time, O(h) - space
    public int sumOfLeftLeavesBF2(TreeNode root) {
        int[] sum = new int[1];
        helper(root, sum);

        return sum[0];
    }

    // O(n) - time, O(1) - space
    // Morris tree traversal
    public int sumOfLeftLeaves3(TreeNode root) {
        int totalSum = 0;
        TreeNode currentNode = root;
        while (currentNode != null) {
            // If there is no left child, we can simply explore the right subtree
            // without needing to worry about keeping track of currentNode's other
            // child.
            if (currentNode.left == null) {
                currentNode = currentNode.right;
            } else {
                TreeNode previous = currentNode.left;
                // Check if this left node is a leaf node.
                if (previous.left == null && previous.right == null) {
                    totalSum += previous.val;
                }
                // Find the inorder predecessor for currentNode.
                while (previous.right != null && !previous.right.equals(currentNode)) {
                    previous = previous.right;
                }
                // We've not yet visited the inorder predecessor. This means that we
                // still need to explore currentNode's left subtree. Before doing this,
                // we will put a link back so that we can get back to the right subtree
                // when we need to.
                if (previous.right == null) {
                    previous.right = currentNode;
                    currentNode = currentNode.left;
                }
                // We have already visited the inorder predecessor. This means that we
                // need to remove the link we added, and then move onto the right
                // subtree and explore it.
                else {
                    previous.right = null;
                    currentNode = currentNode.right;
                }
            }
        }
        return totalSum;
    }

    private boolean isNodeALeafNode(TreeNode node){
        return node != null && node.left == null && node.right == null;
    }

    private void helper(TreeNode root, int[] sum) {
        if (root == null) {
            return;
        }
        if (root.left != null && isLeaf(root.left)) {
            sum[0] += root.left.val;
        }
        helper(root.left, sum);
        helper(root.right, sum);
    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
