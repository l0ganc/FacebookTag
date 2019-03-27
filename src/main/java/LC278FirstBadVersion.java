public class LC278FirstBadVersion {
    // 二分啦
    //The isBadVersion API is defined in the parent class VersionControl.
    static boolean isBadVersion(int version) {
        return true;
    }

    public int firstBadVersion(int n) {
        if (n <= 0) {
            return -1;
        }

        int start = 1;
        int end = n;
        while (start < end) {
            int mid = (end - start) / 2;
            if (!isBadVersion(mid)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
