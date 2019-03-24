import java.util.List;

public class LC339NestedListWeightSum {
    /**
     * Input: [[1,1],2,[1,1]]
     * Output: 10
     * Explanation: Four 1's at depth 2, one 2 at depth 1.
     *
     * Input: [[1,1],2,[1,1]]
     * Output: 10
     * Explanation: Four 1's at depth 2, one 2 at depth 1.
     *
     * recursive dfs
     */


    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {
//        // Constructor initializes an empty nested list.
//        public NestedInteger();
//
//        // Constructor initializes a single integer.
//        public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }


    public int depthSum(List<NestedInteger> nestedList) {
        return dfsSum(nestedList, 1);
    }

    private static int dfsSum(List<NestedInteger> list, int depth) {
        int sum = 0;
        for (NestedInteger n : list) {
            if (n.isInteger())  {
                sum += n.getInteger() * depth;
            } else {
                sum += dfsSum(list, depth + 1);
            }
        }
        return sum;
    }


    // 变种：给了⼀个嵌套list⽐如 [1,5,[3,4],2,7,[5,[8],3]]
    //      要求输出字符串： 1+5+(3+4)* 2+2+7+(5+(8)*3+3)*2
    //      这个input的嵌套结构让⾃⼰设计
    public int depthSum2(List<NestedInteger> nestedList) {
        return dfsSum2(nestedList, 1);
    }
    public static int dfsSum2(List<NestedInteger> lists, int depth) {
        int sum = 0;
        for (int i = 0; i < lists.size(); i++) {
            NestedInteger cur = lists.get(i);

            if (depth > 1 && i == 0) {
                System.out.print("(");;
            }
            if (i > 0) {
                System.out.print("+");
            }

            if (cur.isInteger()) {
                sum += cur.getInteger() * depth;
                System.out.print(cur.getInteger());
            } else {
                sum += dfsSum2(lists, depth + 1);
            }

            if (depth> 1 && i == lists.size() - 1) {
                System.out.print(")*" + depth);
            }
        }
        return sum;
    }


    /**
     * Complexity Analysis
     *
     * The algorithm takes O(N) time, where N is the total number of nested elements in the input list.
     *      For example, the list [ [[[[1]]]], 2 ] contains 4 nested lists and 2 nested integers (1 and 2), so N=6.
     *
     * In terms of space, at most O(D) recursive calls are placed on the stack,
     *      where D is the maximum level of nesting in the input.
     *            For example, D=2 for the input [[1,1],2,[1,1]], and D=3 for the input [1,[4,[6]]].
     */
}
