package tree.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import utils.TreeNode;

/** M [MARKED]
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned
 * in any order.
 *
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 *          3
 *         / \
 *        5   1
 *      / |   | \
 *     6  2   0  8
 *       / \
 *      7   4
 * Output: [7,4,1]
 *
 * Explanation:
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 * Note that the inputs "root" and "target" are actually TreeNodes.
 * The descriptions of the inputs above are just serializations of these objects.
 *
 * Note:
 *     The given tree is non-empty.
 *     Each node in the tree has unique values 0 <= node.val <= 500.
 *     The target node is a node in the tree.
 *     0 <= K <= 1000.
 */
public class AllNodesDistanceKInBinaryTree_863 {

    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        AllNodesDistanceKInBinaryTree_863 s = new AllNodesDistanceKInBinaryTree_863();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.right = new TreeNode(8);
        root.right.left = new TreeNode(0);

        System.out.println(s.distanceK(root, root.left, 2));
    }

    // O(n) - time, space
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null || target == null) {
            return  new ArrayList<>();
        }
        Map<TreeNode, TreeNode> nodeToParent = mapNodeToParent(root);
        Set<TreeNode> seen = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        while (K-- > 0) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                seen.add(node);
                if (node.left != null && !seen.contains(node.left)) queue.add(node.left);
                if (node.right != null && !seen.contains(node.right)) queue.add(node.right);
                if (nodeToParent.get(node) != null && !seen.contains(nodeToParent.get(node))) queue.add(nodeToParent.get(node));
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }
        return result;
    }

    private Map<TreeNode, TreeNode> mapNodeToParent(TreeNode root) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        populateNodeToParentMap(map, root, null);
        return map;
    }

    private void populateNodeToParentMap(Map<TreeNode, TreeNode> map, TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        map.put(root, parent);
        populateNodeToParentMap(map, root.left, root);
        populateNodeToParentMap(map, root.right, root);
    }

}
