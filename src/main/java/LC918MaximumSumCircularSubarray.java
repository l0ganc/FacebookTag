public class LC918MaximumSumCircularSubarray {
    /**
     * 循环数组求最大subarray的和
     * Example 1:
     *
     * Input: [1,-2,3,-2]
     * Output: 3
     * Explanation: Subarray [3] has maximum sum 3
     * Example 2:
     *
     * Input: [5,-3,5]
     * Output: 10
     * Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10
     * Example 3:
     *
     * Input: [3,-1,2,-1]
     * Output: 4
     * Explanation: Subarray [2,-1,3] has maximum sum 2 + (-1) + 3 = 4
     * Example 4:
     *
     * Input: [3,-2,2,-3]
     * Output: 3
     * Explanation: Subarray [3] and [3,-2,2] both have maximum sum 3
     * Example 5:
     *
     * Input: [-2,-3,-1]
     * Output: -1
     * Explanation: Subarray [-1] has maximum sum -1
     */

    /**
     * So there are two case.
     *
     * The first is that the subarray take only a middle part, and we know how to find the max subarray sum.
     * The second is that the subarray take a part of head array and a part of tail array.
     * We can transfer this case to the first one.
     * The maximum result equals to the total sum minus the minimum subarray sum.

     So the max subarray circular sum equals to
     max(the max subarray sum, the total sum - the min subarray sum)


     Corner case
     Just one to pay attention:
     If all numbers are negative, maxSum = max(A) and minSum = sum(A).
     In this case, max(maxSum, total - minSum) = 0, which means the sum of an empty subarray.
     According to the deacription, We need to return the max(A), instead of sum of am empty subarray.
     So we return the maxSum to handle this corner case.


     Complexity
     One pass, time O(N)
     No extra space, space O(1)
     */
    public int maxSubarraySumCircular(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int total = 0;
        int maxSum = Integer.MIN_VALUE;
        int minSum = Integer.MAX_VALUE;
        int curMax = 0;
        int curMin = 0;

        for (int a : A) {
            total += a;
            curMax = Math.max(curMax + a, a);
            curMin = Math.min(curMin + a, a);
            maxSum = Math.max(maxSum, curMax);
            minSum = Math.min(minSum, curMin);
        }

        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    public static void main(String[] args) {
        LC918MaximumSumCircularSubarray obj = new LC918MaximumSumCircularSubarray();

        int[] nums1 = {1,-2,3,-2};
        System.out.println(obj.maxSubarraySumCircular(nums1));

        int[] nums2 = {5,-3,5};
        System.out.println(obj.maxSubarraySumCircular(nums2));

        int[] nums3 = {3,-1,2,-1};
        System.out.println(obj.maxSubarraySumCircular(nums3));

        int[] nums4 = {3,-2,2,-3};
        System.out.println(obj.maxSubarraySumCircular(nums4));

        int[] nums5 = {-2,-3,-1};
        System.out.println(obj.maxSubarraySumCircular(nums5));
    }
}
