package tree;

import java.util.HashMap;
import java.util.Map;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**M
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
public class ConstructBinaryTreeFromPreorderAndInorderTraversal_105 {

    public static void main(String[] args) {
        ConstructBinaryTreeFromPreorderAndInorderTraversal_105 s = new ConstructBinaryTreeFromPreorderAndInorderTraversal_105();
        //TreeUtils.print(s.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));
        TreeUtils.print(s.buildTree(new int[]{1,2,4,5,3,6,7}, new int[]{4,2,5,1,6,3,7}));
        //TreeUtils.print(s.buildTree(new int[]{1,2,3}, new int[]{2,3,1}));
    }

    // O(n) - time, space
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> inIndexByValue = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inIndexByValue.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length-1, 0, inorder.length-1, inIndexByValue);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int inStart, int inEnd, Map<Integer, Integer> inIndexByValue) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        Integer rootInorderIdx = inIndexByValue.get(preorder[preStart]);
        int leftSubtreeSize = rootInorderIdx - inStart;
        TreeNode node = new TreeNode(preorder[preStart]);
        node.left = buildTree(preorder, preStart + 1, preStart + leftSubtreeSize, inStart, rootInorderIdx - 1, inIndexByValue);
        node.right = buildTree(preorder, preStart + leftSubtreeSize + 1, preEnd, rootInorderIdx + 1, inEnd, inIndexByValue);

        return node;
    }
}
