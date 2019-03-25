import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LC56MergeIntervals {
     public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    // LC56 : merge intervals, time = O(nlogn) space = O(n
    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) {
            return intervals;
        }
        List<Interval> res = new ArrayList<>();

        Collections.sort(intervals, (a, b) -> a.start - b.start);
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (end >= interval.start) {
                end = Math.max(interval.end, end);
                start = Math.min(start, interval.start);
            } else {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        res.add(new Interval(start, end));
        return res;
    }

    /**
     * 变种:红蓝胶带
     * blue tape按照 start 已经排序好了， 所以要先把blue tape按照merval interval的做法先merge一遍，
     * 然后再拿red tape进去比， 如果被blue tape的某一个区间完全包住就是false， 整个遍历一遍如果没返回false就是返回true
     */
    public static boolean isVisable(Interval redtap, List<Interval> bluetaps) {
        List<Interval> afterMergedBluetaps = merge(bluetaps);
        for (int i = 0; i < afterMergedBluetaps.size(); i++) {
            Interval blue = afterMergedBluetaps.get(i);
            if (blue.start <= redtap.start && blue.end >= redtap.end) {
                return false;
            }
        }
        return true;
    }

    /**
     * 变种：merge two list of Intervals
     */
    public static List<Interval> mergeIntervals(List<Interval> a, List<Interval> b) {
        int i = 0, j = 0;
        Interval last = null;
        List<Interval> res = new ArrayList<>();

        while (i < a.size() && j < b.size()) {
            Interval cur = null;
            if (j == b.size() || i < a.size() && a.get(i).start < b.get(j).start) {
                // pick the one with less start time
                cur = a.get(i++);
            } else {
                cur = b.get(j++);
            }

            if (last == null) {
                last = cur;
                continue;
            }
            if (last.end < cur.start) {
                res.add(last);   // 这种情况是last 跟 cur没有交集，先把last加进去
                last = cur;    // last 往前移动一步
            } else {
                last.end = Math.max(last.end, cur.end);  // 因为是merge操作，相当于是并集，所有用max
            }
        }

        if (last != null) {
            res.add(last);    // 最后别忘记加入last
        }
        return res;
    }


    /**
     * 变种 : given two sorted arrays of intervals, return the intersection.
     * ex. a = [(1 5), (8, 10), (15 20)] sorted b = [(2 6), (45 100)] sorted return
     * [(2, 5)]
     * 我⽤了两个pointer指着a和b然后⽤了⼏个case对⽐
     * follow up:如何improve？举了说很像merge 2 sorted array的问题，说可以⽤类似⽅法 然
     * 后就解释了⼀⻓⼀短两个array的时候可以⽤遍历短array元素，同时⽤
     * binary search 查询⻓array元素。时间是O(SlogL)的
     */
    public static List<Interval> intersect(List<Interval> a, List<Interval> b) {
        int i = 0, j = 0;
        List<Interval> res = new ArrayList<>();

        while (i < a.size() && j < b.size()) {
            Interval cura = a.get(i);
            Interval curb = b.get(j);

            if (!(cura.end < curb.start || curb.end < cura.start)) {
                // 有交集
                int start = Math.max(cura.start, curb.start);
                int end = Math.min(cura.end, curb.end);
                res.add(new Interval(start, end));
            }

            // 无交集
            if (cura.end < curb.end) {
                i++;
            } else {
                j++;
            }
        }
        return res;
    }


    // LC57:  Insert Interval
    /**
     * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
     *
     * You may assume that the intervals were initially sorted according to their start times.
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int i = 0;

        // add all the intervals ending before newInterval starts
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i));
        }

        // merge all overlapping intervals to one considering newInterval
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval(
                    Math.min(intervals.get(i).start, newInterval.start),
                    Math.max(intervals.get(i).end, newInterval.end));
            i++;
        }
        res.add(newInterval);  // add the union of intervals we got

        // add the union of intervals we got
        while (i < intervals.size()) {
            res.add(intervals.get(i++));
        }
        return res;
    }

    public static void main(String[] args) {
        LC56MergeIntervals sol = new LC56MergeIntervals();

        List<Interval> a = new ArrayList<>();
        a.add(new Interval(1, 5));
        a.add(new Interval(8, 10));
        a.add(new Interval(15, 25));
        List<Interval> b = new ArrayList<>();
        b.add(new Interval(2, 6));
        b.add(new Interval(25, 100));
        List<Interval> ans = sol.intersect(a, b);
        for (Interval interval : ans) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }


        List<Interval> aa = new ArrayList<>();
        aa.add(new Interval(2, 8));
        List<Interval> bb = new ArrayList<>();
        bb.add(new Interval(2, 4));
        bb.add(new Interval(5, 6));
        bb.add(new Interval(7, 9));
        List<Interval> res = mergeIntervals(aa, bb);
        for (Interval interval : res) {
            System.out.println("[" + interval.start + ", " + interval.end + "]");
        }
    }
}
