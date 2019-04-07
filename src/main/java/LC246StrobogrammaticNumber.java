public class LC246StrobogrammaticNumber {
    /**
     * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
     *
     * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
     *
     * Example 1:
     *
     * Input:  "69"
     * Output: true
     * Example 2:
     *
     * Input:  "88"
     * Output: true
     */
    public static boolean isStrobogrammatic(String num) {
        int[] map = new int[10];
        map[0] = 0;
        map[1] = 1;
        map[6] = 9;
        map[8] = 8;
        map[9] = 6;

        int i = 0;
        int j = num.length() - 1;

        while (i <= j) {
            if (map[num.charAt(i) - '0'] != num.charAt(j) - '0') {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isStrobogrammatic("69"));
        System.out.println(isStrobogrammatic("88"));
        System.out.println(isStrobogrammatic("826"));
    }
}
