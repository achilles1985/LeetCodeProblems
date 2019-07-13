package graph.CloneGraph_113;

import graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** M
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.
 Example:
    1-2
    | |
    4-3
 Input:
 {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}

 Explanation:
 Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 Node 4's value is 4, and it has two neighbors: Node 1 and 3.

 Note:
 The number of nodes will be between 1 and 100.
 The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 You must return the copy of the given node as a reference to the cloned graph.
 */
public class Solution {

    // O(n) - space, O(n) - time, n- number of nodes
    public Node cloneGraph(Node node) {
        if (node == null || node.adjacent == null || node.adjacent.isEmpty()) {
            Node clone = new Node(node.data);
            clone.adjacent = new ArrayList<>();
            return clone;
        }
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Node> map = new HashMap<>();
        Node clone = new Node(node.data);
        map.put(clone.data, clone);
        cloneGraph(node, clone, visited, map);

        return clone;
    }

    private void cloneGraph(Node current, Node clone, Set<Integer> visited, Map<Integer, Node> map) {
        if (visited.contains(current.data)) {
            return;
        }
        visited.add(current.data);
        for (Node child: current.adjacent) {
            Node childClone = map.get(child.data);
            if (childClone == null) {
                childClone = new Node(child.data);
                map.put(child.data, childClone);
            }
            clone.adjacent.add(childClone);
            cloneGraph(child, childClone, visited, map);
        }
    }
}
