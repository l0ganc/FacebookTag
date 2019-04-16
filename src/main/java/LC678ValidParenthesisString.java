import java.util.Stack;

public class LC678ValidParenthesisString {
    /**
     * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
     *
     * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
     * Any right parenthesis ')' must have a corresponding left parenthesis '('.
     * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
     * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
     * An empty string is also valid.
     * Example 1:
     * Input: "()"
     * Output: True
     * Example 2:
     * Input: "(*)"
     * Output: True
     * Example 3:
     * Input: "(*))"
     * Output: True
     */

    /**
     * 思路：
     * 两个栈，
     * The basic idea is to track the index of the left bracket and star position.
     * Push all the indices of the star and left bracket to their stack respectively.
     * STEP 1
     *      Once a right bracket comes, pop left bracket stack first if it is not empty.
     *      If the left bracket stack is empty, pop the star stack if it is not empty.
     *      A false return can be made provided that both stacks are empty.
     *
     * STEP 2
     *      Now attention is paid to the remaining stuff in these two stacks.
     *      Note that the left bracket CANNOT appear after the star
     *          as there is NO way to balance the bracket.
     *          In other words, whenever there is a left bracket index appears after the Last star,
     *          a false statement can be made.
     *          Otherwise, pop out each from the left bracket and star stack.
     *
     * STEP 3
     * A correct sequence should have an empty left bracket stack.
     * You don't need to take care of the star stack.
     *
     * Final Remarks:
     * Greedy algorithm is used here.
     * We always want to use left brackets to balance the right one first as the * symbol is a wild card.
     * There is probably an O(1) space complexity but I think this is worth mentioning.
     *
     */

    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }

        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> starStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftStack.push(i);
            } else if (c == '*') {
                starStack.push(i);
            } else {
                if (leftStack.isEmpty() && starStack.isEmpty()) {
                    return false;
                }

                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else {
                    starStack.pop();
                }
            }
        }

        while (!leftStack.isEmpty() && !starStack.isEmpty()) {
            if (leftStack.pop() > starStack.pop()) {
                return false;
            }
        }

        return leftStack.isEmpty();
    }

    public static void main(String[] args) {
        LC678ValidParenthesisString obj = new LC678ValidParenthesisString();
        System.out.println(obj.checkValidString("()"));
        System.out.println(obj.checkValidString("(*)"));
        System.out.println(obj.checkValidString("(*))"));
    }
}
