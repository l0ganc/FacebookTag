import java.util.HashSet;

public class LC128LongestConsecutiveSequence {
    /**
     * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
     *
     * Your algorithm should run in O(n) complexity.
     *
     * Example:
     *
     * Input: [100, 4, 200, 1, 3, 2]
     * Output: 4
     * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
     */

    // time = O(n)
    // 先用set存每一个num，然后遍历，判断有没有curNum - 1，找到最开始的元素然后依次累加看在不在set里，更新最长
    public static int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int curMax = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum = currentNum + 1;
                    curMax++;
                }

                res = Math.max(res, curMax);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }
}
