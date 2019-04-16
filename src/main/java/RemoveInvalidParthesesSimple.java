import java.util.Stack;

public class RemoveInvalidParthesesSimple {

    // follow up: LC301: Remove Invalid Parentheses 简化版，只用return 一个valid就行
    public static String removeInvalidParentheses(String input) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty() || input.charAt(stack.peek()) != '(') {
                    stack.push(i);
                } else {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder(input);
        while (!stack.isEmpty()) {
            sb.deleteCharAt(stack.pop());
        }

        return sb.toString();

    }


    public static String balance(String s) {
        if (s == null || s.length() == 0)
            return s;
        int left = 0;
        int right = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isAlphabetic(curr)) {
                sb.append(curr);
            }
            if (curr == '(') {
                left++;
                sb.append('(');
            }
            else if (curr == ')' && left > right) {
                right++;
                sb.append(')');
            }
        }
        s = sb.toString();
        sb = new StringBuilder();
        left = 0;
        right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char curr = s.charAt(i);
            if (Character.isAlphabetic(curr)) {
                sb.append(curr);
            }
            if (curr == ')') {
                right++;
                sb.append(')');
            }
            if (curr == '(' && right > left) {
                left++;
                sb.append('(');
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
        System.out.println(removeInvalidParentheses("(())))"));
        System.out.println(" ");

        System.out.println(balance("()())()"));
        System.out.println(balance("(a)())()"));
        System.out.println(balance(")("));
        System.out.println(balance("(())))"));

    }
}
