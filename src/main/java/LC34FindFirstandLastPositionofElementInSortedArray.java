import java.util.Arrays;

public class LC34FindFirstandLastPositionofElementInSortedArray {
    /**
     * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
     *
     * Your algorithm's runtime complexity must be in the order of O(log n).
     *
     * If the target is not found in the array, return [-1, -1].
     *
     * Example 1:
     *
     * Input: nums = [5,7,7,8,8,10], target = 8
     * Output: [3,4]
     *
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return res;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                // find the next target index from mid
                start = mid;
                end = mid;
                while (start - 1 > 0 && nums[start - 1] == target) {
                    start--;
                }
                while (end + 1 < nums.length && nums[end + 1] == target) {
                    end++;
                }
                break;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (nums[start] == target) {
            res[0] = start;
            res[1] = end;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {5,7,7,8,8,10};
        int target1 = 8;
        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;

        System.out.println(Arrays.toString(searchRange(nums1, target1)));
        System.out.println(Arrays.toString(searchRange(nums2, target2)));
    }
}
