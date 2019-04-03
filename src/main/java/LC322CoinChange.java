//You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
//
// Example 1:
//
//
//Input: coins = [1, 2, 5], amount = 11
//Output: 3
//Explanation: 11 = 5 + 5 + 1
//
// Example 2:
//
//
//Input: coins = [2], amount = 3
//Output: -1
//
//
// Note:
//You may assume that you have an infinite number of each kind of coin.
//

import java.util.Arrays;

public class LC322CoinChange {
    // dp top down, time = O(S * n), Space = O(S) where s is the amount
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        // dp[S]: Minimum number of coins needed to make change for amount S using denominations[c0, c1, c2,...cn]
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins1 = {1,2,5};
        System.out.println(coinChange(coins1, 11));
        System.out.println(coinChange(new int[]{2}, 3));
    }
}
