package tree.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

import utils.TreeNode;

/**M
 * Given a binary tree, return the vertical order traversal of its nodes values.
 * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and
 * (X+1, Y-1).
 * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we
 * report the values of the nodes in order from top to bottom (decreasing Y coordinates).
 * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
 * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
 *
 * Example 1:
 * Input: [3,9,20,null,null,15,7]
 *  *    3
 *  *  /   \
 *  * 9     20
 *  *  \     \
 *  *   15    7
 * Output: [[9],[3,15],[20],[7]]
 *
 * Explanation:
 * Without loss of generality, we can assume the root node is at position (0, 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 *
 * Example 2:
 * Input: [1,2,3,4,5,6,7]
 *  *  *     1
 *  *  *  /    \
 *  *  * 2      3
 *  *  /  \    / \
 *  * 4    5  6   7
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * The node with value 5 and the node with value 6 have the same position according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.
 *
 * Note:
 *     The tree will have between 1 and 1000 nodes.
 *     Each node's value will be between 0 and 1000.
 */
public class VerticalOrderTraversalOfBinaryTree_987 {

    public static void main(String[] args) {
        VerticalOrderTraversalOfBinaryTree_987 s = new VerticalOrderTraversalOfBinaryTree_987();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.left.right = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(0);
        root2.left = new TreeNode(8);
        root2.right = new TreeNode(1);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(2);
        root2.right.left.right = new TreeNode(4);
        root2.right.left.right.right = new TreeNode(7);
        root2.right.right.left = new TreeNode(5);
        root2.right.right.left.left = new TreeNode(6);

        System.out.println(s.verticalTraversal(root)); //[[4],[2],[1,5,6],[3],[7]]
        System.out.println(s.verticalTraversal2(root1)); //[[9],[3,15],[20],[7]]
        System.out.println(s.verticalTraversal2(root2)); //[[8],[0,3,6],[1,4,5],[2,7]]
    }

    // O(n) - time, O(n) - space (Iterative)
    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> valueQueue = new LinkedList<>();
        nodeQueue.add(root);
        valueQueue.add(0);
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            Integer value = valueQueue.poll();
            map.computeIfAbsent(value, key -> new ArrayList<>()).add(node.val);
            if (node.left != null) {
                nodeQueue.add(node.left);
                valueQueue.add(value-1);
            }
            if (node.right != null) {
                nodeQueue.add(node.right);
                valueQueue.add(value+1);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> value: map.values()) {
            result.add(value);
        }
        return result;
    }

    // O(n) - time, O(n) - space (recursive)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<Integer>> map = new TreeMap<>();
        populateNodeDepthMap(map, root, 0);
        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

    private void populateNodeDepthMap(Map<Integer, List<Integer>> map, TreeNode root, int key) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(key, k -> new ArrayList<>()).add(root.val);
        populateNodeDepthMap(map, root.left, key-1);
        populateNodeDepthMap(map, root.right, key+1);
    }
}
