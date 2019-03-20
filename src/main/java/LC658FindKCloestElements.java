import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a sorted array, two integers k and x,
 * find the k closest elements to x in the array.
 * The result should also be sorted in ascending order.
 * If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 */
public class LC658FindKCloestElements {

    // O(n) solution
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int low = 0;
        int high = arr.length - 1;

        while (high - low >= k) {
            if (Math.abs(arr[low] - x) > Math.abs(arr[high] - x)) {
                low++;
            } else {
                high--;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = low; i <= high; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    //O(log(N - K)) to binary research and find reseult
    //O(N) to create the returned list.
    // 相当于一个大小为k的滑动窗口，假设k=3, x = 6
    // 1, 3, 4, 5, 6, 9 一开始这个窗口里有1,3,4
    // 到底要不要向前移动呢，主要要比较1跟x的距离以及x跟5的距离，就是x-1>5-x,因此要移动
    /**
     * Assume we are taking A[i] ~ A[i + k -1].
     * We can binary research i
     * We compare the distance between x - A[mid] and A[mid + k] - x
     *
     * If x - A[mid] > A[mid + k] - x,
     * it means A[mid + 1] ~ A[mid + k] is better than A[mid] ~ A[mid + k - 1],
     * and we have mid smaller than the right i.
     * So assign left = mid + 1.
     *
     * Reversely, it's similar.
     */
    public static List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - k;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (x - arr[mid] > arr[mid +  k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 6, 9, 100, 1000};
        int k = 3;
        int x = 6;
        System.out.println(findClosestElements(arr, k, x));
        System.out.println(findClosestElements2(arr, k, x));
    }
}
