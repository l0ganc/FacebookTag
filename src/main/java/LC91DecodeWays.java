public class LC91DecodeWays {
    /**
     * A message containing letters from A-Z is being encoded to numbers using the following mapping:
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * Given a non-empty string containing only digits, determine the total number of ways to decode it.
     *
     * Example 1:
     *
     * Input: "12"
     * Output: 2
     * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
     */

    public static int numDecodings(String s) {
         /*
        dp，facebook常考题
        第一位只能是1跟2， 第二位最大是6
        time = O(n), space = O(n)
        */
         if (s == null || s.length() == 0) {
             return 0;
         }

         int[] dp = new int[s.length() + 1];  // dp[i]表示s[0] ~ s[i - 1]能够被decode的数目
         dp[0] = 1;
         dp[1] = s.charAt(0) == '0' ? 0 : 1;

         for (int i = 2; i <= s.length(); i++) {
             int oneDigit = Integer.valueOf(s.substring(i - 1, i));
             int secondDigit = Integer.valueOf(s.substring(i - 2, i));
             if (oneDigit >= 1 && oneDigit <= 9) {
                 dp[i] += dp[i - 1];
             }
             if (secondDigit >= 10 && secondDigit <= 26) {
                 dp[i] += dp[i - 2];
             }
         }
         return dp[s.length()];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("226"));
    }
}
