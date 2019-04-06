import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC621TaskScheduler {
    /**
     * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.
     *
     * You need to return the least number of intervals the CPU will take to finish all the given tasks.
     *
     *
     *
     * Example:
     *
     * Input: tasks = ["A","A","A","B","B","B"], n = 2
     * Output: 8
     * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
     */
    // LC621 原版题，执行顺序可以不固定
    public static int leastInterval(char[] tasks, int n) {
        // greedy method
        Map<Character, Integer> map = new HashMap<>();
        int mostFreq = 0;
        int sameFreqNum = 0;
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            mostFreq = Math.max(mostFreq, map.get(c));
        }

        for (char c : map.keySet()) {
            if (map.get(c) == mostFreq) {
                sameFreqNum++;
            }
        }

        return Math.max(tasks.length, (mostFreq - 1) * (n + 1) + sameFreqNum);
    }

    // 稍微优化一点的方法
    private static int leastInterval2(char[] tasks, int n) {
        int[] arr = new int[26];
        for (char c : tasks) {
            arr[c - 'A']++;
        }
        Arrays.sort(arr);

        int num = 0;
        int maxSamefreq = 0;

        for (int i = 0; i < arr.length; i++) {
            if (maxSamefreq == arr[i]) {
                num++;
            } else if (maxSamefreq < arr[i]) {
                maxSamefreq = arr[i];
                num = 1;
            }
        }

        int res = (maxSamefreq - 1) * (n + 1) + num;
        return Math.max(res, tasks.length);

    }

    public static void main(String[] args) {
        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(leastInterval(tasks, 2));
        System.out.println(leastInterval2(tasks, 2));
    }

}
