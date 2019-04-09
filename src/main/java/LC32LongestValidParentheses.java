import java.util.Stack;

public class LC32LongestValidParentheses {
    /**
     * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
     *
     * Example 1:
     *
     * Input: "(()"
     * Output: 2
     * Explanation: The longest valid parentheses substring is "()"
     * Example 2:
     *
     * Input: ")()())"
     * Output: 4
     * Explanation: The longest valid parentheses substring is "()()"
     */

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int res = 0;
        /**
         *  start是valid字符串开始的下标，初始值下标-1是为了防止第一个字符就是有效字符的情况，
         *  例如 s : "(()))"
         */
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        res = Math.max(res, i - start);
                    } else {
                        res = Math.max(res, i - stack.peek());
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
    }
}
