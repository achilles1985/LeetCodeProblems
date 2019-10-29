package tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**M
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be
 * serialized to a string and this string can be deserialized to the original tree structure.
 *
 * The encoded string should be as compact as possible.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class SerializeAndDeserializeBST_449 {

    public static void main(String[] args) {
        SerializeAndDeserializeBST_449 s = new SerializeAndDeserializeBST_449();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.right = new TreeNode(8);
        root.right.left = new TreeNode(0);

        String serialized = s.serialize(root);
        TreeUtils.print(s.deserialize(serialized));
    }

    // Encodes a tree to a single string.
    // O(n) - time, O(h) - space
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    // O(n) - time, O(h) - space
    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if ("X".equals(val)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buildTree(queue);
        node.right = buildTree(queue);

        return node;
    }

}
