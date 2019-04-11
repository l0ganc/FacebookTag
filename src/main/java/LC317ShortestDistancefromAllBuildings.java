import java.util.LinkedList;
import java.util.Queue;

public class LC317ShortestDistancefromAllBuildings {
    /**
     * You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:
     *
     * Each 0 marks an empty land which you can pass by freely.
     * Each 1 marks a building which you cannot pass through.
     * Each 2 marks an obstacle which you cannot pass through.
     * Example:
     *
     * Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
     *
     * 1 - 0 - 2 - 0 - 1
     * |   |   |   |   |
     * 0 - 0 - 0 - 0 - 0
     * |   |   |   |   |
     * 0 - 0 - 1 - 0 - 0
     *
     * Output: 7
     *
     * Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
     *              the point (1,2) is an ideal empty land to build a house, as the total
     *              travel distance of 3+3+1=7 is minimal. So return 7.
     * Note:
     * There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
     */


    // 遍历grid，对每一个1做bfs，更新canReach数组跟distance数组，最后遍历结果矩阵来得到结果
    // canReach[i][j] : 表示从每个(i,j)为0的位置一共能访问到多少个1(building)
    // distance[i][j] : 表示从每个(i,j)为0的位置到所有1的距离之和
    // buildingNum表示1的个数
    // time = O(m^2*n^2), space = O(m*n)
    int[] rowsDir = {0, 0, 1, -1};
    int[] colsDir = {1, -1, 0, 0};
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int buildingNum = 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] canReach = new int[m][n];
        int[][] distance = new int[m][n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    buildingNum++;
                    dfs(grid, i, j, canReach, distance);
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canReach[i][j] == buildingNum && distance[i][j] < res) {
                    res = distance[i][j];
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    public void dfs(int[][] grid, int x, int y, int[][] canReach, int[][] distance) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int d = 0;  // current distance
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            d++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int nextX = cur[0] + rowsDir[k];
                    int nextY = cur[1] + colsDir[k];
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY] || grid[nextX][nextY] != 0) {
                        continue;    // 不满足条件，跳过这个坐标
                    }

                    // (nextX, nextY)是符合条件的下一个点，此时更新queue, canReach, distance, visited
                    queue.offer(new int[]{nextX, nextY});
                    distance[nextX][nextY] += d;
                    canReach[nextX][nextY]++;
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        LC317ShortestDistancefromAllBuildings obj = new LC317ShortestDistancefromAllBuildings();
        int[][] grid = new int[][] {
                {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        System.out.println(obj.shortestDistance(grid));
    }
}
