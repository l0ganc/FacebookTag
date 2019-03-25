import java.util.HashMap;
import java.util.Map;

public class LC149MaxPointsonALine {
    /**
     * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
     * Output: 4
     * Explanation:
     * ^
     * |
     * |  o
     * |     o        o
     * |        o
     * |  o        o
     * +------------------->
     * 0  1  2  3  4  5  6
     *
     * O(n^2),
     * 注意不能用斜率来做，因为会丢失精度导致结果不准确，这里用Map做，key是字符串(dx + "," + dy)
     */

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> map =  new HashMap<>();
            int samePoint = 1;
            int localMax = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[j].y - points[i].y;
                int y = points[j].x - points[i].x;

                // 相同点
                if (x == 0 && y == 0) {
                    samePoint++;
                    continue;
                }

                int gcd = gcd(x, y);
                int dx = x / gcd;
                int dy = y / gcd;

                String str = dx + "," + dy;
                map.put(str, map.getOrDefault(str, 0) + 1);
                localMax = Math.max(localMax, map.get(str));
            }
            res = Math.max(res, localMax + samePoint);
        }
        return res;
    }

    // 计算两个数的最大公约苏
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(3, 2),
                new Point(5, 3),
                new Point(4, 1),
                new Point(2, 3),
                new Point(1, 4),
        };
        System.out.println(maxPoints(points));
    }
}
