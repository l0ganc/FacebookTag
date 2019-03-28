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

    public static int calculate(String s) {
        // Write your code here
        int len;
        if(s == null || (len = s.length()) == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char sign = '+';
        for(int i = 0; i < len; i ++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if ((!Character.isDigit(s.charAt(i)) && ' ' != s.charAt(i)) || i == len - 1) {
                if(sign == '-') {
                    stack.push(-num);
                }
                if(sign == '+') {
                    stack.push(num);
                }
                if(sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if(sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }

        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }


    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate("3/2"));
        System.out.println(calculate("3+5 / 2"));
    }
}

