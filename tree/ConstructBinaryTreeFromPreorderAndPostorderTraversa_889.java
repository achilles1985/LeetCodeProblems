package tree;

import java.util.HashMap;
import java.util.Map;

import tree.utils.TreeUtils;
import utils.TreeNode;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversa_889 {

    public static class Holder {
        int count;

        public int getCount() {
            return count;
        }

        public int incrementAndGet() {
            return  ++count;
        }
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndPostorderTraversa_889 s = new ConstructBinaryTreeFromPreorderAndPostorderTraversa_889();
        TreeUtils.print(s.constructFromPrePost(new int[]{1,2,4,9,10,6,3,7,8}, new int[]{9,10,4,6,2,7,8,3,1}));
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        Holder h1 = new Holder();
        return builtTree(pre, 0, post.length-1, map, h1.getCount(), h1);
    }

    private TreeNode builtTree(int[] pre, int postStart, int postEnd, Map<Integer, Integer> map, int preIdx, Holder h1) {
        if (postStart > postEnd || h1.getCount() > pre.length) {
            return null;
        }
        TreeNode node = new TreeNode(pre[preIdx]);
        Integer rootPost = map.get(pre[preIdx]);
        node.left = builtTree(pre, postStart, rootPost-1, map, h1.incrementAndGet(), h1);
        node.right = builtTree(pre, rootPost + 1, postEnd, map, h1.incrementAndGet(), h1);
        return node;
    }

    // O(n) - time, O(h) - space
    public TreeNode constructFromPrePost2(int[] pre, int[] post) {
        Map<Integer, Integer> postOrder = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            postOrder.put(post[i], i);
        }
        return construct(pre, 0, pre.length - 1, postOrder, 0, pre.length);
    }

    private TreeNode construct(int[] pre, int preLeft, int preRight, Map<Integer, Integer> postMap, int postLeft, int size) {
        if (size <= 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preLeft]);
        if (size == 1) {
            return root;
        }
        int index = postMap.get(pre[preLeft + 1]);
        int leftSize = index - postLeft + 1;
        int rightSize = size - 1 - leftSize;
        root.left = construct(pre, preLeft + 1, preLeft + leftSize, postMap, postLeft, leftSize);
        root.right = construct(pre, preLeft + leftSize + 1, preRight, postMap, postLeft + leftSize, rightSize);
        return root;
    }
}
