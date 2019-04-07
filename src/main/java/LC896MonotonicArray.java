public class LC896MonotonicArray {
    /**
     * An array is monotonic if it is either monotone increasing or monotone decreasing.
     *
     * An array A is monotone increasing if for all i <= j, A[i] <= A[j].  An array A is monotone decreasing if for all i <= j, A[i] >= A[j].
     *
     * Return true if and only if the given array A is monotonic.
     *
     *
     *
     * Example 1:
     *
     * Input: [1,2,2,3]
     * Output: true
     * Example 2:
     *
     * Input: [6,5,4,4]
     * Output: true
     */

    public static boolean isMonotonic(int[] A) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i <= A.length - 2; i++) {
            if (A[i] > A[i + 1]) {
                isIncreasing = false;
            }
            if (A[i] < A[i + 1]) {
                isDecreasing = false;
            }
        }

        return isDecreasing || isIncreasing;
    }

    public static void main(String[] args) {
        int[] A = {1,2,2,3};
        int[] A2 = {-1,0,0,0,3};
        int[] A3 = {6,5,4,4};
        System.out.println(isMonotonic(A));
        System.out.println(isMonotonic(A2));
        System.out.println(isMonotonic(A3));
    }
}
