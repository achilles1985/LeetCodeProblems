package tree.SumRootToLeafNumbers_129;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** M
 Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 An example is the root-to-leaf path 1->2->3 which represents the number 123.
 Find the total sum of all root-to-leaf numbers.
 Note: A leaf is a node with no children.

 Example:
 Input: [1,2,3]
    1
  / \
 2   3
 Output: 25
 Explanation:
 The root-to-leaf path 1->2 represents the number 12.
 The root-to-leaf path 1->3 represents the number 13.
 Therefore, sum = 12 + 13 = 25.

 Example 2:
 Input: [4,9,0,5,1]
     4
    / \
   9   0
  / \
 5   1
 Output: 1026
 Explanation:
 The root-to-leaf path 4->9->5 represents the number 495.
 The root-to-leaf path 4->9->1 represents the number 491.
 The root-to-leaf path 4->0 represents the number 40.
 Therefore, sum = 495 + 491 + 40 = 1026.
 */

// https://leetcode.com/problems/sum-root-to-leaf-numbers/discuss/302113/Java-Recursive-solution-beats-100-in-both-Runtime-and-Memory-Usage
public class Solution {

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> numbers = new ArrayList<>();
        sumNumbers(root, 0, numbers);

        return numbers.stream()
                .mapToInt(i -> i)
                .sum();
    }

    private void sumNumbers(TreeNode node, int number, List<Integer> numbers) {
        number = number*10 + node.val;
        if (node.left == null && node.right == null) {
            numbers.add(number);
            return;
        }

        if (node.left != null) sumNumbers(node.left, number, numbers);
        if (node.right != null) sumNumbers(node.right, number, numbers);
    }

    public int sumNumbers2(TreeNode root) {
        return sumNumbers2(root, 0);
    }

    public int sumNumbers2(TreeNode root, int val) {
        if (root == null)
            return 0;

        val = 10 * val + root.val;
        if (root.left == null && root.right == null)
            return val;

        return sumNumbers2(root.left, val) + sumNumbers2(root.right, val);
    }

    public int sumNumbers3(TreeNode root) {
        return DFS(root,0);
    }

    private int DFS(TreeNode node, int sum) {
        if(node == null) {
            return sum;
        }
        else if(node.left == null && node.right == null) {
            return sum*10 + node.val;
        }
        else if(node.left == null) {
            return DFS(node.right, sum*10+node.val);
        }
        else if(node.right == null) {
            return DFS(node.left, sum*10+node.val);
        }
        else {
            return DFS(node.left, sum*10+node.val) + DFS(node.right, sum*10+node.val);
        }
    }
}
