package tree.medium;

import tree.utils.TreeUtils;
import utils.TreeNode;

import java.util.Stack;

/**
 * M [marked]
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same
 * structure.
 * You always start to construct the left child node of the parent first if it exists.
 * <p>
 * Note:
 * There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 * An empty tree is represented by "" instead of "()".
 * <p>
 * Example 1:
 * Input: "-4(2(3)(1))(6(5)(7)))"
 * Output: {-4,2,6,3,1,5}
 * Explanation:
 * The output is look like this:
 *     -4
 *    /  \
 *   2    6
 *  / \   /\
 * 3   1 5  7
 * <p>
 * Example 2:
 * Input: "1(-1)"
 * Output: {1,-1}
 * Explanation:
 * The output is look like this:
 *    1
 *   /
 * -1
 */
/*
Questions:
    1. Only positive? Only '(',')','-' and number?
    2. Max string length? Max number?
Mistakes:
    1. Be careful with start/end substring indexes.
    2. stack is not always must be empty at the end. At the end it contains the root of the tree.
 */
public class ConstructBinaryTreeFromString_536 {

    public static void main(String[] args) {
        ConstructBinaryTreeFromString_536 s = new ConstructBinaryTreeFromString_536();
        TreeUtils.print(s.str2tree("-4(2(3)(1))(6(5)(7))"));
        TreeUtils.print(s.str2tree("-14(2(3)(4))(5(6)(78))"));
        TreeUtils.print(s.str2tree("1(-1)"));

        TreeUtils.print(s.str2treeIterative("-4(2(3)(1))(6(5)(7))"));
        TreeUtils.print(s.str2treeIterative("-14(2(3)(4))(5(6)(78))"));
        TreeUtils.print(s.str2treeIterative("1(-1)"));
    }

    // O(n) - time, O(h) - space
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int i = 0, j = 0;
        while (j < s.length() && Character.isDigit(s.charAt(j)) || j < s.length() && s.charAt(j) == '-') {
            j++;
        }
        TreeNode node = new TreeNode(Integer.parseInt(s.substring(i, j)));
        if (j < s.length()) {
            j++;
            i = j;
            int count = 1;
            while (j < s.length() && count != 0) {
                j++;
                if (s.charAt(j) == '(') count++;
                if (s.charAt(j) == ')') count--;
            }
            node.left = str2tree(s.substring(i, j));
        }
        j++;
        if (j < s.length()) {
            node.right = str2tree(s.substring(j + 1, s.length() - 1));
        }
        return node;
    }

    // O(n) - time, O(h) - space
    public TreeNode str2treeIterative(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for (int i = 0, j = i; i < s.length(); i++, j = i) {
            char c = s.charAt(i);
            if (c == ')') {
                stack.pop();
            } else if (c >= '0' && c <= '9' || c == '-') {
                while (i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') {
                    i++;
                }
                TreeNode currentNode = new TreeNode(Integer.parseInt(s.substring(j, i + 1)));
                if (!stack.isEmpty()) {
                    TreeNode parent = stack.peek();
                    if (parent.left != null) {
                        parent.right = currentNode;
                    } else {
                        parent.left = currentNode;
                    }
                }
                stack.push(currentNode);
            }
        }

        return stack.isEmpty() ? null : stack.peek();
    }

}
