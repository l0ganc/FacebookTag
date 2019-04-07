import java.util.Stack;

public class LC42TrappingRainWater {
    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
     *
     */

    /**
     * two scan:
     * from left to right get the max height
     * from right to left get the max height
     * get min value from above two array
     * add every (minvalue - height) to res;
     */

    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int[] left = new int[height.length];
        int[] right = new int[height.length];
        int bar = 0;
        for (int i = 0; i < height.length; i++) {
            bar = Math.max(bar, height[i]);
            left[i] = bar;
        }
        bar = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            bar = Math.max(bar, height[i]);
            right[i] = bar;
        }

        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += (Math.min(left[i], right[i]) - height[i]);
        }

        return res;
    }


    // one scan solution, time = O(n), space = O(1)
    public static int trap2(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int barL = 0, barR = 0;

        while (left <= right) {
            barL = Math.max(height[left], barL);
            barR = Math.max(height[right], barR);
            if (barL < barR)  {
                res += (barL - height[left++]);
            } else {
                res += (barR - height[right--]);
            }
        }
        return res;
    }


    // stack solution, time = O(n), space = O(1)
    public static int trap3(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int cur = height[stack.pop()];
                if (!stack.isEmpty()) {
                    res += (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - cur);
                }
            }
            stack.push(i);
        }
        return res;
    }


    // new two pointer, time = O(n), space = O(1),想想木桶原理
    public static int trap4(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int res = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(nums));
        System.out.println(trap2(nums));
        System.out.println(trap3(nums));
        System.out.println(trap4(nums));
    }
}
