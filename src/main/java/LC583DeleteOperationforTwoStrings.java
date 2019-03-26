public class LC583DeleteOperationforTwoStrings {
    /**
     * Delete Operation for Two Strings
     * Input: "sea", "eat"
     * Output: 2
     * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
     *
     * dp算出longest common Subsequence 然后用两个字符串的长度减去2倍的LCS
     */

    // time = O(m * n);  space = O(m * n)
    public static int minDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return s1.length() + s2.length() - 2 * dp[s1.length()][s2.length()];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
    }

    /**
     *  s1 = a a b c d
     *  s2 = a c b b d
     *
     * dp:
     *       a a b c d
     *     0 0 0 0 0 0
     *   a 0 1 1 1 1 1
     *   c 0 1 1 1 2 2
     *   b 0 1 1 2 2 2
     *   b 0 1 1 2 2 2
     *   d 0 1 1 2 2 3
     *
     */
}
