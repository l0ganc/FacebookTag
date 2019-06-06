import java.util.Stack;

public class LC20ValidParentheses {
    // time = O(n), Space = O(n)
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (c == '}'){
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = ")))))";
        System.out.println(isValid(s));
    }


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
}
