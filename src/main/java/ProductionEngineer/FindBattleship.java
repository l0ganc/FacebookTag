package ProductionEngineer;

import java.util.Arrays;

public class FindBattleship {
    /**
     * 一个N*N的grid, 里面battleship是一个横着或者竖着的一条线（三个格子）， 要找到battleship的坐标。
     */

    public static int[][] getBattleship(int[][] grid) {
        int[][] res = new int[3][2];
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return res;
        }

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (i > 0 && i + 1 < m && grid[i - 1][j] == 1 && grid[i + 1][j] == 1) {
                        // find one
                        res[0] = new int[]{i - 1, j};
                        res[1] = new int[]{i, j};
                        res[2] = new int[]{i + 1, j};
                        return res;
                    }

                    if (j > 0 && j + 1 < n && grid[i][j - 1] == 1 && grid[i][j + 1] == 1) {
                        // find one
                        res[0] = new int[]{i, j - 1};
                        res[1] = new int[]{i, j};
                        res[2] = new int[]{i, j + 1};
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 1, 0, 0},
                {0, 1, 1, 0, 1},
        };

        int[][] res = getBattleship(grid);
        for (int[] d : res) {
            System.out.println(Arrays.toString(d));
        }
    }
}
