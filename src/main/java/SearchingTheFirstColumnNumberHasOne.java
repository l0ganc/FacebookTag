public class SearchingTheFirstColumnNumberHasOne {
    /**
     * 2. Given a 2D array. Each row is constructed by 0's at the beginning and 1's at the following.
     * ex: [
     *        [0,0,0,0,0,1,1]. check 1point3acres for more.
     *        [0,0,0,1,1,1,1]
     *        [0,0,0,0,1,1,1]
     *       ]
     * Return the first column number that has 1 occurs. In the example it should be 3
     *
     * 最优法复杂度O(m + n), m是行，n是列
     * 从右上角开始(i,j)，如果当前元素是1并且(i,j-1)也是1，那么就左移走到(i,j-1);
     *      否则，往下走到(i+1,j)
     * 重复这个操作，直到i==m
     */

    public static int getFirstColumnHasOne(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }

        int m = grid.length - 1;
        int n = grid[0].length - 1;
        int i = 0, j = n;

        while (true) {
            if (grid[i][j] == 1 && j - 1 >= 0 && grid[i][j - 1] == 1) {
                j--;
            } else {
                if (i == m) {
                    break;
                }
                i++;
            }
        }

        if (j == n) return -1;
        return j;
    }


    // 二分法，对每行做一次，时间复杂度是O(mlogn), 其中m是行，n是列
    public static int getFirstColumnHashOne2(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return -1;
        }

        int m = grid.length;
        int n = grid[0].length;
        int left = 0;
        int right = n - 1;
        for (int i = 0; i < m; i++) {
            // do binary search and update the right bound
            while (left < right) {
                int mid  = (right - left) / 2 + left;
                if (grid[i][mid] == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            right = left;
            left = 0;
        }
        return right;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                {0,0,0,0,1,1,1},
                {0,0,0,0,1,1,1},
                {0,0,1,1,1,1,1},
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,1,1},
        };
        System.out.println(getFirstColumnHasOne(grid));
        System.out.println(getFirstColumnHashOne2(grid));
    }
}
