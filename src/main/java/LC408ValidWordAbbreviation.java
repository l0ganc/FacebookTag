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

            if (abbr.charAt(j) <= '0' || abbr.charAt(j) > '9') {
                return false;
            }

            int start = j;
            while (j < abbr.length() && abbr.charAt(j) >= '0' && abbr.charAt(j) <= '9') {
                j++;
            }
            int num = Integer.valueOf(abbr.substring(start, j));
            i += num;
        }

        return i == word.length() && j == abbr.length();
    }

    public static void main(String[] args) {
        String s = "internationalization";
        String abbr = "i12iz4n";
        System.out.println(validWordAbbreviation(s, abbr));
        System.out.println(validWordAbbreviation("apple", "a2e"));
        String str = "abc";
        System.out.println(str.charAt(0) - '0');
        System.out.println(str.charAt(0) - '9');
    }
}
