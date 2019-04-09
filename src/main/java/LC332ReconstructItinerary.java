import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class LC332ReconstructItinerary {
    /**
     * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
     * Example 1:
     *
     * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     * Example 2:
     *
     * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
     *              But it is larger in lexical order.
     */

    // time = O(nlogn), space = O(n)
    HashMap<String, PriorityQueue<String>> map;
    List<String> res;
    public List<String> findItinerary(String[][] tickets) {
        // DFS, 排序用min-heap
        map = new HashMap<>();
        res = new LinkedList<>();   // 这里用linkedlist而不是arraylist是因为用前者可以用add(0, ***)方法

        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<>());
            map.get(ticket[0]).add(ticket[1]);
        }

        dfs("JFK");
        return res;
    }

    private void dfs(String airport) {
        while (map.containsKey(airport) && !map.get(airport).isEmpty()) {
            // 有起点并且终点
            dfs(map.get(airport).poll());
        }
        res.add(0, airport);
    }

    public static void main(String[] args) {
        String[][] tickets1 = new String[][]{
                {"MUC", "LHR"},
                {"JFK", "MUC"},
                {"SFO", "SJC"},
                {"LHR", "SFO"}
        };
        String[][] tickets2 = new String[][]{
                {"JFK","SFO"},
                {"JFK","ATL"},
                {"SFO","ATL"},
                {"ATL","JFK"},
                {"ATL","SFO"}
        };
        LC332ReconstructItinerary obj = new LC332ReconstructItinerary();
        System.out.println(obj.findItinerary(tickets1));
        System.out.println(obj.findItinerary(tickets2));
    }
}
