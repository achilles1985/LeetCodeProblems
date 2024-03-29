package tree.medium;

import utils.TreeNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** M
 Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA)
 of all the nodes in nodes. All the nodes will exist in the tree, and all values of the tree's nodes are unique.
 Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ...,
 pn in a binary tree T is the lowest node that has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i".
 A descendant of a node x is a node y that is on the path from node x to some leaf node.

 Example 1:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [4,7]
 Output: 2
 Explanation: The lowest common ancestor of nodes 4 and 7 is node 2.

 Example 2:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [1]
 Output: 1
 Explanation: The lowest common ancestor of a single node is the node itself.

 Example 3:
 Input: root = [3,5,1,6,2,0,8,null,null,7,4], nodes = [7,6,2,4]
 Output: 5
 Explanation: The lowest common ancestor of the nodes 7, 6, 2, and 4 is node 5.
 */
public class LowestCommonAncestorOfBinaryTreeIV_1676 {

    // O(n) - time, O(n) - space
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Set<TreeNode> set = new HashSet<>(Arrays.asList(nodes));
        TreeNode[] result = new TreeNode[1];
        helper(root, set, result);

        return result[0];
    }

   private int helper(TreeNode root, Set<TreeNode> set, TreeNode[] result) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, set, result);
        int right = helper(root.right, set, result);
        int count = left + right;
        if (set.contains(root)) {
            count++;
        }
        if (count == set.size() && result[0] == null) {
            result[0] = root;
        }

        return count;
    }
}
