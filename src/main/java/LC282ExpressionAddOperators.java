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
    public  static List<String> addOperators(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        dfs(res, num, target, "", 0, 0, 0);
        return res;
    }

    public static void dfs(List<String> res, String num, int target, String path, long calcVal, long preNum, int index) {
        if (index == num.length()) {
            if (calcVal == target) {
                res.add(path);
                return;
            }
        }

        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(index, i + 1));
            if (index == 0) {
                dfs(res, num, target, path + curNum, curNum, curNum, i + 1);
            } else {
                dfs(res, num, target, path + "+" + curNum, calcVal + curNum, curNum, i + 1);
                dfs(res, num, target, path + "-" + curNum, calcVal - curNum, -curNum, i + 1);
                dfs(res, num, target, path + "*" + curNum, calcVal - preNum + preNum * curNum, preNum * curNum, i + 1);
            }
        }
    }



    // 简化版，只有+ 跟 -
    public  static List<String> addOperators2(String num, int target) {
        if (num == null || num.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        dfs2(res, num, target, "", 0, 0);
        return res;
    }

    public static void dfs2(List<String> res, String num, int target, String path, long calcVal, int index) {
        if (index == num.length()) {
            if (calcVal == target) {
                res.add(path);
                return;
            }
        }

        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(index, i + 1));
            if (index == 0) {
                dfs2(res, num, target, path + curNum, curNum, i + 1);
            } else {
                dfs2(res, num, target, path + "+" + curNum, calcVal + curNum, i + 1);
                dfs2(res, num, target, path + "-" + curNum, calcVal - curNum, i + 1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(addOperators("123", 6));
        System.out.println(addOperators2("123", 6));
    }

}
