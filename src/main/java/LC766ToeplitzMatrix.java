public class LC766ToeplitzMatrix {
    /**
     * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.
     *
     * Now given an M x N matrix, return True if and only if the matrix is Toeplitz.
     *
     *
     * Example 1:
     *
     * Input:
     * matrix = [
     *   [1,2,3,4],
     *   [5,1,2,3],
     *   [9,5,1,2]
     * ]
     * Output: True
     * Explanation:
     * In the above grid, the diagonals are:
     * "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
     * In each diagonal all elements are the same, so the answer is True.
     */

    public boolean isToeplitzMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix[i].length - 1; j++) {   // 这里的j范围要留意
                if (matrix[i][j] != matrix[i + 1][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LC766ToeplitzMatrix obj = new LC766ToeplitzMatrix();
        int[][] matrix = new int[][]{
                {1,2,3,4},
                {5,1,2,3},
                {9,5,1,2}
        };
        System.out.println(obj.isToeplitzMatrix(matrix));
    }
}
