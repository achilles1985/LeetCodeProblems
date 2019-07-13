package tree.MaximumDepthOfNaryTree_559;

import tree.Node;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.children = Arrays.asList(n3, n2, n4);
        n2.children = new ArrayList<>();
        n3.children = Arrays.asList(n5, n6);
        n4.children = new ArrayList<>();
        n5.children = new ArrayList<>();
        n6.children = new ArrayList<>();

        System.out.println(s.maxDepth(n1)); // 3
    }
}
