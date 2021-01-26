package tree.medium;

import java.util.HashMap;
import java.util.Map;

/** M
 * A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.
 * Return a deep copy of the tree.
 * The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:
 *
 * val: an integer representing Node.val
 * random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node.
 * You will be given the tree in class Node and you should return the cloned tree in class NodeCopy.
 * NodeCopy class is just a clone of Node class with the same attributes and constructors.
 */
/*
Questions
    1. the same node values? tree size?
 */
public class CloneBinaryTreeWithRandomPointer_1485 {

    // BF O(2n) - time, is to traverse first time to build the map. Traverse second time to build the result.

    // O(n) - time, O(n) - space
    public NodeCopy copyRandomBinaryTree(Node root) {
        Map<Node, NodeCopy> map = new HashMap<>();
        return dfs(root, map);
    }

    private NodeCopy dfs(Node root, Map<Node, NodeCopy> map) {
        if (root == null) {
            return null;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }

        NodeCopy node = new NodeCopy(root.val);
        map.put(root, node);
        node.left = dfs(root.left, map);
        node.right = dfs(root.right, map);
        node.random = dfs(root.random, map);

        return node;
    }

    public static class Node {
      int val;
      Node left;
      Node right;
      Node random;
      Node() {}
      Node(int val) { this.val = val; }
      Node(int val, Node left, Node right, Node random) {
          this.val = val;
          this.left = left;
          this.right = right;
          this.random = random;
      }
  }

    private static class NodeCopy extends Node {
        NodeCopy(int val) {
            super(val);
        }
    }
}
