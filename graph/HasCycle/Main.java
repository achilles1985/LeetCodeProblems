package graph.HasCycle;

import graph.utils.Graph;

public class Main {

    public static void main(String[] args) {
        // directed
        Graph graph1 = new Graph(false);
        graph1.addEdge(1,2);
        graph1.addEdge(2,3);
        graph1.addEdge(3,4);
        graph1.addEdge(4,2);

        Graph graph2 = new Graph(false);
        graph2.addEdge(1,2);
        graph2.addEdge(2,3);
        graph2.addEdge(3,4);

        // undirected
        Graph graph3 = new Graph(true);
        graph3.addEdge(1,2);
        graph3.addEdge(2,3);
        graph3.addEdge(3,4);
        graph3.addEdge(4,2);

        Graph graph4 = new Graph(true);
        graph4.addEdge(1,2);
        graph4.addEdge(2,3);
        graph4.addEdge(3,4);

        Graph graph5 = new Graph(true);
        graph5.addEdge(1,2);
        graph5.addEdge(1,3);
        graph5.addEdge(2,3);
        graph5.addEdge(4,1);
        graph5.addEdge(4,5);
        graph5.addEdge(5,6);
        graph5.addEdge(6,4);

        System.out.println(new DFSolution().hasCycleUndirectedGraph(graph1)); // true
        System.out.println(new DFSolution().hasCycleUndirectedGraph(graph5)); // false

        System.out.println(new DisjointSetSolution().hasCycle(graph1)); // true
        System.out.println(new DisjointSetSolution().hasCycle(graph5)); // false

        System.out.println(new DFSolution().hasCycleDirectedGraph(graph3)); // true
        System.out.println(new DFSolution().hasCycleDirectedGraph(graph4)); // false
        System.out.println(new DFSolution().hasCycleDirectedGraph(graph5)); // true
    }
}
