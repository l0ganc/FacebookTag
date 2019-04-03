//Given an array of n positive integers and a positive integer s, find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
//
// Example:
//
//
//Input: s = 7, nums = [2,3,1,2,4,3]
//Output: 2
//Explanation: the subarray [4,3] has the minimal length under the problem constraint.
//
// Follow up:
//
// If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
//
public class LC209MinimumSizeSubarraySum {
    // time = O(n ^ 2), Space = O(n), better brute force
    public static int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int res = Integer.MAX_VALUE;
        int[] sum = new int[n];
        sum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int cur = sum[j] - sum[i] + nums[i];
                if (cur >= s) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    // method2: two pointers; time = O(n), space = O(1)
    public static int minSubArrayLen2(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int start = 0;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (start <= i && sum >= s) {
                res = Math.min(res, i - start + 1);
                sum -= nums[start];
                start++;
            }
        }

        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int s = 7;
        System.out.println(minSubArrayLen(s, nums));
        System.out.println(minSubArrayLen2(s, nums));
    }
}
