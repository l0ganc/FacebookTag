public class LC408ValidWordAbbreviation {
    /**
     * Example 1:
     * Given s = "internationalization", abbr = "i12iz4n":
     *
     * Return true.
     * Example 2:
     * Given s = "apple", abbr = "a2e":
     *
     * Return false.
     *
     * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
     */

    public static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;

        while (i < word.length() && j < abbr.length()) {
            if (word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            }

            // 如果程序运行到这里，表明遇到了不相等的字符，如果abbr中的字符不是数字，直接返回false
            // 是数字字符的话第一位不能是'0'，所以下面的范围是不能等于'0'的
            if (!(abbr.charAt(j) > '0' && abbr.charAt(j) <= '9')) {
                return false;
            }

            int start = j;
            while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                j++;
            }
            int num = Integer.valueOf(abbr.substring(start, j));   // 计算出数字
            i += num;   // 跳过word中对应的字符
        }

        return i == word.length() && j == abbr.length();
    }

    public static void main(String[] args) {
        String s = "internationalization";
        String abbr = "i12iz4n";
        System.out.println(validWordAbbreviation(s, abbr));
        System.out.println(validWordAbbreviation("apple", "a2e"));
    }
}
