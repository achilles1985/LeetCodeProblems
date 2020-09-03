package assessments.amazon;

import utils.TreeNode;

/** M
 * Write a function that given a BST, it will return the distance (number of edges) between 2 nodes.
 * For example, given this tree
 *
 *          5
 *         / \
 *        3   6
 *       / \   \
 *      2   4   7
 *     /         \
 *    1           8
 * The distance between 1 and 4 is 3: [1 -> 2 -> 3 -> 4]
 * The distance between 1 and 8 is 6: [1 -> 2 -> 3 -> 5 -> 6 -> 7 -> 8]
 */
public class DistanceBetweenTwoNodesInBST {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(1);
        root.right.right.right = new TreeNode(8);

        DistanceBetweenTwoNodesInBST s = new DistanceBetweenTwoNodesInBST();
        System.out.println(s.bstDistance(root, 3,4)); //1
        System.out.println(s.bstDistance(root, 4,8)); //5
        System.out.println(s.bstDistance(root, 6,8)); //2
    }

    // O(n) - time, space
    public int bstDistance(TreeNode root, int node1, int node2) {
        if (root == null) {
            return -1;
        }
        TreeNode lca = lowestCommonAncestor(root, node1, node2);
        int left = getDistance(lca.left, node1);
        int right = getDistance(lca.right, node2);

        return left + right;
    }

    private int getDistance(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        if (root.val == target) {
            return 1;
        }
        int left = getDistance(root.left, target);
        int right = getDistance(root.right, target);

        if (left == 0 && right == 0) {
            return 0;
        }
        return Math.max(left, right) + 1;
    }

    // O(n) - time, O(1) - space
    private TreeNode lowestCommonAncestor(TreeNode root, int node1, int node2) {
        if (root == null) {
            return null;
        }
        while (root != null) {
            if (root.val < Math.min(node1, node2)) {
                root = root.right;
            } else if (root.val > Math.max(node1, node2)) {
                root = root.left;
            } else {
                return root;
            }
        }
        return null;
    }
}
