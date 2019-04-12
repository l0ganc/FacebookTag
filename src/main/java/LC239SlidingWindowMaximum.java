import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LC239SlidingWindowMaximum {
    /**
     * Sliding Window Maximum
     *
     * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
     *
     * Example:
     *
     * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
     * Output: [3,3,5,5,6,7]
     * Explanation:
     *
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     */

    /**
     *
     We scan the array from 0 to n-1, keep "promising" elements in the deque. The algorithm is amortized O(n) as each element is put and polled once.

     At each i, we keep "promising" elements, which are potentially max number in window [i-(k-1),i] or any subsequent window. This means

     If an element in the deque and it is out of i-(k-1), we discard them. We just need to poll from the head, as we are using a deque and elements are ordered as the sequence in the array

     Now only those elements within [i-(k-1),i] are in the deque. We then discard elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent window: a[i] would always be a better candidate.

     As a result elements in the deque are ordered in both sequence in array and their value. At each step the head of the deque is the max element in [i-(k-1),i]
     */


    public int[] maxSlidingWindow(int[] a, int k) {
        if (a == null || k <= 0) {
            return new int[0];
        }

        int[] res = new int[a.length - k + 1];   // 没k的原数组中的元素里有一个是结果数组的元素
        Deque<Integer> deque = new ArrayDeque<>();  // deque中存的是下标，从deque的头部开始到尾部是一个降序(按照下标所在元素)的序列，
        int index = 0;  // 记录结果数组的下标

        for (int i = 0; i < a.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();   // remove element out of range k，从头部开始剔除
            }

            while (!deque.isEmpty() && a[deque.peekLast()] < a[i]) {
                // 从尾部开始，剔除小于当前元素的下标
                deque.pollLast();
            }

            deque.offer(i);   // 压元素进deque，从尾部进入
            if (i >= k - 1) {
                // 更新res数组
                res[index++] = a[deque.peekFirst()];
            }
        }
        return res;
    }



    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        LC239SlidingWindowMaximum obj = new LC239SlidingWindowMaximum();
        System.out.println(Arrays.toString(obj.maxSlidingWindow(nums, 3)));
    }

    /**
     * 双端队列Deque. https://www.jianshu.com/p/d78a7c982edb
     */
}
