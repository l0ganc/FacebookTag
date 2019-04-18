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

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
        System.out.println(removeInvalidParentheses("(())))"));
        System.out.println(" ");

    }
}
