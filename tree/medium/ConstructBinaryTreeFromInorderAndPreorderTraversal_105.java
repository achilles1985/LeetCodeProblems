package tree.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**M [marked]
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * For example, given
 * preorder = [3,9,20,15,7]
 * inorder = [9,3,15,20,7]
 * Return the following binary tree:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
/*
    All values are unique?
 */
/*
    The idea is to find the root first which is the first element in preorder array.
    Then find that element in inorder array. Then all element on the left would be left subtree, right - right subtree.
    Then recurse on the left and right subarrays of inorder till the leaf is found. While recursing increment preorderIndex to find the next root.
 */
public class ConstructBinaryTreeFromInorderAndPreorderTraversal_105 {

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPreorderTraversal_105 s = new ConstructBinaryTreeFromInorderAndPreorderTraversal_105();
        TreeUtils.print(s.buildTree(new int[]{1,2,4,5,3,6,7}, new int[]{4,2,5,1,6,3,7}));
        TreeUtils.print(s.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
        TreeUtils.print(s.buildTree(new int[]{1,2,3}, new int[]{2,3,1}));
    }

    // O(n) - time, space
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length-1, new AtomicInteger(), preorder, inorderMap);
    }

    private TreeNode buildTree(int[] inorder, int inStart, int inEnd, AtomicInteger preIdx, int[] preorder, Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd) {
            return null;
        }
        Integer rootIdx = inorderMap.get(preorder[preIdx.intValue()]);
        TreeNode root = new TreeNode(inorder[rootIdx]);
        preIdx.getAndIncrement(); // must be global
        root.left = buildTree(inorder, inStart, rootIdx-1, preIdx, preorder, inorderMap);
        root.right = buildTree(inorder, rootIdx+1, inEnd, preIdx, preorder, inorderMap);

        return root;
    }

}
