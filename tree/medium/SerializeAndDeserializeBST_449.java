package tree.medium;

import java.nio.ByteBuffer;
import java.util.*;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**
 * M [MARKED]
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
 * serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be
 * serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * The encoded string should be as compact as possible.
 * <p>
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms
 * should be stateless.
 */
public class SerializeAndDeserializeBST_449 {

    public static void main(String[] args) {
        SerializeAndDeserializeBST_449 s = new SerializeAndDeserializeBST_449();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(12345);
        root2.left = new TreeNode(12344);
        root2.right = new TreeNode(12346);

        TreeNode root3 = new TreeNode(1757700657); //

        String s3 = s.serialize2(root3);
        TreeUtils.print(s.deserialize(s3));

        String s1 = s.serialize(root);
        TreeUtils.print(s.deserialize(s1));

        String s2 = s.serialize2(root2);
        TreeUtils.print(s.deserialize2(s2));
    }

    // Encodes a tree to a single string. Do preorder traversal
    // O(n) - time, O(h) - space
    public String serialize(TreeNode root) {
        if (root == null) {
            return "X";
        }

        final String left = serialize(root.left);
        final String right = serialize(root.right);
        return root.val + "," + left + "," + right;
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

    // Optimized on space (no delimeters, each number as 4 byte string)
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        return sb.toString();
    }

    public TreeNode deserialize2(String data) {
        Deque<Integer> nums = new ArrayDeque<>();
        for (int i = 0; i < data.length() / 4; ++i) {
            nums.add(stringToInt(data.substring(4 * i, 4 * i + 4)));
        }

        return helper(Integer.MIN_VALUE, Integer.MAX_VALUE, nums);
    }

    // Encodes a tree to a list.
    private void postorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        postorder(root.left, sb);
        postorder(root.right, sb);
        sb.append(intToString(root.val));
    }

    // Encodes integer to bytes string
    private String intToString(int x) {
        // a)
        char[] bytes = new char[4];
        for (int i = 3; i > -1; --i) {
            bytes[3 - i] = (char) (x >> (i * 8) & 0xff);
        }
        // b)
        byte[] bytes2 = new byte[4];
        for (byte i = 0; i < 4; i++) {
            bytes2[i] = (byte) (x >> i * 8);
        }

        // c)
        byte[] asBytes = ByteBuffer.allocate(4).putInt(x).array();
        String asStr = Arrays.toString(asBytes);

        // d)
        byte[] asBytes1 = convertIntToByteArray(x);

        return new String(bytes);
    }

    private TreeNode helper(Integer lower, Integer upper, Deque<Integer> nums) {
        if (nums.isEmpty()) {
            return null;
        }
        int val = nums.getLast();
        if (val < lower || val > upper) {
            return null;
        }
        nums.removeLast();
        TreeNode root = new TreeNode(val);
        root.right = helper(val, upper, nums);
        root.left = helper(lower, val, nums);

        return root;
    }

    private int stringToInt(String bytesStr) {
        int result = 0;
        // a)
        for (char b : bytesStr.toCharArray()) {
            result = (result << 8) + (int) b;
        }

        // b)
        int num = ByteBuffer.wrap(bytesStr.getBytes()).getInt();

        //d
        int num1 = convertByteArrayToInt(bytesStr.getBytes());

        return result;
    }

    // alternative
    public static byte[] convertIntToByteArray(int value) {
        return new byte[] {
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value};
    }
    public static int convertByteArrayToInt(byte[] bytes) { // https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java#:~:text=The%20hex%20literal%200xFF%20is,...
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8) |
                ((bytes[3] & 0xFF) << 0);
    }

}
