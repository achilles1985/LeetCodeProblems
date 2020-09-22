package tree.medium;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**M
Return the root node of a binary search tree that matches the given preorder traversal.
(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.
 Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)

Example 1:
Input: [8,5,1,7,10,12]
 *     8
 *    / \
 *   5  10
 *  / \   \
 * 1  7   12
Output: [8,5,10,1,7,null,12]

Note:
    1 <= preorder.length <= 100
    The values of preorder are distinct.
 */
public class ConstructBinarySearchTreeFromPreorderTraversal_1008 {

    public static void main(String[] args) {
        ConstructBinarySearchTreeFromPreorderTraversal_1008 s = new ConstructBinarySearchTreeFromPreorderTraversal_1008();
        TreeUtils.print(s.bstFromPreorder(new int[]{8,5,1,7,10,12}));
        TreeUtils.print(s.bstFromPreorder(new int[]{4,2}));
    }

    // O(n^2) - time, O(h) - space
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return bstFromPreorder(preorder, 0, preorder.length-1);
    }

    private TreeNode bstFromPreorder(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[start]);
        int rightStart = findRightStartIndex(preorder, start, end);
        node.left = bstFromPreorder(preorder, start+1, rightStart-1);
        node.right = bstFromPreorder(preorder, rightStart, end);

        return node;
    }

    // For BST right start index must be > then current root
    private int findRightStartIndex(int[] preorder, int start, int end) {
        int i = start + 1;
        while (i <= end && preorder[start] > preorder[i]) {
            i++;
        }
        return i;
    }
}
