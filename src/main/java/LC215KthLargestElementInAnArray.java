import java.util.PriorityQueue;

public class LC215KthLargestElementInAnArray {
    /**
     * 两种方法，一种用min heap; 一种用QuickSelect
     * 其实也可以用max heap做，Time: O(n + klog(n)) | Space: O(n)
     */

    // time = O(k) + O(N * logk) 因为建一个大小为k的堆需要花O(k)时间
    // space = O(k)
    public static int i(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    /**
     * quickselect
     * time: O(N) in the average case, O(N^2) in the worse case
     * space : O(1)
     * Choose a random pivot.
     *
     * Use a partition algorithm to place the pivot into its perfect position pos in the sorted array,
     *      move larger elements to the left of pivot, and smaller or equal ones - to the right.
     *
     * 因为是求第k大，可以用稍微改版的quick select来做，将比pivot大的元素都移到左边，比它小的都移到右边，是反个的quick select
     */
    public static int i2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (true)  {
            int position = partition(nums, left, right);
            if (position + 1 == k) {
                return nums[position];
            } else if (position + 1 < k) {
                left = position + 1;
            } else {
                right = position - 1;
            }
        }
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[left];   // 选取nums[left]为pivot
        int l = left + 1;
        int r = right;

        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l++, r--);
            } else if (nums[l] >= pivot) {
                l++;
            } else if (nums[r] <= pivot) {
                r--;
            }
        }
        swap(nums, left, r);
        return r;
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums1 = {3,2,1,5,6,4};
        int k1 = 2;

        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2 = 4;

        System.out.println(i(nums1, k1));
        System.out.println(i(nums2, k2));
    }
}
