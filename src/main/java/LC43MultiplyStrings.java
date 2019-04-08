public class LC43MultiplyStrings {
    /**
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
     *
     * Example 1:
     *
     * Input: num1 = "2", num2 = "3"
     * Output: "6"
     * Example 2:
     *
     * Input: num1 = "123", num2 = "456"
     * Output: "56088"
     */

    public static String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] res = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int temp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = temp + res[p2];
                res[p1] += sum / 10;
                res[p2] = sum % 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (sb.length() == 0 && num == 0) {
                continue;
            } else {
                sb.append(num);
            }
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("2", "3"));
        System.out.println(multiply("123", "456"));
    }
}
