public class FindLocalMinumumInAnArray {
    /**
     * Given an array arr[0 .. n-1] of distinct integers, the task is to find a local minima in it. We say that an element arr[x] is a local minimum if it is less than or equal to both its neighbors.
     *
     * For corner elements, we need to consider only one neighbor for comparison.
     * There can be more than one local minima in an array, we need to find any one of them.
     *
     * 本体作为LC162的变种，找局部最小，就是说要找的这个值比左边的小，也比右边的小
     * 同样还是用二分，返回的是index
     */

    public static int findLocalMinimumElement(int[] nums){
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] < nums[mid + 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (nums[start] < nums[end]) {
            return start;
        }
        return end;
    }

    public static void main(String[] args) {
        int[] nums = {9, 6, 3, 14, 5, 7, 4};
        int[] nums2 = {23, 8, 15, 2, 3};
        int[] nums3 = {1, 2, 3};
        int[] nums4 = {3, 2, 1};
        System.out.println(findLocalMinimumElement(nums));
        System.out.println(findLocalMinimumElement(nums2));
        System.out.println(findLocalMinimumElement(nums3));
        System.out.println(findLocalMinimumElement(nums4));
    }
}
