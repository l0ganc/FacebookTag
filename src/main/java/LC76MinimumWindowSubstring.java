import java.util.HashMap;

/**
 * Given a string S and a string T,
 * find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 */
public class LC76MinimumWindowSubstring {
    // 滑动窗口经典题目， time = O(∣S∣+∣T∣), space = O(∣S∣+∣T∣)
    /**
     * We start with two pointers, begin and end initially pointing to the first element of the string S.
     *
     * We use the end pointer to expand the window until we get a desirable window
     *      i.e. a window that contains all of the characters of T.
     *
     * Once we have a window with all the characters, we can move the begin pointer ahead one by one.
     *      If the window is still a desirable one we keep on updating the minimum window size.
     *
     * If the window is not desirable any more, we repeat step 2 onwards.
     */
    public static String minWindow(String s, String t) {
        String res = "";
        if (s.length() < t.length()) {
            return res;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int begin = 0, end = 0;
        int len = Integer.MAX_VALUE;
        int count = map.size();
        int head = 0;   // store the start index of minWindow string

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    count--;
                }
            }
            end++;

            while (count == 0) {
                char temp = s.charAt(begin);
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                    if (map.get(temp) > 0) {
                        count++;
                    }
                }
                if (end - begin < len) {
                    len = end - begin;
                    head = begin;
                }
                begin++;
            }
        }
        if (len == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(head, head + len);
    }

    public static void main(String[] args) {
        String S = "ADOBECODEBANC";
        String T = "ABC";
        System.out.println(minWindow(S, T));
    }
}
