//Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
//
//
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
//
//
// The matching should cover the entire input string (not partial).
//
// Note:
//
//
// s could be empty and contains only lowercase letters a-z.
// p could be empty and contains only lowercase letters a-z, and characters like ? or *.
//
//
// Example 1:
//
//
//Input:
//s = "aa"
//p = "a"
//Output: false
//Explanation: "a" does not match the entire string "aa".
//
//
// Example 2:
//
//
//Input:
//s = "aa"
//p = "*"
//Output: true
//Explanation: '*' matches any sequence.
//
//
// Example 3:
//
//
//Input:
//s = "cb"
//p = "?a"
//Output: false
//Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
//
//
// Example 4:
//
//
//Input:
//s = "adceb"
//p = "*a*b"
//Output: true
//Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
//
//
// Example 5:
//
//
//Input:
//s = "acdcb"
//p = "a*c?b"
//Output: false
//
public class LC44WildcardMatching {
    // dp 经典问题，类型与leetcode 10
    /**
     * T[i][j]表示s从0~ith 跟p从0~jth能不能匹配成功
     *
     * T[i][j] = T[i-1][j-1]  if s[i] == p[j] || p[j] == '?'
     *         = T[i-1][j] || T[i][j-1]  if p[j] == '*'
     *         = False
     *
     * time = O(m * n) ， Space = O(m * n) can be reduced to O(n)
     */
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // 初始化第0行，若p中有*，那么是可能匹配到空的s成功的
        // 不需要初始化第0列，因为当p为空， 那么dp[i][0]肯定是false， 而boolean数组默认就是false
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("adceb", "*a*b"));
        System.out.println(isMatch("acdcb", "a*c?b"));
    }
}
