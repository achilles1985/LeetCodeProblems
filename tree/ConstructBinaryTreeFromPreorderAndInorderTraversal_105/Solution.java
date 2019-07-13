package tree.ConstructBinaryTreeFromPreorderAndInorderTraversal_105;

import utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/** M
 * Given preorder and inorder traversal of a tree, construct the binary tree.
  Note:
 You may assume that duplicates do not exist in the tree.

 For example, given
 preorder = [3,9,20,15,7]
 inorder = [9,3,15,20,7]

 Return the following binary tree:
      3
     / \
    9  20
       / \
      15  7
 */
public class Solution {

    /** Note:
     * 1. pre[0] is the root
     * 2. In inorder array, all elements to the left of pre[i] forms its left sub-tree
     *    and all elements to the right of pre[i] forms its right sub-tree */
// build a hash of value to index by iterating through in-order array
    public TreeNode buildTree(int[] pre, int[] in) {
        Map<Integer, Integer> valueToIdx = new HashMap<>();
        for (int i = 0; i < in.length; i++)
            valueToIdx.put(in[i], i);
        return buildTree(pre, new int[]{0}, 0, in.length - 1, valueToIdx);
    }

    /**
     * 1. Maintain a pointer that progresses through pre-order one element at time, say prePointer
     * 2. Construct the node corresponding to prePointer
     * 3. inStart and inEnd pointers point to the start and end of the left/right subtree in the inorder array
     * 4. start == end implies that it's a leaf node, so there's no need to explore its left or right subtree as
     * there are none. The reason it's a leaf node is that start == end implies that there's exactly one element to the
     * right or to the left of its parent, which is this very node.
     * 5. Get the index of the current element using the hash. Remember this is the index of the element in the
     * inorder array. Use this index to get the ranges of the left and right subtrees.
     * 6. Recurse for left and right subtrees.
     */
    private TreeNode buildTree(int[] pre, int[] prePointer, int inStart, int inEnd, Map<Integer, Integer> valueToIdx) {
        if (inStart > inEnd)
            return null;
        int currNodeVal = pre[prePointer[0]++];
        TreeNode currNode = new TreeNode(currNodeVal);
        if (inStart == inEnd) // leaf node
            return currNode;
        int inIdxCurrNode = valueToIdx.get(currNodeVal);
        currNode.left = buildTree(pre, prePointer, inStart, inIdxCurrNode - 1, valueToIdx);
        currNode.right = buildTree(pre, prePointer, inIdxCurrNode + 1, inEnd, valueToIdx);
        return currNode;
    }
}
