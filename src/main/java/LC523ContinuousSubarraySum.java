import java.util.HashMap;
import java.util.Map;

public class LC523ContinuousSubarraySum {
    /**
     * Given a list of non-negative numbers and a target integer k,
     * write a function to check if the array has a continuous subarray of size at least 2
     * that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
     */

    /** Key point: if we can find any two subarray of prefix sum have same mod value, then their difference MUST be
     * divisible by k. So we can use a map to store mod value of each prefix sum in map, with its index. Then check
     * if map contains the same mod value with size > 2 when we have new mod value in every iteration
     *
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        // corner case: if the very first subarray with first two numbers in array could form the result, we need to
        // put mod value 0 and index -1 to make it as a true case
        map.put(0, -1);
        int curSum = 0;

        for (int i = 0; i < nums.length; i++) {
            curSum += nums[i];

            // corner case: k CANNOT be 0 when we use a number mod k
            if (k != 0) {
                curSum = curSum % k;
            }

            if (map.containsKey(curSum)) {  // map中key是preSum mod k后的值，value是index
                if (i - map.get(curSum) > 1) {  // 要求是size >= 2
                    return true;
                }
            } else {
                map.put(curSum, i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = {23, 2, 4, 6, 7};
        int k1 = 6;
        int[] nums2 = {23, 2, 6, 4, 7};
        int k2 = 6;

        System.out.println(checkSubarraySum(nums1, k1));
        System.out.println(checkSubarraySum(nums2, k2));
    }

}
