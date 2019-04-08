public class LC477TotalHammingDistance {
    /**
     * Total Hamming Distance
     * Input: 4, 14, 2
     *
     * Output: 6
     *
     * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
     * showing the four bits relevant in this case). So the answer will be:
     * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
     */

    public static int totalHammingDistance(int[] nums) {
        int total = 0;
        int n = nums.length;

        for (int j = 0; j < 32; j++) {
            int bitCount = 0;
            for (int i = 0; i < nums.length; i++) {
                bitCount += (nums[i] >> j) & 1;
            }
            total += bitCount * (n - bitCount);
        }
        return total;
    }


    /**
     * new
     * 位操作的题，对32位每一位统计为0的个数乘上为1的个数就是最后的结果
     *
     * 4  :   0100
     * 14 :   1110
     * 2  :   0010
     * bit    ones    zeros     pairs
     *  0      0     [4,14,2]     0
     *  1     [2,14]    [4]       2*1=2
     *  2     [4,14]    [2]       2*1=2
     *  3     [14]     [4,2]      1*2=2
     */
    public static int totalHammingDistance2(int[] nums) {
        int res = 0;

        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                if (((num >> i) & 1) == 1) {
                    count++;
                }
            }
            res += (nums.length - count) * count;    // count就是为1的个数，nums.length - count就是为0的个数
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {4,14,2};
        System.out.println(totalHammingDistance(nums));
        System.out.println(totalHammingDistance2(nums));
    }
}
