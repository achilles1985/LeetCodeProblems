package tree.medium;

import java.nio.ByteBuffer;
import java.util.*;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**
 * M [marked]
 *          10
 *         /  \
 *        5    20
 *       / \   / \
 *      1   7 15  30
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
/*
    Questions:
    1. Max size of the tree and max val in it?
    2. Only positive numbers?
    3. Duplicates?
 */
public class SerializeAndDeserializeBST_449 {

    public static void main(String[] args) {
        SerializeAndDeserializeBST_449 s = new SerializeAndDeserializeBST_449();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(30);

        TreeNode root2 = new TreeNode(12345);
        root2.left = new TreeNode(12344);
        root2.right = new TreeNode(12346);

        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);

        String s1 = s.serializeBF2(root3);
        TreeUtils.print(s.deserializeBF2(s1));

        String s2 = s.serialize(root);
        TreeUtils.print(s.deserialize(s2));
    }

    // Incorrect, if using inorder
    public String serializeBF2(TreeNode root) {
        if (root == null) {
            return "X";
        }
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);

        return sb.toString();
    }
    // Incorrect, if using inorder
    public TreeNode deserializeBF2(String data) {
        if (data.isEmpty()) {
            return null;
        }
        String[] arr = data.split(",");
        return toTree(arr, 0, arr.length-1);
    }

    private TreeNode toTree(String[] data, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right)/2;
        String valAsStr = data[mid];
        if ("X".equals(valAsStr)) {
            return null;
        }
        int val = Integer.parseInt(valAsStr);
        TreeNode node = new TreeNode(val);
        node.left = toTree(data, left, mid-1);
        node.right = toTree(data, mid+1, right);

        return node;
    }

    private void inorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        inorder(root.left, sb);
        sb.append(root.val).append(",");
        inorder(root.right, sb);
    }

    // O(n) - time, O(h) - space (only stack, result does not count. If it does, n+(n-1), where n - number of nodes, n-1 - number of delimeters)
    // The problem that if the val becomes larger, it start taking more space in the serialized string.
    // To make it occupy always the same space (4 bytes for int)+no delimiters and 'X'.
    public String serializeBF(TreeNode root) {
        if (root == null) {
            return "X";
        }
        return root.val + "," + serializeBF(root.left) + ',' + serializeBF(root.right);
    }

    public TreeNode deserializeBF(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return buildTreeBF(queue);
    }

    // O(n) - time, O(h) - space
    private TreeNode buildTreeBF(Queue<String> queue) {
        String val = queue.poll();
        if ("X".equals(val)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = buildTreeBF(queue);
        node.right = buildTreeBF(queue);

        return node;
    }

    // Most optimal solution
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        helper(root, sb);

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 0; i < data.length()/4; i++) {
            String numAsString = data.substring(i*4, 4 + i*4);
            int num = convertToInt(numAsString);
            nums.add(num);
        }

        return buildTree(nums, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private void helper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(convertToString(root.val));
        helper(root.left,sb);
        helper(root.right,sb);
    }

    private String convertToString(int num) {
        byte[] asBytes = convertToByteArray(num);

        return new String(asBytes);
    }

    private TreeNode buildTree(LinkedList<Integer> nums, int min, int max) {
        if (nums.isEmpty()) {
            return null;
        }
        int val = nums.getFirst();
        if (val < min || val > max) {
            return null;
        }
        nums.removeFirst();
        TreeNode node = new TreeNode(val);
        node.left = buildTree(nums, min, val);
        node.right = buildTree(nums, val, max);

        return node;
    }

    // https://stackoverflow.com/questions/11380062/what-does-value-0xff-do-in-java#:~:text=The%20hex%20literal%200xFF%20is,...
    private int convertToInt(String str) {
        byte[] asByteArr = str.getBytes();
        //return ByteBuffer.wrap(asByteArr).getInt();

        return ((0xFF & asByteArr[0]) << 24) |
                ((0xFF & asByteArr[1]) << 16) |
                ((0xFF & asByteArr[2]) << 8) |
                ((0xFF & asByteArr[3]));
    }

    private byte[] convertToByteArray(int num) {
        // return ByteBuffer.allocate(4).putInt(x).array();
        return new byte[] {
                (byte) (num >> 24),
                (byte) (num >> 16),
                (byte) (num >> 8),
                (byte) num};
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
        byte[] bytes3 = convertToByteArray(x);

        return new String(bytes3);
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
        int num2 = convertToInt(bytesStr);

        return num2;
    }

}
