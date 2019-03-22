public class LC31NextPermutation {
    /**
     * 给一个数，要求返回最小的比这个数大的数
     *
     * 1 2 6 4 2
     *       i         a[i] > a[i + 1]
     *     i           a[i] > a[i + 1]
     *   i             a[i] <= a[i + 1]    from right to left to find the first index which is less than a[index + 1]
     * 1 2 [6 4 2]
     *
     * then in the [6 4 2] to find the minimum number which is greater than a[i], in this process we can use binary search
     *  we find 4 is the right minimum number
     *
     *  swap 2 and 4
     *
     * 1 4 [6 2 2]
     *
     * finally we need to sort [6 2 2] to [2 2 6]
     * in this operation, we should use swap to decrease the time complexity
     *
     */

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int replace = nums.length - 2;
        while (replace >= 0) {
            if (nums[replace] < nums[replace + 1]) {
                break;
            }
            replace--;
        }

        if (replace < 0) {
            // corner case : 6 5 4 3 2 1
            swap(nums, 0, nums.length - 1);
            return;
        }

        // 这部分也可以优化成binary search，在一个降序排列的数组中找最小的大于target的元素
        // 在nums[replace + 1] 到 nums[nums.length -1] 这些降序排列的元素中找到最小的大于nums[replace]的元素
        int largeIndex = replace + 1;
        while (largeIndex < nums.length &&  nums[largeIndex] > nums[replace]) {
            largeIndex++;
        }

        // 交换nums[replace]跟nums[largeIndex]
        int temp = nums[replace];
        nums[replace] = nums[largeIndex - 1];
        nums[largeIndex - 1] = temp;

        // nums[replace + 1] 到 nums[nums.length -1]这些降序排列的元素改为升序
        swap(nums, replace + 1, nums.length - 1);
    }

    private static void swap(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    // 数组是降序的
    private static int findMinimumElementGreaterThanTarget(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] < target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[right] > target) {
            return right;
        }
        if (nums[left] > target) {
            return left;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {7, 6, 5, 3};
        int target = 4;
        System.out.println(findMinimumElementGreaterThanTarget(nums, target));
    }
}
