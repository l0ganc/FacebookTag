import java.util.HashSet;

public class LC266PalindromePermutation {
    /**
     * Given a string, determine if a permutation of the string could form a palindrome.
     *
     * Example 1:
     *
     * Input: "code"
     * Output: false
     * Example 2:
     *
     * Input: "aab"
     * Output: true
     */

    public boolean canPermutePalindrome(String s) {
        // 能经过调换位置形成回文
        // 要么单词中每个字符都出现了两次
        // 要么单词中至于一个字符出现了1次其他的都出现了两次
        // 用HashSet实现
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }

        // 如果set.size() 等于1， 说明只有一个字符出现了一次，其他字符都出现2次
        // 如果set.size() 等于0， 说明每个字符都出现2次
        return set.size() <= 1;
    }
}
