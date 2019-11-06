package tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.TreeNode;

/**M
 * Given the root of a binary tree, each node in the tree has a distinct value.
 * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
 * Return the roots of the trees in the remaining forest.  You may return the result in any order.
 *
 * Example 1:
 * *         1
 *  *       /   \
 *  *     2       3
 *  *   /  \     /  \
 *  *  4    5   6    7
 * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
 * Output: [[1,2,null,4],[6],[7]]
 *
 * Constraints:
 *     The number of nodes in the given tree is at most 1000.
 *     Each node has a distinct value between 1 and 1000.
 *     to_delete.length <= 1000
 *     to_delete contains distinct values between 1 and 1000.
 */
public class DeleteNodesAndReturnForest_1110 {

    public static void main(String[] args) {
        DeleteNodesAndReturnForest_1110 s = new DeleteNodesAndReturnForest_1110();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(s.delNodes(root, new int[]{1}));
    }

    // O(n) - time (we travers each node in a tree), O(n) - space (for skewed tree stack depth=number of nodes + toDelete set contains all nodes)
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDelete = new HashSet<>();
        List<TreeNode> result = new ArrayList<>();
        for (int val: to_delete) {
            toDelete.add(val);
        }

        delNodesHelper(root, toDelete, result);
        if (!toDelete.contains(root.val)) {
            result.add(root);
        }
        return result;
    }

    private TreeNode delNodesHelper(TreeNode root, Set<Integer> toDelete, List<TreeNode> result) {
        if (root == null) {
            return null;
        }
        root.left = delNodesHelper(root.left, toDelete, result);
        root.right = delNodesHelper(root.right, toDelete, result);
        if (toDelete.contains(root.val)) {
            if (root.left != null) {
                result.add(root.left);
            }
            if (root.right != null) {
                result.add(root.right);
            }
            return null;
        }
        return root;
    }

}
