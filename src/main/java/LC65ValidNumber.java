public class LC65ValidNumber {
    /**
     * Validate if a given string can be interpreted as a decimal number.
     *
     * fb常考的是不带e的，只有正负号小数，下面就给出这种情况下的解
     */

    //  简化版，不用考虑e
    public static boolean isNumberSimple(String s) {
        s = s.trim();  // 如果不给用trim就用一个point来skip空格
        boolean pointSeen = false;
        boolean numberSeen = false;

        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            } else if (s.charAt(i) == '.') {
                if (pointSeen) {
                    return false;
                }
                pointSeen =  true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0) {
                    return false;
                }
            } else {
                return  false;
            }
        }
        return numberSeen;
    }

    // LC65: Valid number原版
    public static boolean isNumber(String s) {
        s = s.trim();
        boolean eSeen = false;
        boolean pointSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = false;

        for (int i = 0; i < s.length(); i++)  {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {  // If we see [0-9] we reset the number flags.
                numberSeen = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {   // We can only see . if we didn't see 'e' or '.'
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {  // We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
                if (eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') { // We can only see + and - in the beginning and after an e
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return numberSeen && numberAfterE;
    }

    /**
     * "0" => true
     * " 0.1 " => true
     * "abc" => false
     * "1 a" => false
     * "2e10" => true
     * " -90e3   " => true
     * " 1e" => false
     * "e3" => false
     * " 6e-1" => true
     * " 99e2.5 " => false
     * "53.5e93" => true
     * " --6 " => false
     * "-+3" => false
     * "95a54e53" => false
     */
    public static void main(String[] args) {
        System.out.println(isNumber("0"));
        System.out.println(isNumber(" 0.1"));
        System.out.println(isNumber("abc"));
        System.out.println(isNumber("1 a"));
        System.out.println(isNumber("2e10"));
        System.out.println(isNumber(" -90e3"));
        System.out.println(isNumber(" 1e"));
        System.out.println(isNumber("e3"));
        System.out.println(isNumber(" 6e-1"));
        System.out.println(isNumber(" 99e2.5"));
        System.out.println(isNumber("53.5e93"));
        System.out.println(isNumber(" --6"));
        System.out.println(isNumber("-+3"));
        System.out.println(isNumber("95a54e53"));
        System.out.println(isNumber(".1"));
        System.out.println(isNumber(". 1"));
        System.out.println(isNumber("00000010000"));
    }
}
