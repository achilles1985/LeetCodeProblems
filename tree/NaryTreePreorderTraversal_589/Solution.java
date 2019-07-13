package tree.NaryTreePreorderTraversal_589;

import tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> list = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            list.add(node.val);
            for (int i = node.children.size() - 1; i <= 0; i--) {
                if (node.children.get(i) != null) {
                    stack.push(node.children.get(i));
                }
            }
        }

        return list;
    }
}
