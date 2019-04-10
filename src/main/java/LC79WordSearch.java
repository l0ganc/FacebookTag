public class LC79WordSearch {
    /**
     * board =
     * [
     *   ['A','B','C','E'],
     *   ['S','F','C','S'],
     *   ['A','D','E','E']
     * ]
     *
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     */

    //I think the time complexity is O(n*4^k), n is the size of the board and k is the length of the word.
    // Because in worst case, for each element in the board,
    //          search() will be recursively called 4 times until it reaches the length of the word.
    // And the space complexity is O(n + k). If k is relatively much smaller than n and can be considered as a constant

    // DFS 常规题
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (DFS(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean DFS(char[][] board, int i, int j, int start, String word) {
        if (start >= word.length()) {
            return true;
        }

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
            return false;
        }

        if (board[i][j] == word.charAt(start)) {
            char c = board[i][j];
            board[i][j] = '*';
            boolean res = DFS(board, i + 1, j, start + 1, word) ||
                    DFS(board, i - 1, j, start + 1, word) ||
                    DFS(board, i, j + 1, start + 1, word) ||
                    DFS(board, i, j - 1 , start + 1, word);
            board[i][j] = c;
            return res;
        }
        return false;
    }

    public static void main(String[] args) {
        LC79WordSearch obj = new LC79WordSearch();
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(obj.exist(board, "ABCCED"));
        System.out.println(obj.exist(board, "SEE"));
        System.out.println(obj.exist(board, "ABCB"));
    }
}
