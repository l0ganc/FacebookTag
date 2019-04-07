import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LC286WallsAndGates {
    /**
     * You are given a m x n 2D grid initialized with these three possible values.
     *
     * -1 - A wall or an obstacle.
     * 0 - A gate.
     * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
     * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
     *
     * Example:
     *
     * Given the 2D grid:
     *
     * INF  -1  0  INF
     * INF INF INF  -1
     * INF  -1 INF  -1
     *   0  -1 INF INF
     * After running your function, the 2D grid should be:
     *
     *   3  -1   0   1
     *   2   2   1  -1
     *   1  -1   2  -1
     *   0  -1   3   4
     */

    // 用DFS比较简洁简单，time = O(Gmn) where G is the number if gates, space = O(mn)
    // start from gates
    public static void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    // find a gate, need to do dfs
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private static void dfs(int[][] rooms, int i, int j, int distance) {
        if (i < 0 || i >= rooms.length || j < 0 || j >= rooms[0].length || rooms[i][j] < distance) {
            // 如果rooms[i][j]小于distance，说明要么碰到了gate要么碰到了obstacle(-1)
            return;
        }

        rooms[i][j] = distance;
        dfs(rooms, i + 1, j, distance + 1);
        dfs(rooms, i - 1, j, distance + 1);
        dfs(rooms, i, j + 1, distance + 1);
        dfs(rooms, i, j - 1, distance + 1);
    }

    // BFS 版本， time = O(mn); space = O(mn)
    public static void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();   // level traversal, make sure get the shortest distance
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                for (int[] dir : directions) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];
                    if (nextX < 0 || nextX >= rooms.length || nextY < 0 || nextY >= rooms[0].length) {
                        continue;
                    }

                    if (rooms[nextX][nextY] == Integer.MAX_VALUE) {
                        rooms[nextX][nextY] = rooms[x][y] + 1;
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        int[][] rooms = new int[][] {
                {Integer.MAX_VALUE, -1, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, -1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE, -1},
                {0, -1, Integer.MAX_VALUE, Integer.MAX_VALUE},
        };
        //wallsAndGates(rooms);
        wallsAndGates2(rooms);
        for (int i = 0; i < rooms.length; i++) {
            System.out.println(Arrays.toString(rooms[i]));
        }
    }

}
