import java.util.LinkedList;
import java.util.Queue;

public class LC785IsGraphBipartite {
    /**
     * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.
     *
     * Example 1:
     * Input: [[1,3], [0,2], [1,3], [0,2]]
     * Output: true
     * Explanation:
     * The graph looks like this:
     * 0----1
     * |    |
     * |    |
     * 3----2
     * We can divide the vertices into two groups: {0, 2} and {1, 3}.
     *
     */

    public static boolean isBipartite(int[][] graph) {
        int len = graph.length;
        int[] colors = new int[len];

        for (int i = 0; i < len; i++) {
            if (colors[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            colors[i] = 1;   // Blue: 1 and Rew: -1

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int next : graph[i]) {
                    if (colors[next] == 0) {          // If this node hasn't been colored;
                        colors[next] = -colors[cur];  // Color it with a different color;
                        queue.offer(next);
                    } else if (colors[next] != colors[cur]) { // If it is colored and its color is different, return false;
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
     * Initialize a color[] array for each node. Here are three states for colors[] array:
     * 0: Haven't been colored yet.
     * 1: Blue.
     * -1: Red.
     * For each node,
     *
     * If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
     * If it has been colored, check if the current color is the same as the color that is going to be used to color it. (Please forgive my english... Hope you can understand it.)
     * DFS Solution:
     *
     * time = O(N + E) where N is the number of nodes int the graph, E is the number of edges
     * space = O(N) , the space used to store the color
     */
    public static boolean isBipartite2(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {  //This graph might be a disconnected graph. So check each unvisited node.
            if (colors[i] == 0 && !validColor(graph, colors, 1, i)) {
                return false;
            }
        }
        return true;
    }
    private static boolean validColor(int[][] graph, int[] colors, int color, int node) {
        if (colors[node] != 0) {
            return colors[node] == color;
        }

        colors[node] = color;
        for (int next : graph[node]) {
            if (!validColor(graph, colors, -color, node)) {
                return false;
            }
        }
        return true;
    }
}
