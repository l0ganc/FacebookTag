import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotProduct {
    /**
     *  超高频dot product
     *
     */
    // 常考的两个稀疏向量的dot product
    // map存index跟value版本
    public static int[] dotHash(int[] a, int[] b) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 0) {
                map.put(i, a[i]);
            }
        }

        int[] c = new int[a.length];
        for (int i = 0; i < b.length; i++) {
            if (b[i] > 0 && map.containsKey(i)) {
                c[i] = b[i] * map.get(i);
            }
        }
        return c;
    }


    // 另一种是造一个 Vector内部类, 用two pointer来做
    static class Vector{
        int index;
        int value;
        public Vector(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public static int[] dotList(int[] va, int[] vb) {
        List<Vector> a = new ArrayList<>();
        for (int i = 0; i < va.length; i++) {
            if (va[i] > 0) {
                a.add(new Vector(i, va[i]));
            }
        }

        List<Vector> b = new ArrayList<>();
        for (int i = 0; i < vb.length; i++) {
            if (vb[i] > 0) {
                b.add(new Vector(i, vb[i]));
            }
        }

        int[] c = new int[va.length];

        // two pointer
        int i = 0, j = 0;
        while (i < a.size() && j < b.size()) {
            while (i < a.size() && a.get(i).index < b.get(j).index) {
                i++;
            }
            if (i == a.size()) break;
            while (j < b.size() && a.get(i).index > b.get(j).index) {
                j++;
            }
            c[a.get(i).index] = a.get(i++).value * b.get(j++).value;
        }
        return c;
    }

    // 如果一个list特别稀疏，那么就用下面的binary search方法来优化
    public static int[] dotList2(int[] va, int[] vb) {
        List<Vector> a = new ArrayList<>();
        for (int i = 0; i < va.length; i++) {
            if (va[i] > 0) {
                a.add(new Vector(i, va[i]));
            }
        }

        List<Vector> b = new ArrayList<>();
        for (int i = 0; i < vb.length; i++) {
            if (vb[i] > 0) {
                b.add(new Vector(i, vb[i]));
            }
        }

        int[] c = new int[va.length];

        // 开始binary search
        for (int i = 0; i < b.size(); i++) {
            int tmp = binarySearch(a, b.get(i).index);
            if (tmp != 0) {
                tmp *= b.get(i).value;
                c[b.get(i).index] = tmp;
            }
        }
        return c;
    }

    private static int binarySearch(List<Vector> a, int target) {
        int left = 0, right = a.size();
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (a.get(mid).index == target) {
                return a.get(mid).value;
            } else if (a.get(mid).index < target) {
                left = mid +  1;
            } else {
                right = mid;
            }
        }

        if (left >= a.size() || a.get(left).index != target)  {
            return 0;
        }
        return a.get(left).value;
    }


    public static void main(String[] args) {
        int len = 1000;
        int[] a = new int[len];
        a[200] = 200;
        a[300] = 300;
        a[500] = 500;
        int[] b = new int[len];
        b[100] = 100;
        b[200] = 203;
        b[500] = 504;

        int[] c = dotList(a, b);
        for (int i = 0; i < c.length; i ++)
            if (c[i] > 0)
                System.out.println(i + " " + c[i]);

        int[] c2 = dotList2(a, b);
        for (int i = 0; i < c2.length; i ++)
            if (c2[i] > 0)
                System.out.println(i + " " + c2[i]);
    }
}
