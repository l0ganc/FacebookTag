import java.util.*;

public class LC133CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val, List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

    // time : O(m + n) m : nodes n : edges  space : O(m)
    // DFS
    public Node cloneGraph(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> map = new HashMap<>();
        helper(node, map);
        return map.get(node);
    }

    private static void helper(Node node, Map<Node, Node> map) {
        if (node == null || map.containsKey(node)) {
            return;
        }

        Node newNode = new Node(node.val, new ArrayList<>());
        map.put(node, newNode);
        for (Node neighbor : node.neighbors) {
            if (!map.containsKey(neighbor)) {
                helper(neighbor, map);
            }
            map.get(node).neighbors.add(map.get(neighbor));
        }
    }


    // time : O(m + n) m : nodes n : edges  space : O(m)
    // BFS
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }
        Map<Node, Node> map = new HashMap<>();
        map.put(node, null);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            Node newNode = new Node(cur.val, new ArrayList<>());
            map.put(cur, newNode);

            for (Node neighbor : cur.neighbors) {
                if (!map.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    map.put(neighbor, null);
                }
            }
        }

        for (Node original : map.keySet()) {
            Node copy = map.get(original);
            for (Node neighbor : original.neighbors) {
                copy.neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }
}
