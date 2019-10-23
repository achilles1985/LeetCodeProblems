package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import tree.utils.Node;

public class NaryTreePreorderTraversal_589 {

    // O(n) - time, space
    public List<Integer> preorder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node popped = stack.pop();
            result.add(popped.val);
            if (popped.children != null) {
                for (int i = popped.children.size()-1; i >= 0; i--) {
                    stack.push(popped.children.get(i));
                }
            }
        }
        return result;
    }
}
