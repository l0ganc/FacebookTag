package ProductionEngineer;

public class BestTimetoBuyandSellStock {
    // LC121 Best Time to Buy and Sell Stock  time = O(n), space = O(1)
    /**
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     */

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int res = 0;
        int min = prices[0];

        for (int price : prices) {
            min = Math.min(min, price);
            res = Math.max(res, price - min);
        }
        return res;
    }

    // follow up 1: LC122 Best Time to Buy and Sell Stock II
    /**
     * 可以多次买卖，但是必须卖了以后才能买 time = O(n), space = O(1)
     */
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    // follow up 2: LC123 Best Time to Buy and Sell Stock III
    public int maxProfit3(int[] prices) {
        /**
         * https://discuss.leetcode.com/topic/32288/2ms-java-dp-solution/12
         * 初始利润为0
         * 买股票操作相当于，将利润减去买股票的价格
         * <p>
         * time: O(n)
         * space: O(1)
         */
        // 第一次购买的价格
        int buy1 = Integer.MAX_VALUE;

        // 在每次买/卖之后的利润
        int afterSell1 = 0;
        int afterBuy2 = Integer.MIN_VALUE;
        int afterSell2 = 0;

        for (int curPrice : prices) {
            // 第一次买入的价格越小越好，才能保证利润最大
            buy1 = Math.min(buy1, curPrice);

            // 第一次卖出后获得的利润，越大越好
            afterSell1 = Math.max(afterSell1, curPrice - buy1);

            // 用第一次交易的利润做第二次买入后获得的利润，越大越好
            afterBuy2 = Math.max(afterBuy2, afterSell1 - curPrice);

            // 第二次卖出后获得的利润，越大越好
            afterSell2 = Math.max(afterSell2, afterBuy2 + curPrice);
        }

        return afterSell2;   // 最终获得的最大利润
    }


    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        int[] prices1 = {7,6,5,4,3,2,1};
        int[] prices2 = {1,2,3,4,5};
        System.out.println(maxProfit(prices));
        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit2(prices));
        System.out.println(maxProfit2(prices1));
        System.out.println(maxProfit2(prices2));
    }
}
