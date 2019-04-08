public class LC536ConstructBinaryTreefromString {
    /**
     * You need to construct a binary tree from a string consisting of parenthesis and integers.
     *
     * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
     *
     * You always start to construct the left child node of the parent first if it exists.
     *
     * Example:
     * Input: "4(2(3)(1))(6(5))"
     * Output: return the tree root node representing the following tree:
     *
     *        4
     *      /   \
     *     2     6
     *    / \   /
     *   3   1 5
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }


    static int i = 0;
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        return helper(s.toCharArray());
    }

    private TreeNode helper(char[] s) {
        // 递归终止条件
        if (i == s.length) {
            return null;
        }

        // 处理数字的情况,'-'表示负号，s中只可能有'0'~'9'、'-'、'('、')'
        StringBuilder sb = new StringBuilder();
        while (i < s.length && s[i] != '(' && s[i] != ')') {
            sb.append(s[i++]);
        }

        // create new node
        TreeNode node = null;
        if (sb.length() != 0) {
            node = new TreeNode(Integer.valueOf(sb.toString()));
        }

        // 检查是左孩子还是右孩子
        if (i < s.length && s[i] == '(') {
            // 开始造左孩子
            i++;
            node.left = helper(s);
            i++;

            // 开始造右孩子
            if (i < s.length && s[i] == ')') {
                i++;
                node.right = helper(s);
                i++;
            }
        }
        return node;
    }
}
