import java.util.Arrays;

public class LC75SortColorIWithII {
    /**
     * Input: [2,0,2,1,1,0]
     * Output: [0,0,1,1,2,2]
     */

    // LC75原版： two pointer
    public static void sortColors(int[] nums) {
        int left = 0;
        int index = 0;
        int right = nums.length - 1;

        while (index <= right) {
            if (nums[index] == 0) {
                swap(nums, index++, left++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, index, right--);
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    // follow up: 给定一个有n个对象（包括k种不同的颜色，并按照1到k进行编号）的数组，
    // 将对象进行分类使相同颜色的对象相邻，并按照1,2，...k的顺序进行排序。
    // O(nk)
    public static void sortColorsII(int[] colors, int k) {
        int count = 0;
        int left = 0;
        int right = colors.length - 1;

        while (count < k) {
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            for (int i = left; i <= right; i++) {
                min = Math.min(min, colors[i]);
                max = Math.max(max, colors[i]);
            }

            int cur = left;
            while (cur <= right) {
                if (colors[cur] == min) {
                    swap(colors, cur++, left++);
                } else if (colors[cur] > min && colors[cur] < max) {
                    cur++;
                } else {
                    swap(colors, cur, right--);
                }
            }

            count += 2;   // 因为每次都是排好一头一尾两个元素
        }
    }

    // 最优解： 分治法 O(nlogk)
    public void sortColors3(int[] colors, int k) {
        if (colors == null || colors.length == 0) {
            return;
        }

        rainbowSort(colors, 0, colors.length - 1, 1, k);
    }

    private static void rainbowSort(int[] colors, int left, int right, int colorFrom, int colorTo) {
        if (colorFrom == colorTo) {
            return;
        }

        if (left >= right) {
            return;
        }

        int colorMid = (colorFrom + colorTo) / 2;
        int l = left, r = right;
        while (l <= r) {
            while (l <= r && colors[l] <= colors[colorMid]) {
                l++;
            }

            while (l <= r && colors[r] > colors[colorMid]) {
                r--;
            }

            if (l <= r) {
                int temp = colors[l];
                colors[l] = colors[r];
                colors[r] = temp;
                l++;
                r--;
            }
        }

        rainbowSort(colors, left, r, colorFrom, colorMid);
        rainbowSort(colors, l, right, colorMid + 1, colorTo);
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));

        int[] nums2 = {3,2,2,1,4};
        int k = 4;
        sortColorsII(nums2, k);
        System.out.println(Arrays.toString(nums2));
    }
}
