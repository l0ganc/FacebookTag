public class LC606ConstructStringfromBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    };

    /**
     * Input: Binary tree: [1,2,3,4]
     *        1
     *      /   \
     *     2     3
     *    /
     *   4
     *
     * Output: "1(2(4))(3)"
     *
     * Explanation: Originallay it needs to be "1(2(4)())(3()())",
     * but you need to omit all the unnecessary empty parenthesis pairs.
     * And it will be "1(2(4))(3)".
     */

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }

        String s = t.val + "";
        String left = tree2str(t.left);
        String right = tree2str(t.right);

        // case 0
        if (t.left == null && t.right == null) {
            return s;
        }

        // case 1: s(l)
        if (t.right == null) {
            return s + "(" + right + ")";
        }

        // general case : s(l)(r)
        return s + "(" + left + ")" + "(" + right + ")";
    }
}
