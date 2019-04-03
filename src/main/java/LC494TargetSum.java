//
//You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
//
//
// Find out how many ways to assign symbols to make sum of integers equal to target S.
//
//
// Example 1:
//
//Input: nums is [1, 1, 1, 1, 1], S is 3.
//Output: 5
//Explanation:
//
//-1+1+1+1+1 = 3
//+1-1+1+1+1 = 3
//+1+1-1+1+1 = 3
//+1+1+1-1+1 = 3
//+1+1+1+1-1 = 3
//
//There are 5 ways to assign symbols to make the sum of nums be target 3.
//
//
//
// Note:
//
// The length of the given array is positive and will not exceed 20.
// The sum of elements in the given array will not exceed 1000.
// Your output answer is guaranteed to be fitted in a 32-bit integer.
//
//

public class LC494TargetSum {

    // method1: time = O(2^n), time = O(n)
    static int res = 0;
    public static int findTargetSumWays(int[] nums, int S) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        dfs(nums, S, 0, 0);
        return res;
    }

    private static void dfs(int[] nums, int target, int curSum, int index) {
        if (index == nums.length) {
            if (curSum == target) {
                res++;
            }
            return;
        }

        dfs(nums, target, curSum + nums[index], index + 1);
        dfs(nums, target, curSum - nums[index], index + 1);
    }


    // method2: dp solution, time = O(n * sum), space = O(n * sum)
    public static int findTargetSumWays2(int[] nums, int S) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < S || S < -sum) return 0;
        int offset = sum;
        int[][] ways = new int[n + 1][2 * sum + 1];
        ways[0][offset] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = nums[i]; j < 2 * sum + 1 - nums[i]; j++) {
                if (ways[i][j] != 0) {
                    ways[i + 1][j + nums[i]] += ways[i][j];
                    ways[i + 1][j - nums[i]] += ways[i][j];
                }
            }
        }
        return ways[n][S + offset];

    }
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1};
        int S = 3;
        System.out.println(findTargetSumWays(nums, 3));
        System.out.println(findTargetSumWays2(nums, 3));
    }
}
