import java.util.*;

public class LC692TopKFrequentWords {
    /**
     * Example 1:
     * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     * Output: ["i", "love"]
     * Explanation: "i" and "love" are the two most frequent words.
     *     Note that "i" comes before "love" due to a lower alphabetical order.
     */

    // 这道题只能用最小堆来做，如果用了最大堆，那么当优先队列的size大于k的时候，执行的pop操作会把顶端元素（most frequent）pop出去，
    // 导致错误结果
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        List<String> res = new ArrayList<>();

        // 最小堆，如果a跟b的value相同，则按照key来降序排列； 否则按照a跟b的value值来升序排列
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            res.add(0, pq.poll().getKey());
        }

        return res;
    }
}
