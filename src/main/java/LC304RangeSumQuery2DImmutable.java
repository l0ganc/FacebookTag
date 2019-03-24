public class LC304RangeSumQuery2DImmutable {
    /**
     * Given matrix = [
     *   [3, 0, 1, 4, 2],
     *   [5, 6, 3, 2, 1],
     *   [1, 2, 0, 1, 5],
     *   [4, 1, 0, 1, 7],
     *   [1, 0, 3, 0, 5]
     * ]
     *
     * sumRegion(2, 1, 4, 3) -> 8
     * sumRegion(1, 1, 2, 2) -> 11
     * sumRegion(1, 2, 2, 4) -> 12
     *
     * 思路：dp，前缀和相减
     */

    static int[][] dp;
    public LC304RangeSumQuery2DImmutable(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        // dp[i][j]是从matrix[0][0]一直加到matrix[i - 1][j - 1]这范围内所有元素的和
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                dp[i + 1][j + 1] = dp[i + 1][j] + dp[i][j + 1] + matrix[i][j] - dp[i][j];
            }
        }
    }

    public  int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        LC304RangeSumQuery2DImmutable demo = new LC304RangeSumQuery2DImmutable(matrix);

        System.out.println(demo.sumRegion(2, 1,4,3));
        System.out.println(demo.sumRegion(1, 1,2,2));
        System.out.println(demo.sumRegion(1, 2,2,4));
    }
}
