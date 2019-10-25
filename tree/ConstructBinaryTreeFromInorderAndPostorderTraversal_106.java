package tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    Then recurse on the left subarray and find its root in postorder array by traversing through it starting from the end till find the first occurrence of that
    element in postorder array.
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal_106 {

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal_106 s = new ConstructBinaryTreeFromInorderAndPostorderTraversal_106();
        TreeUtils.print(s.buildTree(new int[]{4,2,5,1,6,3,7}, new int[]{4,5,2,6,7,3,1}));
        TreeUtils.print(s.buildTree(new int[]{9,3,15,20,7}, new int[]{9,15,7,20,3}));
    }

    // O(n*log(n)) - O(n^2) - time, space. Time/space can be improve if to determine how to get next root in O(1) - time. like in https://github.com/mission-peace/interview/blob/master/src/com/interview/tree/ContructTreeFromInorderPostOrder.java
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, 0, inorder.length-1, postorder, postorder.length-1, map);
    }

    private TreeNode buildTree(int[] inorder, int inorderStart, int inorderEnd, int[] postorder, int postorderEnd, Map<Integer, Integer> map) {
        if (inorderStart > inorderEnd || postorderEnd < 0) {
            return null;
        }
        int inorderRootIdx = map.get(postorder[postorderEnd]);
        TreeNode node = new TreeNode(postorder[postorderEnd]);
        node.left = buildTree(inorder, inorderStart, inorderRootIdx - 1, postorder, findIndex(inorder, postorder, inorderStart, inorderRootIdx - 1), map);
        node.right = buildTree(inorder, inorderRootIdx + 1, inorderEnd, postorder, findIndex(inorder, postorder, inorderRootIdx + 1, inorderEnd), map);

        return node;
    }

    private int findIndex(int[] inorder, int[] postorder, int inorderStart, int inorderEnd) {
        Set<Integer> set = new HashSet<>();
        for (int i = inorderStart; i <= inorderEnd; i++) {
            set.add(inorder[i]);
        }
        for (int i = postorder.length-1; i >= 0; i--) {
            if (set.contains(postorder[i])) {
                return i;
            }
        }
        return -1;
    }
}
