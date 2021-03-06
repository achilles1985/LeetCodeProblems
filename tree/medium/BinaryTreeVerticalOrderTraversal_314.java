package tree.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import utils.TreeNode;

/** M
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples 1:
 * Input: [3,9,20,null,null,15,7]
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Output:
 * [
 *   [9],
 *   [3,15],
 *   [20],
 *   [7]
 * ]
 *
 * Examples 2:
 * Input: [3,9,8,4,0,1,7]
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *
 * Output:
 * [
 *   [4],
 *   [9],
 *   [3,0,1],
 *   [8],
 *   [7]
 * ]
 *
 * Examples 3:
 * Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *     /\
 *    /  \
 *    5   2
 *
 * Output:
 * [
 *   [4],
 *   [9,5],
 *   [3,0,1],
 *   [8,2],
 *   [7]
 * ]
 */
/*
    sort the nodes based on the 3-dimensional coordinates (col,row)
 */
public class BinaryTreeVerticalOrderTraversal_314 {

    // [3,9,8,4,0,1,7,null,null,null,2,5]
    public static void main(String[] args) {
        BinaryTreeVerticalOrderTraversal_314 s = new BinaryTreeVerticalOrderTraversal_314();

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(2);

        System.out.println(s.verticalOrderDFS(root)); //[[4],[9,5],[3,0,1],[8,2],[7]]
        System.out.println(s.verticalOrderBFS(root)); //[[4],[9,5],[3,0,1],[8,2],[7]]
    }

    // O(n*log(n/k)) - k - number of subgroups we divide out nodes to (number of subgroups = vertical lines), O(n) - space
    // In case the tree is a linked list - O(n) - time, subgroups = N, each subgroup consists of only one value
    public List<List<Integer>> verticalOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, List<NodeInfo>> map = new HashMap<>();
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        helper(map, root, 0, 0, min, max);

        for (int i = min.intValue(); i <= max.intValue(); i++) {
            List<NodeInfo> nodes = map.get(i);
            nodes.sort(Comparator.comparing(node -> node.row)); //max nodes in a level
            List<Integer> temp = new ArrayList<>();
            for (NodeInfo nodeInfo: nodes) {
                temp.add(nodeInfo.val);
            }
            result.add(temp);
        }
        return result;
    }

    // O(n*log(n/k)) - k - number of subgroups we divide out nodes to (number of subgroups = vertical lines), O(n) - space
    public List<List<Integer>> verticalOrderBFS(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        Queue<NodeInfoBFS> queue = new LinkedList<>();
        Map<Integer, List<NodeInfoBFS>> rowToNodeInfo = new TreeMap<>();
        NodeInfoBFS rootNode = new NodeInfoBFS(0,0, root);
        queue.add(rootNode);
        rowToNodeInfo.computeIfAbsent(0, key -> new ArrayList<>()).add(rootNode);

        while (!queue.isEmpty()) {
            NodeInfoBFS nodeInfo = queue.poll();
            int row = nodeInfo.row;
            int col = nodeInfo.col;
            if (nodeInfo.node.left != null) {
                NodeInfoBFS nodeLeft = new NodeInfoBFS(row + 1, col - 1, nodeInfo.node.left);
                rowToNodeInfo.computeIfAbsent(col-1, key-> new ArrayList<>()).add(nodeLeft);
                queue.add(nodeLeft);
            }
            if (nodeInfo.node.right != null) {
                NodeInfoBFS nodeRight = new NodeInfoBFS(row + 1, col + 1, nodeInfo.node.right);
                rowToNodeInfo.computeIfAbsent(col+1, key-> new ArrayList<>()).add(nodeRight);
                queue.add(nodeRight);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, List<NodeInfoBFS>> entry: rowToNodeInfo.entrySet()) { // O(v*w*log(w))
            List<NodeInfoBFS> nodes = entry.getValue();
            Collections.sort(nodes, Comparator.comparing(node -> node.col));
            List<Integer> temp = new ArrayList<>();
            for (NodeInfoBFS nodeInfo: nodes) {
                final Integer val = nodeInfo.node.val;
                temp.add(val);
            }
            res.add(temp);
        }

        return res;
    }

    private void helper(Map<Integer, List<NodeInfo>> map, TreeNode root, int col, int row, AtomicInteger min, AtomicInteger max) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(col, key->new ArrayList<>()).add(new NodeInfo(root.val, col, row));
        if (col < min.get()) {
            min.set(col);
        }
        if (col > max.get()) {
            max.set(col);
        }
        helper(map, root.left, col-1, row+1, min, max);
        helper(map, root.right, col+1, row+1, min, max);
    }

    private static class NodeInfo {
        int val;
        int col;
        int row;

        public NodeInfo(int val, int col, int row) {
            this.val = val;
            this.col = col;
            this.row = row;
        }
    }

    private static class NodeInfoBFS {
        int row;
        int col;
        TreeNode node;

        NodeInfoBFS(int row, int col, TreeNode node) {
            this.row = row;
            this.col = col;
            this.node = node;
        }
    }
}
