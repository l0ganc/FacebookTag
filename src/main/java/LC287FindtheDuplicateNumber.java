public class LC287FindtheDuplicateNumber {
    /**
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
     *
     * Example 1:
     *
     * Input: [1,3,4,2,2]
     * Output: 2
     * Example 2:
     *
     * Input: [3,1,3,4,2]
     * Output: 3
     */
    public int findDuplicate(int[] nums) {
        int l = 1;
        int r = nums.length - 1;

        while (l + 1 < r) {
            int mid = (r - l) / 2 + l;
            if (count(nums, mid) <= mid) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if (count(nums, l) <= l) {
            return r;
        }
        return l;
    }

    public int count(int[] nums, int x) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= x) {
                res++;
            }
        }
        return res;
    }
}
