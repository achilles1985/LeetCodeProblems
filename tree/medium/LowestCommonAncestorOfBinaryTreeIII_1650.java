package tree.medium;

import java.util.*;

/** M
 Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 Each node will have a reference to its parent node. The definition for Node is below:
 class Node {
 public int val;
 public Node left;
 public Node right;
 public Node parent;
 }
 According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is
 the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 *  *            3
 *  *         /    \
 *  *        5      1
 *  *       / \    /  \
 *  *      6   2  0    8
 *  *         / \
 *  *        7   4
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 *
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA
 * definition.
 *
 *  Note:
 *     All of the nodes' values will be unique.
 *     p and q are different and both values will exist in the binary tree.
 */
public class LowestCommonAncestorOfBinaryTreeIII_1650 {

    // O(n) - time, O(n) - space
    public Node lowestCommonAncestorBF(Node p, Node q) {
        Set<Node> set = new HashSet<>();
        while (p != null) {
            set.add(p);
            p = p.parent;
        }
        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = q.parent;
        }
        return null;
    }

    // O(n) - time, O(1) - space
    public Node lowestCommonAncestor(Node p, Node q) {
        int pLen = getLen(p), qLen = getLen(q);
        if (pLen > qLen) {
            for (int i = 0 ; i < pLen - qLen; i++) {
                p = p.parent;
            }
        } else if (qLen > pLen) {
            for (int i = 0 ; i < qLen - pLen; i++) {
                q = q.parent;
            }
        }
        while (p != q) {
            p = p.parent;
            q = q.parent;
        }

        return p;
    }

    private int getLen(Node n) {
        int count = 0;
        while (n != null) {
            count++;
            n = n.parent;
        }

        return count;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
