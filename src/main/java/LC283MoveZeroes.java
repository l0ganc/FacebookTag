public class LC283MoveZeroes {
    /**
     * Input: [0,1,0,3,12]
     * Output: [1,3,12,0,0]
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int i = 0;
        int j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                swap(nums, i++, j);
            }
            j++;
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
