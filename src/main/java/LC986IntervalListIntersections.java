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
            int end = Math.min(A[i].end, B[j].end);
            if (start <= end) {
                res.add(new Interval(start, end));
            }

            if (A[i].end < B[j].end) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new Interval[res.size()]);
    }

    public static void main(String[] args) {
        Interval[] A = new Interval[4];
        Interval[] B = new Interval[4];
        A[0] = new Interval(0, 2);
        A[1] = new Interval(5, 10);
        A[2] = new Interval(13, 23);
        A[3] = new Interval(24, 25);

        B[0] = new Interval(1, 5);
        B[1] = new Interval(8, 12);
        B[2] = new Interval(15, 24);
        B[3] = new Interval(25, 26);


        Interval[] C = new Interval[1];
        Interval[] D = new Interval[3];
        C[0] = new Interval(2, 8);

        D[0] = new Interval(2, 4);
        D[1] = new Interval(5, 6);
        D[2] = new Interval(7, 9);

        Interval[] res = intervalIntersection(A, B);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i].start + "," + res[i].end);
        }

        System.out.println("**********************");
        System.out.println("");
        Interval[] res2 = intervalIntersection(C, D);
        for (int i = 0; i < res2.length; i++) {
            System.out.println(res2[i].start + "," + res2[i].end);
        }

    }

}
