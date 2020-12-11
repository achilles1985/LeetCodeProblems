package tree.medium;

import tree.utils.TreeUtils;
import utils.TreeNode;

/**
 * M [marked]
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root
 * node reference (possibly updated) of the BST.
 * Basically, the deletion can be divided into two stages:
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Note: Time complexity should be O(height of tree).
 * <p>
 * Example:
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *      5
 *     / \
 *    3   6
 *   / \   \
 *  2   4   7
 * <p>
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 * Another valid answer is [5,2,6,null,4,null,7].
 *     5
 *    / \
 *   2   6
 *   \   \
 *   4   7
 */
/*
 if element to be deleted has left and right subtrees, find next greater element in the right subtree,
 nextGreater.left = element.left, root.left = element.rightChild or root.right == element.leftChild
 */
public class DeleteNodeInBST_450 {

    public static void main(String[] args) {
        DeleteNodeInBST_450 s = new DeleteNodeInBST_450();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        TreeUtils.print(s.deleteNode(root, 6));
    }

    // O(n) - time, space
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) { // root is the key
            return mergeLeft(root.left, root.right);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    private TreeNode mergeLeft(TreeNode leftNode, TreeNode rightNode) {
        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
            return leftNode;
        }
        TreeNode iterator = rightNode; // looks for next greater
        while (iterator.left != null) {
            iterator = iterator.left;
        }
        iterator.left = leftNode;
        return rightNode;
    }

    public TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key > root.val) {
            root.right = deleteNode2(root.right, key);
        }
        else if (key < root.val) {
            root.left = deleteNode2(root.left, key);
        }
        else {
            if (root.left == null && root.right == null) {
                root = null;
            }
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode2(root.right, root.val);
            }
            else {
                root.val = predecessor(root);
                root.left = deleteNode2(root.left, root.val);
            }
        }

        return root;
    }

    /*
    One step right and then always left
    */
    private int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    /*
    One step left and then always right
    */
    private int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) {
            root = root.right;
        }
        return root.val;
    }

}
