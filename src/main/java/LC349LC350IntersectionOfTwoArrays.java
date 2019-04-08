import java.util.*;

/**
 * 349是不带重复的样子，用set可以做，350是有重复元素(set换成list). follow up主要是两个数组是排序好的怎么做，
 * 如果两个数组长度相差不大可以用two pointers，如果相差很大，就用binary search（O(nlogm)），
 * 从长度小的数组开始，去另一个数组里查找有没有对应的，然后记录下失败的位置，下次binary seach就从这个点开始
 */
public class LC349LC350IntersectionOfTwoArrays {
    // method 1: using sort, time = O(nlogn), space = O(n)
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;

        Set<Integer> set = new HashSet<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] res = new int[set.size()];
        i = 0;
        for (int num : set) {
            res[i++] = num;
        }
        return res;
    }

    // method 2: using two sets, time = O(n), space = O(n)
    public static int[] intersectionMethod2(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        for (int num : nums2) {
            if (set.contains(num)) {
                resSet.add(num);
            }
        }

        int i = 0;
        int[] res = new int[resSet.size()];
        for (int num : resSet) {
            res[i++] = num;
        }
        return res;
    }

    // method 3 : binary search, time = O(nlogn);
    public static int[] intersectionMethod3(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums2);

        for (int num : nums1) {
            if (binarysearch(nums2, num)) {
                set.add(num);
            }
        }

        int i = 0;
        int[] res = new int[set.size()];
        for (int num : set) {
            res[i++] = num;
        }
        return res;
    }

    // LC350 Intersection of Two Arrays II, 350是有重复元素(set换成list).
    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0;
        List<Integer> res = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                res.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] arr = new int[res.size()];
        int k = 0;
        for (Integer num : res) {
            arr[k++] = num;
        }
        return arr;
    }


    private static boolean binarysearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
