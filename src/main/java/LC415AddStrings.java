public class LC415AddStrings {
    /**
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
     *
     *
     */

    public static String addStrings(String nums1, String nums2) {
        int i = nums1.length() - 1;
        int j = nums2.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while  (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += nums1.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += nums2.charAt(j--) - '0';
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }


    // follow up : 字符串中带小数，例如 124.9 + 789.12 = 914.02
    public static String addStrings2(String num1, String num2) {
        int i1 = 0;
        while (i1 < num1.length() && num1.charAt(i1) != '.') i1++;
        int i2 = 0;
        while (i2 < num2.length() && num2.charAt(i2) != '.') i2++;

        String subNum1 = i1 == num1.length()? "" : num1.substring(i1 + 1);
        String subNum2 = i2 == num2.length()? "" : num2.substring(i2 + 1);
        int diff = subNum1.length() - subNum2.length();
        if (diff > 0)
            while (diff-- > 0)
                subNum2 += '0';
        else {
            diff = -diff;
            while (diff-- > 0)
                subNum1 += '0';
        }

        String sum1 = addStrings(subNum1, subNum2);
        String sum2 = addStrings(num1.substring(0, i1), num2.substring(0, i2));

        if (sum1.length() == 0) return sum2;
        if (sum1.length() > Math.max(subNum1.length(), subNum2.length())) return addStrings(sum2, "1") + "." + sum1.substring(1);
        else return sum2 + "." + sum1;
    }


    public static void main(String[] args) {
        System.out.println(addStrings("123", "877"));
        System.out.println(addStrings2("124.9", "789.12"));
    }
}
