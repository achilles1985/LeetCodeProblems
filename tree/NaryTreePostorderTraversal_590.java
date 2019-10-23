package tree;

import tree.utils.Node;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class NaryTreePostorderTraversal_590 {

    public List<Integer> postorder(Node root) {
        Stack<Node> s = new Stack<>();
        List<Integer> res = new LinkedList<>();
        if (root != null) {
            s.push(root);
        }
        while (! s.isEmpty()) {
            Node n = s.pop();
            res.add(n.val);
            for (Node c : n.children) {
                s.push(c);
            }
        }
        Collections.reverse(res);
        return res;
    }
}
