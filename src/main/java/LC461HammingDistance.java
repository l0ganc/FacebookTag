public class LC461HammingDistance {
    /**
     * Hamming Distance
     * Input: x = 1, y = 4
     *
     * Output: 2
     *
     * Explanation:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     *
     * The above arrows point to positions where the corresponding bits are different.
     *
     */
    public static int hammingDistance(int x, int y)  {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int bitx = x % 2;
            int bity = y % 2;
            if (bitx != bity)  {
                res++;
            }
            x /= 2;
            y /= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }
}
