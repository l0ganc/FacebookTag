import java.util.HashSet;

public class LC3LongestSubstringWithoutRepeatingCharacters {
    /**
     * Input: "pwwkew"
     * Output: 3
     * Explanation: The answer is "wke", with the length of 3.
     *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0, res = 0;

        while (i < s.length() && j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                res = Math.max(res, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
