package design;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**H
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized
 * to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 *
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need
 * to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class SerializeAndDeserializeBinaryTree_297 {

    // O(n) - time
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }

    // O(n) - time
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTree(queue);
    }

    private TreeNode buildTree(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("X")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTree(queue);
        node.right = buildTree(queue);
        return node;
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree_297 s = new SerializeAndDeserializeBinaryTree_297();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.right.right = new TreeNode(8);
        root.right.left = new TreeNode(0);

        String serialized = s.serialize(root);
        TreeUtils.print(s.deserialize(serialized));
    }
}