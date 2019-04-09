import java.util.*;

public class LC269AlienDictionary {
    /**
     * Example 1:
     *
     * Input:
     * [
     *   "wrt",
     *   "wrf",
     *   "er",
     *   "ett",
     *   "rftt"
     * ]
     *
     * Output: "wertf"
     * Example 2:
     *
     * Input:
     * [
     *   "z",
     *   "x"
     * ]
     *
     * Output: "zx"
     */

    // Topological Sorting经典题
    // 拓扑排序的题，三步骤，一：建图 二：入度为0 三：BFS
    // Time complexity = O(n + m) - n represents all vertices, m represents all edges
    // Space complexity = O(n + m) - n represents all vertices, m represents all edges
    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        // key是out char, value是key里的out能指向的所有in chars的set集合
        Map<Character, Set<Character>> map = new HashMap<>();
        int[] inDegree = new int[26];

        // 建图
        for (String word : words) {
            for (char c : word.toCharArray()) {
                map.putIfAbsent(c, new HashSet<>());
            }
        }
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());
            for (int j = 0; j < len; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                if (out != in) {
                    if (!map.get(out).contains(in)) {
                        map.get(out).add(in);
                        inDegree[in - 'a']++;
                    }
                    break;
                }
            }
        }

        // BFS
        StringBuilder sb = new StringBuilder();
        int totalChars = map.size();
        Queue<Character> queue = new LinkedList<>();

        for (char c : map.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                sb.append(c);
                queue.offer(c);
            }
        }

        while (!queue.isEmpty()) {
            char out = queue.poll();
            if (map.get(out) == null || map.get(out).size() == 0) {
                continue;
            }
            for (char in : map.get(out)) {
                inDegree[in - 'a']--;
                if (inDegree[in - 'a'] == 0) {
                    sb.append(in);
                    queue.offer(in);
                }
            }
        }
        return sb.length() == totalChars ? sb.toString() : "";
    }

    public static void main(String[] args) {
        String[] words = {"wrt",
                "wrf",
                "er",
                "ett",
                "rftt"};
        String[] words2 = {
                "z",
                "x"
        };
        System.out.println(alienOrder(words));
        System.out.println(alienOrder(words2));
    }
}
