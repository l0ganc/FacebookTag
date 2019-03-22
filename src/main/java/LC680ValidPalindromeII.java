public class LC680ValidPalindromeII {
    /**
     * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
     *
     * Example 1:
     * Input: "aba"
     * Output: True
     *
     * time : O(N)  space : O(1)
     */
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int i = 0;
        int j = s.length() - 1;
        while  (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalind(s, i + 1, j) || isPalind(s, i, j - 1);
            }
            i++;
            j--;
        }
        return true;
    }

    private static boolean isPalind(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
