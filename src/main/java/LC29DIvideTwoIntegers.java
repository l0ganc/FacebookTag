public class LC29DIvideTwoIntegers {
    /**
     * Input: dividend = 10, divisor = 3
     * Output: 3
     * 用篮子王的方法，一步步左移相除
     */


    // LC29: 不给用 *, /, mod，O(n/d)
    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            } else if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
        }

        long divd = (long) dividend;
        long divs = (long) divisor;
        int sign = 1;

        // 把符号位单拿出来，保证后面的while循环都是对正数进行操作
        if (divd < 0) {
            divd = -divd;
            sign = -sign;
        }
        if (divs < 0) {
            divs = -divs;
            sign = -sign;
        }
        int res = 0;
        while (divd >= divs) {
            int shift = 0;
            while (divd >= (divs << shift)) {
                shift++;
            }
            divd -= (divs << (shift - 1));
            res += (1 << (shift - 1));
        }
        System.out.println("余数为： " + divd);

        return sign * res;
    }


    // O(logn) facebook 面经题是可以用*，需要用二分来优化时间复杂度，并且d/n, n是保证在[1,d]之间,少了些corner case
    public static int divide2(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            } else if (divisor == 1) {
                return Integer.MIN_VALUE;
            }
        }

        long divd = (long) dividend;
        long divs = (long) divisor;
        int sign = 1;

        // 把符号位单拿出来，保证后面的while循环都是对正数进行操作
        if (divd < 0) {
            divd = -divd;
            sign = -sign;
        }
        if (divs < 0) {
            divs = -divs;
            sign = -sign;
        }

        long l = 0, r = divd;
        while (l + 1 < r) {
            long mid = (r - l) / 2 + l;
            if (mid * divs == divd) {
                return sign * (int)mid;
            } else if (mid * divs < divd) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return sign * (int)l;
    }

    public static void main(String[] args) {
        System.out.println(divide(32, 3));
        System.out.println("*************************************");
        System.out.println(divide2(32, 3));
    }
}
