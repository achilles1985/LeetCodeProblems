package stack;

import java.util.LinkedList;
import java.util.Queue;

import utils.TreeNode;

// https://leetcode.com/problems/binary-search-tree-iterator/
/** M
 Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 Calling next() will return the next smallest number in the BST.
        7
       / \
      3   15
         / \
        9   20
 Example:
 BSTIterator iterator = new BSTIterator(root);
 iterator.next();    // return 3
 iterator.next();    // return 7
 iterator.hasNext(); // return true
 iterator.next();    // return 9
 iterator.hasNext(); // return true
 iterator.next();    // return 15
 iterator.hasNext(); // return true
 iterator.next();    // return 20
 iterator.hasNext(); // return false

 Note:
 next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
 */
public class BSTIterator {
    private Queue<Integer> queue;

    public BSTIterator(TreeNode root) {
        queue = new LinkedList<>();
        populate(queue, root);
    }

    // O(n) - time, space
    private void populate(Queue<Integer> queue, TreeNode root) {
        if (root == null) {
            return;
        }
        populate(queue, root.left);
        queue.offer(root.val);
        populate(queue, root.right);
    }

    /** @return the next smallest number */
    // O(1) - time
    public int next() {
        return queue.poll();
    }

    /** @return whether we have a next smallest number */
    // O(1) - time
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }


}
