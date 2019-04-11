import java.util.*;

public class LC210CourseScheduleII {
    /**
     * There are a total of n courses you have to take, labeled from 0 to n-1.
     *
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
     *
     * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
     *
     * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
     *
     * Example 1:
     *
     * Input: 2, [[1,0]]
     * Output: [0,1]
     * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
     *              course 0. So the correct course order is [0,1] .
     */

    // time = O(E + V)
    // space = O(E + V)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];

        // 建图
        for (int i = 0; i < prerequisites.length; i++) {
            int out = prerequisites[i][1];
            int in = prerequisites[i][0];
            if (!map.containsKey(out)) {
                map.put(out, new ArrayList<>());
            }
            map.get(out).add(in);
            inDegree[in]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int validIndex = 0;
        int count = 0;
        for (int num : map.keySet()) {
            if (inDegree[num] == 0) {
                queue.add(num);
                res[validIndex++] = num;
                count++;
            }
        }

        while (!queue.isEmpty()) {
            int out = queue.poll();
            if (map.get(out) == null || map.get(out).size() == 0) continue;
            for (int in : map.get(out)) {
                if (--inDegree[in] == 0) {
                    res[validIndex++] = in;
                    queue.add(in);
                    count++;
                }
            }
        }
        return count == numCourses ? res : new int[]{};
    }

    public static void main(String[] args) {
        LC210CourseScheduleII obj = new LC210CourseScheduleII();
        int[][] prerequisites = new int[][] {
                {1, 0}
        };
        int[][] prerequisites2 = new int[][] {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };
        System.out.println(Arrays.toString(obj.findOrder(2, prerequisites)));
        System.out.println(Arrays.toString(obj.findOrder(4, prerequisites2)));
    }
}
