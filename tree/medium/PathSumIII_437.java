package tree.medium;

import utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

/** M [marked]
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 *
 * Example:
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * Return 3. The paths that sum to 8 are:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
 */
public class PathSumIII_437 {

    public static void main(String[] args) {
        PathSumIII_437 s = new PathSumIII_437();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(11);
        root.left.right.right = new TreeNode(1);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.right.right.right= new TreeNode(1);

        System.out.println(s.pathSumBF(root, 8)); //3
        System.out.println(s.pathSum(root, 8)); //3
    }

    // O(n^2) in worst case (no branching); O(nlogn) in best case (balanced tree). O(n) - space because of recursion
    public int pathSumBF(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        int left = pathSumBF(root.left, targetSum);
        int right = pathSumBF(root.right, targetSum);

        return left + right + helperBF(root, 0, targetSum);
    }

    // O(n) - time, space
    public int pathSum(TreeNode root, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] counter = new int[1];
        preorder(root, targetSum, 0, map, counter);

        return counter[0];
    }

    public int pathSum2(TreeNode root, int targetSum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);

        return helper(root, targetSum, 0, map);
    }

    private int helperBF(TreeNode root, int current, int target) {
        if (root == null) {
            return 0;
        }
        current += root.val;
        return (current == target ? 1 : 0) + helperBF(root.left, current, target) + helperBF(root.right, current, target);
    }

    private int helper(TreeNode root, int target, int sum, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }
        int count = 0;
        sum += root.val;
        if (map.containsKey(sum-target)) {
            count += map.get(sum-target);
        }
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        int left = helper(root.left, target, sum, map);
        int right = helper(root.right, target, sum, map);

        // remove current sum from the map
        map.put(sum, map.getOrDefault(sum, 0) - 1);

        return count + left + right;
    }

    private void preorder(TreeNode root, int sum, int curSum, Map<Integer, Integer> map, int[] counter) {
        if (root == null) {
            return;
        }
        curSum += root.val;
        if (curSum == sum) {
            counter[0]++;
        }
        counter[0] += map.getOrDefault(curSum - sum, 0);
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        preorder(root.left, sum, curSum, map, counter);
        preorder(root.right, sum, curSum, map, counter);

        // remove current sum from the map
        map.put(curSum, map.getOrDefault(curSum, 0) - 1);
    }
}
