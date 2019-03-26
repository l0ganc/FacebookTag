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

    public static void main(String[] args) {
        int[] nums = {4,14,2};
        System.out.println(totalHammingDistance(nums));
    }
}
