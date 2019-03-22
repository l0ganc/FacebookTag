public class LC67AddBinary {
    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += a.charAt(i--) - '0';
            }
            if (j >= 0) {
                sum += b.charAt(j--) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }


    // 使用bit操作
    public static String addBinaryBit(String a, String b) {
        if(a == null || a.length() == 0) {
            return b;
        }
        if(b == null || b.length() == 0) {
            return a;
        }

        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while(i >= 0 || j >= 0) {
            int aI = i >= 0 ? (a.charAt(i) - '0') : 0;
            int bI = j >= 0 ? (b.charAt(j) - '0') : 0;

            int curr = aI ^ bI ^ carry;   // 相当于算出低位
            carry = (aI & bI) | (aI & carry) | (bI & carry);  // 算出进位
            i--;
            j--;
            res.append(curr);
        }

        if(carry != 0) {
            res.append(carry);
        }
        return res.reverse().toString();
    }

    // follow up with base
    public String addBinary3(String a, String b) {
        int base = 2, carry = 0;
        int len = Math.max(a.length(), b.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i ++) {
            int tmp = carry;
            if (i < a.length())
                tmp += a.charAt(a.length() - i - 1) - '0';
            if (i < b.length())
                tmp += b.charAt(b.length() - i - 1) - '0';
            carry = tmp / base;
            sb.append(tmp % base);
        }
        if (carry > 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
        System.out.println(addBinaryBit("11", "1"));
        System.out.println(addBinaryBit("1010", "1011"));
    }
}
