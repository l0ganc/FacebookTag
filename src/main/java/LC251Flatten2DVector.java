public class LC251Flatten2DVector {
    /**
     * Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.
     *
     *
     *
     * Example:
     *
     * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
     *
     * iterator.next(); // return 1
     * iterator.next(); // return 2
     * iterator.next(); // return 3
     * iterator.hasNext(); // return true
     * iterator.hasNext(); // return true
     * iterator.next(); // return 4
     * iterator.hasNext(); // return false
     */
    static int[][] data;
    int rows;
    int row, col;

    public LC251Flatten2DVector (int[][] v) {
        data = v;
        rows = v.length;
        row = 0;
        col = 0;
    }

    public int next() {
        if (hasNext()) {
            int next = data[row][col++];
            return next;
        }
        throw new UnsupportedOperationException();
    }

    public boolean hasNext() {
        while (row < rows) {
            if (col < data[row].length) {
                return true;
            }
            row++;
            col = 0;
        }
        return false;
    }
}
