public class LC674LongestContinuousIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
     *
     * Example 1:
     * Input: [1,3,5,4,7]
     * Output: 3
     * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
     * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
     *
     * Input: [2,2,2,2,2]
     * Output: 1
     * Explanation: The longest continuous increasing subsequence is [2], its length is 1.
     *
     */

    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                count++;
                res = Math.max(res, count);
            } else {
                count = 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,5,4,7};
        int[] nums2 = {2,2,2,2,2};
        System.out.println(findLengthOfLCIS(nums1));
        System.out.println(findLengthOfLCIS(nums2));
    }
}
