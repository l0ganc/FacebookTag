import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC88MergeSortedArray {
    /**
     * Given two sorted integer arrays nums1 and nums2,
     * merge nums2 into nums1 as one sorted array.
     * Input:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * Output: [1,2,2,3,5,6]
     *
     * 思路：Two pointers / Start from the end
     * Time complexity : O(n+m).
     * Space complexity : O(1).
     *
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] < nums2[j]) {
                nums1[k--] = nums2[j--];
            } else {
                nums1[k--] = nums1[i--];
            }
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    static class MyArray {
        int index;
        int[] array;
        public MyArray (int index, int[] array) {
            this.index = index;
            this.array = array;
        }
    }

    // 时间复杂度：O(knlogk), k时候数组的数目，n是每个数组的长度，空间复杂度如果不考虑结果数组的所用空间就是O(k)
    public static  int[] mergeKSortedArray(int[][] arr) {
        // 最小堆，根据每个array里的index所在元素排序，heap的size是k，k个array
        PriorityQueue<MyArray> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.array[a.index]));
        int total = 0;

        // add array to heap
        for (int i = 0; i < arr.length; i++) {
            pq.add(new MyArray(0, arr[i]));
            total += arr[i].length;
        }

        int m = 0;
        int[] res = new int[total];

        // while heap is not empty
        while (!pq.isEmpty()) {
            MyArray cur = pq.poll();
            res[m++] = cur.array[cur.index];
            if (cur.index < cur.array.length - 1) {
                pq.add(new MyArray(cur.index + 1, cur.array));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        int[] arr1 = { 1, 3, 5, 7 };
        int[] arr2 = { 2, 4, 6, 8 };
        int[] arr3 = { 0, 9, 10, 11 };

        int[] result = mergeKSortedArray(new int[][] { arr1, arr2, arr3 });
        System.out.println(Arrays.toString(result));
    }
}
