package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**M
 * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the
 * itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
 *
 * Note:
 *     If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
 *     when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than
 *     ["JFK", "LGB"].
 *     All airports are represented by three capital letters (IATA code).
 *     You may assume all tickets form at least one valid itinerary.
 *
 * Example 1:
 * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
 *
 * Example 2:
 * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
 * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
 *              But it is larger in lexical order.
 */
/*
Main idea is to dfs on the graph and remove already visited nodes. To keep stations sorted and be able to easily remove nodes from collection, we use PriorityQueue.
graph = <String, PriorityQueue<String>>
 */
public class ReconstructItinerary_332 {

    public static void main(String[] args) {
        ReconstructItinerary_332 s = new ReconstructItinerary_332();
        System.out.println(s.findItinerary(Arrays.asList(
                Arrays.asList("JFK","KUL"),
                Arrays.asList("JFK","NRT"),
                Arrays.asList("NRT","JFK")))); // ["JFK","NRT","JFK","KUL"]
        System.out.println(s.findItinerary(Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO")))); // ["JFK", "MUC", "LHR", "SFO", "SJC"]
        System.out.println(s.findItinerary(Arrays.asList(
        Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")))); // ["JFK","ATL","JFK","SFO","ATL","SFO"]
    }

    // O(n*log(n)) - time, O(n) - space, n - number of stations
    public List<String> findItinerary(List<List<String>> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> result = new ArrayList<>();
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for (List<String> ticket: tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            graph.computeIfAbsent(from, key -> graph.getOrDefault(key, new PriorityQueue<>())).add(to);
        }
        dfs("JFK", graph, result);

        return result;
    }

    private void dfs(String station, Map<String, PriorityQueue<String>> graph, List<String> result) {
        PriorityQueue<String> queue = graph.getOrDefault(station, new PriorityQueue<>());
        while (!queue.isEmpty()) {
            dfs(queue.poll(), graph, result);
        }
        result.add(0, station);
    }

}
