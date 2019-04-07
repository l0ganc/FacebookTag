import java.util.Arrays;
import java.util.PriorityQueue;

public class LC973KClosestPointstoOrigin {
    /**
     * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
     * Output: [[3,3],[-2,4]]
     * (The answer [[-2,4],[3,3]] would also be accepted.)
     */


    // method1: sorting, time = O(NlogN), space = O(1)
    public static int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] + p2[1] * p2[1]);
        return Arrays.copyOfRange(points, 0, K);
    }

    // method2: max-heap, time = O(Nlogk), space = P(k)
    public static int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) ->  p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] + p2[1] * p2[1]);
        for (int i = 0; i < points.length; i++) {
            pq.add(points[i]);
            if (pq.size() > K) {
                pq.poll();
            }
        }

        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = pq.poll();
        }

        return res;
    }


    // method3: quick select,
    // the average time complexity is O(N) , but just like quick sort, in the worst case, this solution would be degenerated to O(N^2)
    // space = O(N)
    public int[][] kClosest3(int[][] points, int K) {
        int len =  points.length, l = 0, r = len - 1;
        while (l <= r) {
            int mid = helper(points, l, r);
            if (mid == K) break;
            if (mid < K) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return Arrays.copyOfRange(points, 0, K);
    }

    private int helper(int[][] A, int l, int r) {
        int[] pivot = A[l];
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--;
            A[l] = A[r];
            while (l < r && compare(A[l], pivot) <= 0) l++;
            A[r] = A[l];
        }
        A[l] = pivot;
        return l;
    }

    private int compare(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
    }

}
