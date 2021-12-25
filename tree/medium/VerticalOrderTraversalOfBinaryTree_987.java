package tree.medium;

import java.util.*;

import utils.TreeNode;

/**M [marked]
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
/*
    sort the nodes based on the 3-dimensional coordinates (col,row,val)
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

        System.out.println(s.verticalTraversalDFS(root)); //[[4],[2],[1,5,6],[3],[7]]
        System.out.println(s.verticalTraversalDFS(root1)); //[[9],[3,15],[20],[7]]
        System.out.println(s.verticalTraversalDFS(root2)); //[[8],[0,3,6],[1,4,5],[2,7]]
    }

    // BF approach is to create and collect all tuples(col,row,val) and sort that list by (col,row) and form the res, O(n*log(n))

    // O(n*log(n/k)) - k - number of subgroups we divide out nodes to (number of subgroups = vertical lines),  O(n) - space
    // In case the tree is a linked list - O(n) - time, subgroups = N, each subgroup consists of only one value
    public List<List<Integer>> verticalTraversalDFS(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Map<Integer, List<NodeInfo>> map = new HashMap<>();
        populate(root, map, 0, 0);

        int min = map.keySet().stream().min(Comparator.naturalOrder()).get();
        int max = map.keySet().stream().max(Comparator.naturalOrder()).get();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            List<NodeInfo> list = map.get(i);
            Collections.sort(list, Comparator.comparing(NodeInfo::getRow).thenComparing(NodeInfo::getVal));
            List<Integer> temp = new ArrayList<>();
            for (NodeInfo n: list) {
                temp.add(n.val);
            }
            result.add(temp);
        }

        return result;
    }

    private void populate(TreeNode root,  Map<Integer, List<NodeInfo>> map, int col, int row) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(col, key -> new ArrayList<>()).add(new NodeInfo(root.val, col, row));
        populate(root.left, map, col-1, row+1);
        populate(root.right, map, col+1, row+1);
    }

    static class NodeInfo {
        int val;
        int col;
        int row;

        NodeInfo(int val, int col, int row) {
            this.val = val;
            this.col = col;
            this.row = row;
        }

        public int getVal() {
            return val;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }
    }
}
