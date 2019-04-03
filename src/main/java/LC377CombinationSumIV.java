//Given an integer array with all positive numbers and no duplicates, find the number of possible combinations that add up to a positive integer target.
//
// Example:
//
//
//nums = [1, 2, 3]
//target = 4
//
//The possible combination ways are:
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//
//Note that different sequences are counted as different combinations.
//
//Therefore the output is 7.
//
//
//
//
// Follow up:
//What if negative numbers are allowed in the given array?
//How does it change the problem?
//What limitation we need to add to the question to allow negative numbers?
//
// Credits:
//Special thanks to @pbrother for adding this problem and creating all test cases.
//

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC377CombinationSumIV {
    // recursion way， 超时, time = O(n ^ target), space = O(1)
    public static int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4(nums, target - nums[i]);
            }
        }
        return res;
    }


    // dp way
    static int[] dp;
    public static int combinationSum4_M2(int[] nums, int target) {
        dp = new int[target + 1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        return helper(nums, target);
    }

    private static int helper(int[] nums, int target) {
        if (dp[target] != -1) {
            return dp[target];
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += helper(nums, target - nums[i]);
            }
        }
        dp[target] = res;
        return res;
    }


    // hashmap
    static Map<Integer, Integer> map = new HashMap<>();
    public static int combinationSum4_M3(int[] nums, int target) {
        int res = 0;
        if (nums == null || nums.length == 0 || target < 0) {
            return 0;
        }
        if (target == 0) return 1;
        if (map.containsKey(target)) {
            return map.get(target);
        }

        for (int i = 0; i < nums.length; i++) {
            if (target >= nums[i]) {
                res += combinationSum4_M3(nums, target - nums[i]);
            }
        }
        map.put(target, res);
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));
        System.out.println(combinationSum4_M2(nums, target));
        System.out.println(combinationSum4_M3(nums, target));
    }
}

