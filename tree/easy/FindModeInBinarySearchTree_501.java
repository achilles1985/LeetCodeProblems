package tree.easy;

import java.util.*;

import java.util.concurrent.atomic.AtomicInteger;
import utils.SolutionUtils;
import utils.TreeNode;

/**E
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in
 * the given BST.
 * Assume a BST is defined as follows:
 *     The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 *     The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 *     Both the left and right subtrees must also be binary search trees.
 *
 * For example:
 * Given BST [1,null,2,2],
 *    1
 *     \
 *      2
 *     /
 *    2
 * return [2].
 * Note: If a tree has more than one mode, you can return them in any order.
 * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to
 * recursion does not count).
 */
/*
    Edge cases:
        1. null root
        2. tree with one element
        3. All elements are the same
 */
public class FindModeInBinarySearchTree_501 {

    public static void main(String[] args) {
        FindModeInBinarySearchTree_501 s = new FindModeInBinarySearchTree_501();
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(6);
        root.right.left.right = new TreeNode(7);
        root.right.right = new TreeNode(10);
        root.right.right.right = new TreeNode(12);

        TreeNode root1 = new TreeNode(-2);
        root1.left = new TreeNode(-2);
        root1.right= new TreeNode(-2);

        TreeNode root2 = new TreeNode(6);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(0);
        root2.left.right = new TreeNode(4);
        root2.left.right.left = new TreeNode(2);
        root2.left.right.right = new TreeNode(6);
        root2.right = new TreeNode(8);
        root2.right.left = new TreeNode(7);
        root2.right.left.left = new TreeNode(9);

        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);
        root3.left.left = new TreeNode(1);
        root3.left.left.left = new TreeNode(1);
        root3.left.left.right = new TreeNode(1);
        root3.left.right = new TreeNode(1);
        root3.left.right.left = new TreeNode(1);
        root3.left.right.right = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.right.right = new TreeNode(5);
        root3.right.left = new TreeNode(2);
        root3.right.left.left = new TreeNode(2);
        root3.right.left.right = new TreeNode(3);
        root3.right.right.left = new TreeNode(4);
        root3.right.right.right = new TreeNode(6);

        TreeNode root4 = new TreeNode(3);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(1);
        root4.left.right = new TreeNode(2);
        root4.right = new TreeNode(4);
        root4.right.left = new TreeNode(3);
        root4.right.right = new TreeNode(5);

        SolutionUtils.print(s.findMode(root4)); //[2,3]
        SolutionUtils.print(s.findMode(root1)); //[-2]
        //SolutionUtils.print(s.findModeBF(root2));
    }

    // O(n) - time, space
    public int[] findModeBF(TreeNode root) {
        Map<Integer, Integer> frequency = new HashMap<>();
        populateFrequencyMap(root, frequency);
        int max = Collections.max(frequency.values());
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: frequency.entrySet()) {
            if (entry.getValue() == max) {
                result.add(entry.getKey());
            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }
    private void populateFrequencyMap(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return;
        }
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        populateFrequencyMap(root.left, map);
        populateFrequencyMap(root.right, map);
    }

    // https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/98196/4ms-Java-solution-beats-100-O(1)-space(recursion-stack-space-doesn't-count)
    // https://leetcode.com/problems/find-mode-in-binary-search-tree/discuss/130066/Java-Solution-No-extra-space
    // O(n) - time, O(1) - space. Incorrect
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        Set<Integer> modes = new HashSet<>();
        AtomicInteger count = new AtomicInteger(1);
        AtomicInteger maxCount = new AtomicInteger(1);
        helper(root, modes, count, maxCount, null);

        return modes.stream()
                .mapToInt(i->i)
                .toArray();
    }

    private void helper(TreeNode root, Set<Integer> modes, AtomicInteger count, AtomicInteger maxCount, TreeNode prev) {
        if (root == null) {
            return;
        }

        helper(root.left, modes, count, maxCount, root);
        if (prev != null && prev.val == root.val) {
            count.getAndIncrement();
        } else {
            count.set(1);
        }
        if (count.get() == maxCount.get()) {
            modes.add(root.val);
        } if (count.get() > maxCount.get()) {
            maxCount.set(count.get());
            modes.clear();
            modes.add(root.val);
        }
        helper(root.right, modes, count, maxCount, root);
    }


}
