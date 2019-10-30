package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        SolutionUtils.print(s.findMode2(root)); //[3,6]
        SolutionUtils.print(s.findMode2(root1)); //[-2]
        SolutionUtils.print(s.findMode(root2));
    }

    // O(n) - time, space
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> frequency = new HashMap<>();
        populateFrequencyMap(root, frequency);
        int max = 0;
        for (Map.Entry<Integer, Integer> entry: frequency.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
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

    // O(n) - time, O(1) - space. Incorrect solution
    public int[] findMode2(TreeNode root) {
        return findMode2Helper(root, null, 0).elements.stream().mapToInt(i->i).toArray();
    }

    private Holder findMode2Helper(TreeNode current, TreeNode parent, int maxFrequency) {
        if (current == null) {
            return new Holder(0, new HashSet<>());
        }
        int localFrequency = (parent != null && parent.val == current.val) ? maxFrequency + 1 : 0;
        Set<Integer> elements = new HashSet<>();
        elements.add(current.val);
        Holder holder = new Holder(localFrequency, elements);
        Holder leftHolder = findMode2Helper(current.left, current, localFrequency);
        Holder rightHolder = findMode2Helper(current.right, current, localFrequency);

        return HolderUtils.max(holder, HolderUtils.max(leftHolder, rightHolder));
    }

    private static class Holder implements Comparable<Holder>{
        private int frequency;
        private Set<Integer> elements;

        public Holder(int frequency, Set<Integer> elements) {
            this.frequency = frequency;
            this.elements = elements;
        }

        @Override
        public int compareTo(Holder o) {
            if (this.frequency == o.frequency) {
                return 0;
            }
            return this.frequency > o.frequency ? 1 : -1;
        }
    }

    private static final class HolderUtils {

        public static Holder max(Holder h1, Holder h2) {
            if (h1 == null) {
                return h2;
            }
            if (h2 == null) {
                return h1;
            }
            if (h1.compareTo(h2) == 0) {
                Set<Integer> merged = new HashSet<>();
                merged.addAll(h1.elements);
                merged.addAll(h2.elements);
                return new Holder(h1.frequency, merged);
            }
            return h1.compareTo(h2) > 0 ? h1 : h2;
        }
    }
}
