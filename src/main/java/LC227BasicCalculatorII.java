import java.util.ArrayList;
import java.util.Stack;

public class LC227BasicCalculatorII {
    /**
     * Example 1:
     *
     * Input: "3+2*2"
     * Output: 7
     * Example 2:
     *
     * Input: " 3/2 "
     * Output: 1
     * Example 3:
     *
     * Input: " 3+5 / 2 "
     * Output: 5
     *
     * 通过栈来实现运算，按顺序读取字符串，将第一个数入栈。
     *      之后遇到+，将下一个数num入栈；
     *      遇到-，则将-num入栈；
     *      遇到乘或除，先将上一个数出栈，与当前数进行运算后，再将结果入栈。
     * 读取完整个字符串后，将栈中所有的数相加即运算结果。
     *
     */

    // time = O(n), space = O(n)
    public static int calculate(String s) {
        // Write your code here
        int num = 0;
        char prevSign = '+';
        int res = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) -'0';
                    i++;
                }
            }

            if (!Character.isDigit(i) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (prevSign == '+') {
                    stack.push(num);
                }
                if (prevSign == '-') {
                    stack.push(-num);
                }
                if (prevSign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (prevSign == '/') {
                    stack.push(stack.pop() / num);
                }
                prevSign = s.charAt(i);
                num = 0;
            }
        }

        for (int i : stack) {
            res += i;
        }
        return res;
    }


    // 不用stack，O(n) time and O(1) space
    public static int calculate2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        s = s.trim();
        long prevNum = 0;
        int sum = 0;
        char prevSign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                int val = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    val = val * 10 + s.charAt(i + 1) - '0';
                    i++;
                }

                if (prevSign == '+') {
                    sum += prevNum;
                    prevNum = val;
                } else if (prevSign == '-') {
                    sum += prevNum;
                    prevNum = -val;
                } else if (prevSign == '*') {
                    prevNum = prevNum * val;
                } else if (prevSign == '/') {
                    prevNum = prevNum / val;
                }
            } else {
                prevSign = c;
            }
        }

        sum += prevNum;
        return sum;
    }



    // fb常考题，简化版没有空格，只有数字，'+', '*', 要求O(1)空间
    public static int calculate3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        long prevNum = 0;
        int sum = 0;
        char prevOp = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int val = c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    val = val * 10 + s.charAt(i + 1) - '0';
                    i++;
                }

                if (prevOp == '+') {
                    sum += prevNum;
                    prevNum = val;
                } else if (prevOp == '*') {
                    prevNum = prevNum * val;
                }
            } else {
                prevOp = c;
            }
        }
        sum += prevNum;
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate("3/2"));
        System.out.println(calculate("3+5 / 2"));
        System.out.println(calculate2("3+2*2"));
        System.out.println(calculate2("3/2"));
        System.out.println(calculate2("3+5 / 2"));
        System.out.println(calculate2("3 * 2 + 4 - 5 / 2"));
        System.out.println(calculate2(" 3+5 / 2 "));
        System.out.println("       隔开        ");
        System.out.println(calculate3("3+2*2"));
        System.out.println(calculate3("3+2*2+4+3+3*3*3+2"));
    }
}

