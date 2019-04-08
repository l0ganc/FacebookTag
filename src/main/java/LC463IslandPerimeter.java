public class LC463IslandPerimeter {
    /**
     * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.
     *
     * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
     *
     * The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
     *
     *
     *
     * Example:
     *
     * Input:
     * [[0,1,0,0],
     *  [1,1,1,0],
     *  [0,1,0,0],
     *  [1,1,0,0]]
     *
     * Output: 16
     */
    // 计算总共有多少个island，同时统计每个island上下左右的连接island数，最后用islandnum * 4 - connectino
    // time = O(mn), space = O(1)
    public static int islandPerimeter(int[][] grid) {
        int area = 0;
        int m = grid.length;
        int n = grid[0].length;
        int connection = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    area++;
                    if (i > 0 && grid[i - 1][j] == 1) connection++;   // 上面的邻居
                    if (i + 1 < m && grid[i + 1][j] == 1) connection++;  // 下面的邻居
                    if (j > 0 && grid[i][j - 1] == 1) connection++;   // 左边的邻居
                    if (j + 1 < n && grid[i][j + 1] == 1) connection++;  // 右边的邻居
                }
            }
        }

        return area * 4 - connection;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        System.out.println(islandPerimeter(grid));
    }

}
