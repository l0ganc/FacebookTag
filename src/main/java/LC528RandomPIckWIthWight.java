import java.util.Random;

public class LC528RandomPIckWIthWight {
    /**
     * Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.
     * Input:
     * ["Solution","pickIndex"]
     * [[[1]],[]]
     * Output: [null,0]
     * Example 2:
     *
     * Input:
     * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
     * [[[1,3]],[],[],[],[],[]]
     * Output: [null,0,1,1,1,0]
     *
     * 首先计算前缀和到weight数组里，然后利用random函数算出一个值，
     * 最后用二分法查找算出的这个值落在哪个范围内
     *
     */

    static int[] weights;
    int total = 0;
    Random random = new Random();

    // O(n) to init
    public LC528RandomPIckWIthWight(int[] w) {
        if (w == null || w.length == 0) {
            return;
        }

        weights = new int[w.length];
        for (int i = 0; i < w.length; i++) {
            total += w[i];
            weights[i] = total;
        }
    }

    // O(logn）for one pick
    public int pickIndex() {
        int target = random.nextInt(total);
        int start = 0;
        int end = weights.length - 1;

        while (start <= end) {
            int mid = (end - start) / 2 + start;
            if (weights[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}

