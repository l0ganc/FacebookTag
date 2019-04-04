import java.util.HashMap;
import java.util.Map;

public class LC340LongestSubstringWithAtMostKDistinctCharacters {
    // sliding window 又一题，time = O(n), space = O(n)
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        int start = 0, end = 0;
        int res = 0;
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (end < s.length()) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) == 1) {
                count++;
            }
            end++;

            if (count > k) {
                char temp = s.charAt(start);
                map.put(temp, map.get(temp) - 1);
                if (map.get(temp) == 0) {
                    count--;
                }
                start++;
            }
            res = Math.max(end - start, res);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct("eceba", 2));
        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));
    }
}
