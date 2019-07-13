package recursion;

import utils.TreeNode;

/** E
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.
 The length of path between two nodes is represented by the number of edges between them.
 Example 1:
 Input:

    5
   / \
  4   5
 / \   \
1   1   5

 Output: 2

Example 2:
Input:
    1
   / \
  4   5
 / \   \
4   4   5
 Output: 2
 */
public class LongestUnivaluePath_687 {
    private int res;

    public int longestUnivaluePath(TreeNode root) {
        res = 0;
        longestUnivaluePathUtil(root);

        return res;
    }

    public int longestUnivaluePathUtil(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = longestUnivaluePathUtil(root.left);
        int right = longestUnivaluePathUtil(root.right);

        int leftMax = 0; int rightMax = 0;
        if (root.left != null && root.left.val == root.val) {
            leftMax += left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            rightMax += right + 1;
        }

        res = Math.max(res, leftMax + rightMax);

        return Math.max(leftMax, rightMax);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        root.left.left = node3;
        root.left.right = node4;
        root.right.right = node5;

        TreeNode root1 = new TreeNode(1);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(4);
        root1.left = n1;
        root1.right = n2;
        root1.left.left = n3;
        root1.left.left.left = n6;
        root1.left.right = n4;
        root1.right.right = n5;

        TreeNode root2 = new TreeNode(1);
        TreeNode n11 = new TreeNode(4);
        TreeNode n22 = new TreeNode(4);
        TreeNode n33 = new TreeNode(4);
        TreeNode n44 = new TreeNode(4);
        TreeNode n55 = new TreeNode(4);
        TreeNode n66 = new TreeNode(4);
        TreeNode n77 = new TreeNode(4);
        TreeNode n88 = new TreeNode(4);
        root2.left = n11;
        root2.right = n22;
        root2.left.left = n33;
        root2.left.right = n44;
        root2.left.right.right = n88;
        root2.left.left.left = n55;
        root2.left.left.left.left = n66;
        root2.right.right = n77;

        LongestUnivaluePath_687 s = new LongestUnivaluePath_687();
        System.out.println(s.longestUnivaluePath(root)); // 2
        System.out.println(s.longestUnivaluePath(root1)); // 3
        System.out.println(s.longestUnivaluePath(root2)); // 5
    }
}
