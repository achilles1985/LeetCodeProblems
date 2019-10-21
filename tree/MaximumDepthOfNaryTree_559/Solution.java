package tree.MaximumDepthOfNaryTree_559;

import tree.utils.Node;

/**E
 Given a n-ary tree, find its maximum depth.
 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class Solution {

    public int maxDepth(Node root) {
        return dfs(root);
    }

    private int dfs(Node node) {
        if (node == null) {
            return 0;
        }
        int max = 0;
        for (Node n: node.children) {
            int dn = dfs(n);
            if (dn > max) {
                max = dn;
            }
        }
        return max + 1;
    }

}
