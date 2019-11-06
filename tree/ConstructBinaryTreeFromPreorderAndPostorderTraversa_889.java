package tree;

import java.util.HashMap;
import java.util.Map;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**M
 * Return any binary tree that matches the given preorder and postorder traversals.
 * Values in the traversals pre and post are distinct positive integers.
 * Given two arrays that represent preorder and postorder traversals of a full binary tree, construct the binary tree.
 * A Full Binary Tree is a binary tree where every node has either 0 or 2 children
 *
 * Following are examples of Full Trees.
 *         1
 *       /   \
 *     2       3
 *   /  \     /  \
 *  4    5   6    7
 *
 *        1
 *      /   \
 *    2      3
 *         /   \
 *        4     5
 *            /   \
 *           6    7
 *
 *           1
 *         /   \
 *       2       3
 *     /  \     /  \
 *    4    5   6    7
 *  /  \
 * 8    9
 * Note:
 *     1 <= pre.length == post.length <= 30
 *     pre[] and post[] are both permutations of 1, 2, ..., pre.length.
 *     It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
public class ConstructBinaryTreeFromPreorderAndPostorderTraversa_889 {

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndPostorderTraversa_889 s = new ConstructBinaryTreeFromPreorderAndPostorderTraversa_889();
        TreeUtils.print(s.constructFromPrePost2(new int[]{1,2,4,5,3,6,7}, new int[]{4,5,2,6,7,3,1}));
    }

    // O(n) - time, O(h) - space
    public TreeNode constructFromPrePost2(int[] pre, int[] post) {
        Map<Integer, Integer> postOrder = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            postOrder.put(post[i], i);
        }
        return constructFromPrePost2Helper(pre, 0, pre.length - 1, postOrder, 0, pre.length);
    }
    private TreeNode constructFromPrePost2Helper(int[] pre, int preLeft, int preRight, Map<Integer, Integer> postMap, int postLeft, int size) {
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
        root.left = constructFromPrePost2Helper(pre, preLeft + 1, preLeft + leftSize, postMap, postLeft, leftSize);
        root.right = constructFromPrePost2Helper(pre, preLeft + leftSize + 1, preRight, postMap, postLeft + leftSize, rightSize);
        return root;
    }

    // Incorrect solution
    public TreeNode constructFromPrePost3(int[] pre, int[] post) {
        if (pre == null || post == null) {
            return null;
        }
        Map<Integer, Integer> postMap = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            postMap.put(post[i], i);
        }
        return constructFromPrePost3Helper(pre, new PreorderIndex(), 0, post.length-1, postMap);
    }

    private TreeNode constructFromPrePost3Helper(int[] preorder, PreorderIndex preIdx, int postS, int postEnd, Map<Integer, Integer> postMap) {
        if (postS > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preIdx.index]);
        int rootIdx = postMap.get(preorder[preIdx.index]);
        preIdx.increment();
        int leftIdx = postMap.get(preorder[preIdx.index]);
        root.left = constructFromPrePost3Helper(preorder, preIdx, postS, leftIdx-1, postMap);
        root.right = constructFromPrePost3Helper(preorder, preIdx, leftIdx+1, rootIdx-1, postMap);

        return root;
    }

    private static class PreorderIndex {
        int index;

        void increment() {
            index++;
        }
    }
}
