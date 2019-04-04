import java.util.HashMap;
import java.util.Map;

//Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
//
// Example 1:
//
//Input:s1 = "ab" s2 = "eidbaooo"
//Output:True
//Explanation: s2 contains one permutation of s1 ("ba").
//
//
//
// Example 2:
//
//Input:s1= "ab" s2 = "eidboaoo"
//Output: False
//
//
//
// Note:
//
// The input strings only contain lower case letters.
// The length of both given strings is in range [1, 10,000].
//
//
public class LC567PermutationInString {
    // sliding window 又一经典题，O(n) time and O(n) space
    public static boolean checkInclusion(String s1, String s2){
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int start = 0, end = 0;
        int len = s1.length();
        int count = map.size();

        while (end < s2.length()) {
            char c = s2.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    count--;
                }
            }

            while (count == 0) {
                if (end - start + 1 == len) {
                    return true;
                }
                char temp = s2.charAt(start);
                if (map.containsKey(temp)) {
                    map.put(temp, map.get(temp) + 1);
                    if (map.get(temp) > 0) {
                        count++;
                    }
                }
                start++;
            }
            end++;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }
}
