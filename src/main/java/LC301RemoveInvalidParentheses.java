import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 *
 * 脸家常考的题就是只用返回一个结果就行，地里说用twoscan做
 */
public class LC301RemoveInvalidParentheses {
    public static List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null) {
            return res;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(s);
        queue.offer(s);
        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                res.add(s);
                found = true;
            }
            if (found) {
                continue;
            }

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                    continue;
                }
                String next = s.substring(0, i) + s.substring(i + 1);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        return res;
    }

    private static boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                count++;
            }
            if (s.charAt(i) == ')' && count-- == 0) {
                return false;
            }
        }
        return count == 0;
    }

    public static void main(String[] args) {
        String s1 = "()())()";
        String s2 = "(a)())()";
        String s3 = ")(";
        System.out.println(removeInvalidParentheses(s1));
        System.out.println(removeInvalidParentheses(s2));
        System.out.println(removeInvalidParentheses(s3));

        System.out.println(removeInvalidParenthesesOnlyOne(s1));
        System.out.println(removeInvalidParenthesesOnlyOne(s2));
        System.out.println(removeInvalidParenthesesOnlyOne(s3));
    }

    // only return the first res
    public static String removeInvalidParenthesesOnlyOne(String s) {
        if (s == null) {
            return "";
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(s);
        queue.offer(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                return s;

            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                    continue;
                }
                String next = s.substring(0, i) + s.substring(i + 1);
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.offer(next);
                }
            }
        }
        return "";
    }
}
