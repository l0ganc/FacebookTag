import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LC636ExclusiveTimeOfFunctions {
    /**
     * Input:
     * n = 2
     * logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
     * Output: [3, 4]
     * Explanation:
     * Function 0 starts at the beginning of time 0, then it executes 2 units of time and reaches the end of time 1.
     * Now function 1 starts at the beginning of time 2, executes 4 units of time and ends at time 5.
     * Function 0 is running again at the beginning of time 6, and also ends at the end of time 6, thus executing for 1 unit of time.
     * So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
     */
    public static int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stack = new Stack<>();  // used to stored id
        int prev = 0;

        for (String log : logs) {
            String[] str = log.split(":");
            int id = Integer.parseInt(str[0]);
            int curr = Integer.parseInt(str[2]);

            if (str[1].equals("start")) {
                if (!stack.isEmpty()) {
                    res[stack.peek()] += curr - prev;
                }
                stack.push(id);
                prev = curr;
            } else {
                // curr is end of current interval, belong to current interval. That's why we have +1 here
                res[stack.pop()] += curr - prev + 1;

                // prev means the start of next interval, so we need to +1
                prev = curr + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 2;
        List<String> logs = new ArrayList<>();
        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:6");
        System.out.println(Arrays.toString(exclusiveTime(n, logs)));
    }

}
