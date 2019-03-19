import java.util.*;

public class LC253MeetingRoomsII {
    public static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
    }


    // LC252: MeetingRooms 判断一个人能否参加所有会议
    // time = O(nlogn); space = O(1)
    public static boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }

        Arrays.sort(intervals, (a, b) -> a.start - b.start);
        for (int i= 1; i < intervals.length; i++) {
            if (intervals[i- 1].end > intervals[i].start) {
                return false;
            }
        }
        return true;
    }

    // 扫描线算法
    // 将meeting开始和结束的时间点分别存在list中。
    // 对整个list按时间排序。相同的时间结束应该优先于开始。
    // 遍历排序好的list，对同时存在的线段进行计数并记录时间。
    static class Point {
        int time;
        int flag;
        public Point (int time, int flag) {
            this.time = time;
            this.flag = flag;  // 开始时间标记为1， 结束时间标记为0
        }
    }

    // 返回同时最多meeting room的时间
    public static int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        List<Point> list = new ArrayList<>();
        for (Interval i : intervals) {
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }

        Collections.sort(list, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (p1.time == p2.time) {
                    return p1.flag - p2.flag; // end has priority over start
                }
                return p1.time - p2.time;
            }
        });
        int res = 0;
        int max = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).flag == 1) {
                count++;
            } else {
                count--;
            }
            if (count > max) {
                max = count;
                res = list.get(i).time;
            }
        }
        return res;
    }

    // 当输入是10a - 22:40a 的形式如何转换
    public Interval translate(String input) {
        String[] splits = input.split("-");
        String start = splits[0].trim();
        String end   = splits[1].trim();
        return new Interval(convert(start), convert(end));
    }

    private int convert(String time) {
        boolean PM = false;
        if (time.charAt(time.length() - 1) == 'p')
            PM = true;
        time = time.substring(0, time.length() - 1);
        String[] splits = time.split(":");
        int hour = Integer.parseInt(splits[0]);
        int minute = Integer.parseInt(splits[1]);
        if (PM && hour != 12) hour += 12;
        return hour * 60 + minute;
    }


    // LC56: merge interval
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
}
