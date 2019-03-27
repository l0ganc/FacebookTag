import java.util.PriorityQueue;

public class FindTheKthSmallestElementInMSortedArrays {
    static class MyArray {
        int index;
        int[] array;
        public MyArray (int index, int[] array) {
            this.index = index;
            this.array = array;
        }
    }

    public static int findKthSmallestElementInMSortedArray(int[][] arr, int k) {
        // 最小堆，根据每个array里的index所在元素排序，heap的size是m，m个array
        PriorityQueue<MyArray> pq = new PriorityQueue<>((a, b) -> a.array[a.index] - b.array[b.index]);
        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            pq.add(new MyArray(0, arr[i]));
        }

        int mark = 0;

        // while heap is not empty
        while (!pq.isEmpty()) {
            MyArray cur = pq.poll();
            mark++;
            if (mark == k) return cur.array[cur.index];
            if (cur.index < cur.array.length - 1) {
                pq.add(new MyArray(cur.index + 1, cur.array));
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {2, 3, 9},
                {1, 3, 4},
                {5, 6, 7},
                {4, 5, 8}
        };
        int k = 6;
        System.out.println(findKthSmallestElementInMSortedArray(arr, k));
    }
}
