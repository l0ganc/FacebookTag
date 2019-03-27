public class LC300LongestIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     *
     * Example:
     *
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
     *
     */

    /**
     * We make use of a dpdp array to store the required data.
     * dp[i]dp[i] represents the length of the longest increasing subsequence possible
     *      considering the array elements upto the ith element
     *
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i  = 1; i < nums.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    curMax = Math.max(curMax, dp[j]);
                }
            }
            dp[i] = curMax + 1;
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS(nums));
    }
}
