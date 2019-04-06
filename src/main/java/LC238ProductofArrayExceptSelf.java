import java.util.Arrays;

public class LC238ProductofArrayExceptSelf {
    /**
     * 最原始的思路：第一遍从左往右，计算除了当前元素外，从左边开始到当前元素前一个的乘积；
     *             第二遍从右往左，计算除了当前元素外，从右边直到最后元素的乘积；
     *             最后将这两部分乘积分别相乘就是最终的结果。
     * 如果不考虑结果数组占用的空间，这种方法时间复杂度是O(N), 空间复杂度是O(1)
     *
     * 另一种方法是用除法，需要考虑0的个数，corner case要处理
     */

    public static int[] productExceptSelf(int[] nums) {
        if (nums ==  null || nums.length == 0) {
            return nums;
        }

        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int tmp = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= tmp;
            tmp *= nums[i];
        }

        return res;
    }


    // method 2: Use division and zero judge
    public static int[] productExceptSelf2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int sum = 1;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                sum *= nums[i];
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                if (count > 1) {
                    nums[i] = 0;
                } else {
                    nums[i] = sum;
                }
            } else {
                if (count > 0) {
                    nums[i] = 0;
                } else {
                    nums[i] =  sum / nums[i];
                }
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,3,4,0};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
        System.out.println(Arrays.toString(productExceptSelf2(nums)));
    }

}
