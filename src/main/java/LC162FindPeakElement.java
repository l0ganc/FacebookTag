public class LC162FindPeakElement {
    /**
     * A peak element is an element that is greater than its neighbors.
     * Input: nums = [1,2,3,1]
     * Output: 2
     * Explanation: 3 is a peak element and your function should return the index number 2.
     *
     * 二分法
     */

    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length ==  0) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid =  (end - start) / 2 + start;
            if (nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] > nums[end]) {
            return start;
        }
        return end;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,1};
        System.out.println(findPeakElement(nums));
    }
}
