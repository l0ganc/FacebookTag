public class LC50Pow_x_n {
    /**
     *
     Implement pow(x, n), which calculates x raised to the power n (xn).

     Example 1:

     Input: 2.00000, 10
     Output: 1024.00000
     Example 2:

     Input: 2.10000, 3
     Output: 9.26100
     Example 3:

     Input: 2.00000, -2
     Output: 0.25000
     Explanation: 2-2 = 1/22 = 1/4 = 0.25
     */

    //  recursive way, time = O(logn)

    /**
     * For each computation, we need to store the result of x ^ {n / 2}
     *  . We need to do the computation for O(logn) times, so the space complexity is O(logn).
     */
    public static double myPow(double x, int n) {
        if (n > 0) {
            return pow(x, n);
        }
        return 1.0 / pow(x, n);
    }
    private static double pow (double x, int n) {
        if (n == 0) return 1;

        double y = pow(x, n / 2);
        if (n % 2 == 0) {
            return y * y;
        }

        return y * y * x;
    }


    // iterative way, time = O(logn), space = O(1)
    public static double myPow2(double x, int n) {
        long N = n;
        if (N < 0) {
            N = -N;
            x = 1 / x;
        }

        double res = 1;
        double currentProduct = x;

        for (long i = N; i > 0 ; i /= 2) {
            if (i % 2 == 1) {
                res *= currentProduct;
            }
            currentProduct = currentProduct * currentProduct;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(myPow(3, 10));
        System.out.println(myPow2(2, -10));
    }
}
