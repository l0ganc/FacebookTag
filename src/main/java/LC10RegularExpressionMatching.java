public class LC10RegularExpressionMatching {
    /**
     * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
     *
     * '.' Matches any single character.
     * '*' Matches zero or more of the preceding element.
     * s = "aab"
     * p = "c*a*b"
     * Output: true
     * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
     *
     */


    /**
     * dp题型，time = O(m * n), space = O(m * n)
     T[i][j] represent whether s from 0th into (i - 1)index with p from 0th into (j - 1)index
     can be matched or not


     T[i][j] =  T[i - 1][j - 1]   if s[i] == p[j] || p[j] == '.'
     =  T[i][j - 2]  ====> 0 occurrence
     || T[i - 1][j]   if s[i] == p[j - 1] || p[j - 1] == '.'
     = false

     e.q.
     0 1 2 3 4 5 6
     x a * b . c
     0   T F F F F F F
     1 x F T F T F F F
     2 a F F T T F F F
     3 a F F F T F F F
     4 b F F F F T F F
     5 y F F F F F T F
     6 c F F F F F F T
     *
     *
     */
    public static boolean isMatch(String s, String p) {
        boolean[][] T = new boolean[s.length() + 1][p.length() + 1];

        T[0][0] = true;
        // deals with patterns like a*, a*b*
        for (int j = 1; j < T[0].length; j++) {
            if (p.charAt(j - 1) == '*') {
                T[0][j] = T[0][j - 2];
            }
        }

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    T[i][j] = T[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    T[i][j] = T[i][j - 2];
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        T[i][j] = T[i][j] | T[i - 1][j];
                    }
                } else {
                    T[i][j] = false;
                }
            }
        }
        return T[s.length()][p.length()];
    }

    public static void main(String[] args) {
        String s = "aab";
        String p = "c*a*b";
        System.out.println(isMatch(s, p));
    }
}
