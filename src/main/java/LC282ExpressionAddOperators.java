import java.util.ArrayList;
import java.util.List;

public class LC282ExpressionAddOperators {
    /**
     * Given a string that contains only digits 0-9 and a target value,
     * return all possibilities to add binary operators (not unary) +, -, or * between the digits
     * so they evaluate to the target value.
     *
     * Input: num = "123", target = 6
     * Output: ["1+2+3", "1*2*3"]
     */


    /**
     * When we use dfs to do this question, the most tricky part is that how to deal with multiplication. For every
     * addition and subtraction, we just directly adding or subtracting the new number. However, for multiplication,
     * we should multiply current number and previous number firstly, and then add previous previous number.
     * So we can use a variable preNum to record every previous number in each recursion step. If current recursive
     * call is trying multiplication, we should use previous calculation value subtract previous number, and then
     * adding multiplication result between previous number and current number.
     * */

    /**
     * from second digit, we can choose
     *  + , - , * and empty space (in this case, eg. 12 was treated as one number),
     *  in total four kinds of choices.
     *  Also, remember there is a for loop inside every call stack, So, for the call stack of length N,
     * Total time complexity should be N * 4^(N - 1)
     *
     * space complexity is : O(N)
     */
    public static List<String> addOperators(String num, int target) {
        if (num.length() == 0) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        dfs(result, num, target, "", 0, 0, 0);
        return result;
    }

    /**
     * @param result: result list to store valid expressions
     * @param num: input num candidates
     * @param target: input target number
     * @param expr: current expression string
     * @param calcVal: current calculation value
     * @param preNum: previous number, in order to multiply current number if we want to put * between preNum and curNum
     * @param pos: current index in the input num array
     * */
    public void dfs(List<String> result, String num, int target, String expr, long calcVal, long preNum, int pos) {
        if (pos == num.length()) {
            if (calcVal == target) {
                result.add(expr);
            }
            return;
        }

        // start from first index of current position in num string, try all possible length of nums
        for (int i = pos; i < num.length(); i++) {
            // corner case: if current position is 0, we can only use it as a single digit number, should be 0
            // if it is not a single digit number with leading 0, it should be considered as an invalid number
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(pos, i + 1));

            // position 0 should be considered individually, since it does not have any operand character before curNum
            if (pos == 0) {
                dfs(result, num, target, expr + curNum, curNum, curNum, i + 1);
            }
            else {
                dfs(result, num, target, expr + "+" + curNum, calcVal + curNum, curNum, i + 1);
                dfs(result, num, target, expr + "-" + curNum, calcVal - curNum, -curNum, i + 1);
                // trick part: to calculate multiplication, we should subtract previous number, and then add current
                // multiplication result to the subtraction result
                dfs(result, num, target, expr + "*" + curNum, calcVal - preNum + preNum * curNum, preNum * curNum, i + 1);
            }
        }
    }





    // 简化版，只有+ 跟 -
    // time = O(N * 2 ^(N - 1))
    public  static List<String> addOperators2(String s, int target) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        dfs2(res, s, target, "", 0, 0);
        return res;
    }

    public static void dfs2(List<String> res, String s, int target, String path, long calcVal, int index) {
        if (index == s.length()) {
            if (calcVal == target) {
                res.add(path);
                return;
            }
        }

        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(index) == '0') {
                break;
            }
            long curNum = Long.parseLong(s.substring(index, i + 1));
            if (index == 0) {
                dfs2(res, s, target, path + curNum, curNum, i + 1);
            } else {
                dfs2(res, s, target, path + "+" + curNum, calcVal + curNum, i + 1);
                dfs2(res, s, target, path + "-" + curNum, calcVal - curNum, i + 1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(addOperators("123", 6));
        System.out.println(addOperators2("123", 6));
        System.out.println(addOperators2("232", 8));
        System.out.println(addOperators2("105", 5));
        System.out.println(addOperators2("00", 0));
        System.out.println(addOperators2("3456237490", 9191));
    }

}
