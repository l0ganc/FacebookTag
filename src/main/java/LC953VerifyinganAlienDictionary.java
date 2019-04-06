import java.util.HashMap;

public class LC953VerifyinganAlienDictionary {
    /**
     * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * Output: true
     * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
     * Example 2:
     *
     * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
     * Output: false
     * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
     */
    public static boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        for (int i = 1; i < words.length; i++) {
            if (compare(words[i - 1], words[i], map) > 0) {
                return false;
            }
        }

        return true;
    }

    private static int compare(String s, String t, HashMap<Character, Integer> map) {
        int len1 = s.length();
        int len2 = t.length();
        int len = Math.min(len1, len2);

        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (c1 == c2) {
                continue;
            }

            if (map.get(c1) < map.get(c2)) {
                return -1;
            } else {
                return 1;
            }
        }

        if (len1 < len2) {
            return -1;
        } else if (len1 > len2) {
            return 1;
        } else {
            return 0;
        }
    }
}
