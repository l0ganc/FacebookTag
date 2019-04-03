import java.util.HashMap;
import java.util.Map;

//Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
//
// Note:
//The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
//
// Example 1:
//
//
//Input: nums = [1, -1, 5, -2, 3], k = 3
//Output: 4
//Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
//
//
// Example 2:
//
//
//Input: nums = [-2, -1, 2, 1], k = 1
//Output: 2
//Explanation: The subarray [-1, 2] sums to 1 and is the longest.
//
// Follow Up:
//Can you do it in O(n) time?
//
public class LC325MaximumSizeSubarraySumEqualsK {
    // 暴力解， time = O(n ^ 2), space = O(n)
    public static int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return 0;
        int res = Integer.MIN_VALUE;
        int[] sum = new int[n];
        sum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int cur = sum[j] - sum[i] + nums[i];
                if (cur == k) {
                    res = Math.max(res, j - i + 1);
                }
            }
        }

        return res == Integer.MIN_VALUE ? 0 : res;
    }

    // 类似于LC560的hashmap方法，time = O(n)
    public static int maxSubArrayLen2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res = Math.max(res, i - map.get(sum - k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res == Integer.MIN_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        int[] nums = {-2, -1, 2, 1};
        int k = 3;
        System.out.println(maxSubArrayLen(nums, k));
        System.out.println(maxSubArrayLen2(nums, k));
    }
}
