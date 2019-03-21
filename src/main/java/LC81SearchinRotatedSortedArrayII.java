public class LC81SearchinRotatedSortedArrayII {
    /**
     * This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
     * Would this affect the run-time complexity? How and why?
     *
     * 1) everytime check if targe == nums[mid], if so, we find it.
     * 2) otherwise, we check if the first half is in order (i.e. nums[left]<=nums[mid])
     *   and if so, go to step 3), otherwise, the second half is in order,   go to step 4)
     * 3) check if target in the range of [left, mid-1]
     *      (i.e. nums[left]<=target < nums[mid]), if so, do search in the first half, i.e. right = mid-1;
     *      otherwise, search in the second half left = mid+1;
     * 4) check if target in the range of [mid+1, right]
     *      (i.e. nums[mid]<target <= nums[right]), if so, do search in the second half, i.e. left = mid+1;
     *      otherwise search in the first half right = mid-1
     *
     */

    // O(logn) on average, O(n) worst case)
    public static boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                return true;
            } else if (nums[start] < nums[mid]) {
                if (target >= nums[start] &&  target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (nums[start] > nums[mid]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else {
                start++;
            }
        }
        return false;
    }
}
