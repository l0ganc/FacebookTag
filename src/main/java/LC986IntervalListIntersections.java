import java.util.ArrayList;
import java.util.List;

public class LC986IntervalListIntersections {
    /**
     * Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
     *
     * Return the intersection of these two interval lists.
     *
     * (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
     * The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.
     *          For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
     *
     *  (2,8)
     *  (2,4) (5,6) (7,9)
     *  应该return (2,4) (5,6) (7,8)
     */

    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        int i = 0, j = 0;
        List<Interval> res = new ArrayList<>();

        while (i < A.length && j < B.length) {
            int start = Math.max(A[i].start, B[j].start);
            int end = Math.min(A[i].end, B[i].end);
            if (start <= end) {
                res.add(new Interval(start, end));
            }

            if (A[i].end < B[i].end) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new Interval[res.size()]);
    }

    public static void main(String[] args) {

    }

}
