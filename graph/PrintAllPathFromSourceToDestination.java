package graph;

import java.util.LinkedHashSet;
import java.util.Set;

import graph.utils.Graph;
import graph.utils.Node;

public class PrintAllPathFromSourceToDestination {

    public void printPath(Node start, Node dest) {
        Set<Node> visited = new LinkedHashSet<>();
        printPath(visited, dest,start);
    }

    private void printPath(Set<Node> visited, Node destination, Node current){
        if (visited.contains(current)) {
            return;
        }
        if (current.data == destination.data) {
            for (Node noe: visited) {
                System.out.print(noe.data + " ");
            }
            System.out.print(destination.data);
            System.out.println();
            return;
        }

        visited.add(current);
        for (Node child: current.adjacent) {
            printPath(visited, destination, child);
        }
        visited.remove(current);
    }

    public static void main(String args[]){
        Graph graph = new Graph(false);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.addEdge(6, 7);
        graph.addEdge(4, 7);
        graph.addEdge(1, 8);
        graph.addEdge(8, 9);
        graph.addEdge(9, 1);
        Node start = graph.getNodes().get(1);
        Node dest = graph.getNodes().get(7);

        PrintAllPathFromSourceToDestination pap = new PrintAllPathFromSourceToDestination();
        pap.printPath(start, dest);
    }
}
