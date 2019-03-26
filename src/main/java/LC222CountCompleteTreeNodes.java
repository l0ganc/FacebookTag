public class LC222CountCompleteTreeNodes {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 给定一个完全二叉树(complete tree)跟一个index，判断这个index所在节点是否存在
    /**
     * 比如给的index=15, 因为是完全二叉树，编号从1开始，可以用二进制来做
     * 15的二进制是1111，15一直除以2，得到7(111), 3(11), 1(1)
     *
     *                   1
     *                 /  \
     *                2   3
     *               /\  / \
     *              4 5  6 7
     *            / \ / \/\ / \
     *            89 .......
     *
     *  从 1开始，二进制去掉最高位后剩下的，如果是1就是往右走，是0就是往左走。
     *  15一路除以2得到1，是根节点，15的二进制是1111，去掉最高位是111，说明从1开始
     *  一直是右右右这样走，就是判断root.right, root.right.right,  root.right.right是不是存在
     *  时间复杂度是O(logn)
     *
     */



    // follow up: 给一个完全二叉树，计算出节点个数
    /**
     * 首先计算出树高，然后计算root.right的树高，如果这两个相差1，说明是满二叉树，用2^h - 1计算；
     * 如果相差不是1，说明最后一层没有满，但是倒数第二层是满的
     * 求树高是O(logn)， 计算节点又是一个logn，总的时间是 O((logn)^2)
     */
    public static int countNodes(TreeNode root)  {
        int h = height(root);
        if (h < 0) return 0;
        if (height(root.right) + 1 == h) {
            return (1 << h) + countNodes(root.right);
        }
        return (1 << (h - 1)) + countNodes(root.left);
    }
    // 计算满二叉树的树高
    private static int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return 1 + height(root.left);
    }


    // iterative version
    public int countNodes2(TreeNode root) {
        int nodes = 0;
        int h = height(root);

        while (root != null) {
            if (height(root.right) + 1 == h) {
                nodes += (1 << h);
                root = root.right;
            } else {
                nodes += (1 << (h - 1));
                root = root.left;
            }
            h--;
        }
        return nodes;
    }
}
