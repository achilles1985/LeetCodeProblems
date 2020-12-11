package tree.easy;

import java.util.ArrayList;

import tree.utils.Node;

/** E [marked]
 * Given a n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * For example, given a 3-ary tree:
 *              1
 *            / | \
 *           2  3  4
 *         / |
 *        5  6
 *
 * We should return its max depth, which is 3.
 * Note:
 *     The depth of the tree is at most 1000.
 *     The total number of nodes is at most 5000.
 */
public class MaximumDepthOfNAryTree_559 {

    public static void main(String[] args) {
        MaximumDepthOfNAryTree_559 s = new MaximumDepthOfNAryTree_559();
        Node root = new Node(1);
        root.children = new ArrayList<>();
        root.children.add(new Node(2));
        root.children.add(new Node(3));
        root.children.add(new Node(4));
        root.children.get(0).children = new ArrayList<>();
        root.children.get(0).children.add(new Node(5));
        root.children.get(0).children.add(new Node(6));

        System.out.println(s.maxDepth(root));
    }

    public int maxDepth(Node root) {
        return dfs(root);
    }

    private int dfs(Node node) {
        if (node == null) {
            return 0;
        }
        int max = 0;
        if (node.children != null) {
            for (Node child : node.children) {
                int dn = dfs(child);
                if (dn > max) {
                    max = dn;
                }
            }
        }
        return max + 1;
    }
}
