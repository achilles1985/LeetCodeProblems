package tree.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**M
 * Given inorder and postorder traversal of a tree, construct the binary tree.
 * Note:
 * You may assume that duplicates do not exist in the tree.
 * For example, given
 * inorder = [9,3,15,20,7]
 * postorder = [9,15,7,20,3]
 * Return the following binary tree:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 */
/*
    The idea is to find the root first which is the last element in postorder array.
    Then find that element in inorder array. Then all element on the left would be left subtree, right - right subtree.
    Then recurse on the right and left subarrays of inorder untill the leaf is found. While recursing decrement postorderIndex to find the next root.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal_106 {

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal_106 s = new ConstructBinaryTreeFromInorderAndPostorderTraversal_106();
        TreeUtils.print(s.buildTree(new int[]{4,2,5,1,6,3,7}, new int[]{4,5,2,6,7,3,1}));
        TreeUtils.print(s.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}));
    }

    // O(n) - time, O(h) - space
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeHelper(postorder, new AtomicInteger(postorder.length-1), 0, inorder.length-1, map);
    }

    private TreeNode buildTreeHelper(int[] postorder, AtomicInteger poIdx, int inStart, int inEnd, Map<Integer, Integer> map) {
        if (inStart > inEnd) {
            return null;
        }
        Integer inRoot = map.get(postorder[poIdx.intValue()]);
        TreeNode node = new TreeNode(postorder[poIdx.intValue()]);
        poIdx.getAndDecrement();
        node.right = buildTreeHelper(postorder, poIdx, inRoot+1, inEnd, map);
        node.left = buildTreeHelper(postorder, poIdx, inStart, inRoot-1, map);
        return node;
    }

}
