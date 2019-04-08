import java.util.*;

public class LC341TopKFrequentElements {
    /**
     * Given a non-empty array of integers, return the k most frequent elements.
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,2,2,3], k = 2
     * Output: [1,2]
     * Example 2:
     *
     * Input: nums = [1], k = 1
     * Output: [1]
     */


    // min-heap, time = O(Nlogk), space = O(N)
    // 存hashmap时候遍历了数组，O(N), 然后建min-heap跟pop操作话费O(Nlogk)，总体就是O(Nlogk)
    public static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // min-heap
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for (int num : map.keySet()) {
            pq.add(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }

        Collections.reverse(res);
        return res;
    }


    // Bucket sort, time = O(n), space = O(n)
    public static List<Integer> topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];

        for (int key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null) {
                res.addAll(bucket[i]);
            }
        }
        return res;

    }

    public static void main(String[] args) {
        int[] nums1 = {1,1,1,2,2,3};
        int[] nums2 = {1};
        System.out.println(topKFrequent(nums1, 2));
        System.out.println(topKFrequent(nums2, 1));
        System.out.println(topKFrequent2(nums1, 2));
        System.out.println(topKFrequent2(nums2, 1));
    }
}
