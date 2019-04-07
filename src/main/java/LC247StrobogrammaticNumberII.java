import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC247StrobogrammaticNumberII {
    /**
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
     *
     * Find all strobogrammatic numbers that are of length = n.
     *
     * Example:
     *
     * Input:  n = 2
     * Output: ["11","69","88","96"]
     */

    // backtracking做法注意边界条件，time = O(5^(n/2)), space = O(5^(n/2)

    /**
     * Time complexity O(5 ^ (n / 2)), space complexity O(5 ^ (n / 2)).
     * n / 2 indices for choice, each index has 5 choices.
     * Not counting in the time cost for string connecting. Using char[] to record num during dfs saves this cost.
     * Space complexity means the number of resulting num.
     */
    public static List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    private static List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<>(Arrays.asList(""));
        if (n == 1) return new ArrayList<>(Arrays.asList("0", "1","8"));

        List<String> list = helper(n - 2, m);
        List<String> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            /**
             * n - 2 is the new size of the strings that you need to find. Initially it was n,
             * then you find results for n - 2 and add two more characters one at the starting and one at the end of the strings of size n - 2 so total size of the strings in the result will be n.
             * m is just the given value of n. You can't add 0 at the starting or ending of the number that's why add 0 only if n != m
             * i.e. you will later append more characters to this string
             */
            if (n != m) res.add("0" + s + "0");

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("9" + s + "6");
            res.add("8" + s + "8");
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findStrobogrammatic(5));
    }
}
