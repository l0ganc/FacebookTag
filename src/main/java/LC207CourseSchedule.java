import java.util.*;

public class LC207CourseSchedule {
    /**
     * Course Schedule
     *
     * There are a total of n courses you have to take, labeled from 0 to n-1.
     *
     * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
     *
     * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
     *
     * Example 1:
     *
     * Input: 2, [[1,0]]
     * Output: true
     * Explanation: There are a total of 2 courses to take.
     *              To take course 1 you should have finished course 0. So it is possible.
     */

    // time = O(E + V)
    // space = O(E + V)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // key是先上的课，value是先上这么课后可以上的其他的课
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inDegree = new int[numCourses];  // 入度

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
        for (int num : map.keySet()) {
            if (inDegree[num] == 0) {
                queue.offer(num);
            }
        }

        while (!queue.isEmpty()) {
            int out = queue.poll();
            if (map.get(out) == null || map.get(out).size() == 0) continue;
            for (int in : map.get(out)) {
                if (--inDegree[in] == 0) {
                    queue.offer(in);
                }
            }
        }

        for (int num : inDegree) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LC207CourseSchedule obj = new LC207CourseSchedule();
        int[][] prerequisites = new int[][] {
                {0, 1}
        };
        int[][] prerequisites2 = new int[][] {
                {1, 0},
                {0, 1}
        };
        System.out.println(obj.canFinish(2, prerequisites));
        System.out.println(obj.canFinish(2, prerequisites2));
    }
}
