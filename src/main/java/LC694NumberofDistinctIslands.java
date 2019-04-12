import java.util.HashSet;
import java.util.Set;

public class LC694NumberofDistinctIslands {
    /**
     * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
     *
     * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
     *
     * Example 1:
     * 11000
     * 11000
     * 00011
     * 00011
     * Given the above grid map, return 1.
     * Example 2:
     * 11011
     * 10000
     * 00001
     * 11011
     * Given the above grid map, return 3.
     *
     * Notice that:
     * 11
     * 1
     * and
     *  1
     * 11
     * are considered different island shapes, because we do not consider reflection / rotation.
     */

    int[] rowsDir = {0, 0, 1, -1};
    int[] colsDir = {1, -1, 0, 0};

    public int numDistinctIslands(int[][] grid) {
        Set<String> res = new HashSet<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, 0, 0, sb);
                    String s = sb.toString();
                    if (!res.contains(s)) {
                        res.add(s);
                    }
                }
            }
        }
        return res.size();
    }

    public void dfs(int[][] grid, int x, int y, int xpos, int ypos, StringBuilder sb) {
        grid[x][y] = 0;
        sb.append(xpos + "" + ypos);

        for (int k = 0; k < 4; k++) {
            int nextX = x + rowsDir[k];
            int nextY = y + colsDir[k];

            if (nextX < 0 || nextX >= grid.length || nextY < 0 || nextY >= grid[0].length || grid[nextX][nextY] == 0) continue;

            dfs(grid, nextX, nextY, xpos + rowsDir[k], ypos + colsDir[k], sb);
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {1,1,0,1,1},
                {1,0,0,0,0},
                {0,0,0,0,1},
                {1,1,0,1,1}
        };
        LC694NumberofDistinctIslands obj = new LC694NumberofDistinctIslands();
        System.out.println(obj.numDistinctIslands(grid));
    }


}
