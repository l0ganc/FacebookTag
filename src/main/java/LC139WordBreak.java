import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC139WordBreak {
    /**
     *
     Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     determine if s can be segmented into a space-separated sequence of one or more dictionary words.

     Input: s = "leetcode", wordDict = ["leet", "code"]
     Output: true

     Input: s = "applepenapple", wordDict = ["apple", "pen"]
     Output: true

     Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     Output: false
     */

    // dp做，dp[i]表示s从0th ~ i-1th index是不是在字典里，最后只需要返回dp[s.length()]
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = new ArrayList<>();
        wordDict1.add("leet");
        wordDict1.add("code");

        String s2 = "applepenapple";
        List<String> wordDict2 = new ArrayList<>();
        wordDict2.add("apple");
        wordDict2.add("pen");

        String s3 = "catsandog";
        List<String> wordDict3 = new ArrayList<>();
        wordDict3.add("cats");
        wordDict3.add("dog");
        wordDict3.add("sand");
        wordDict3.add("and");
        wordDict3.add("cat");

        System.out.println(wordBreak(s1, wordDict1));
        System.out.println(wordBreak(s2, wordDict2));
        System.out.println(wordBreak(s3, wordDict3));
    }
}
