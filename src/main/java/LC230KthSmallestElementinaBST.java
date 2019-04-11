import java.util.Stack;

public class LC230KthSmallestElementinaBST {
    /**
     * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
     *
     * Note:
     * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
     *
     * Example 1:
     *
     * Input: root = [3,1,4,null,2], k = 1
     *    3
     *   / \
     *  1   4
     *   \
     *    2
     * Output: 1
     * Example 2:
     *
     * Input: root = [5,3,6,2,4,null,null,1], k = 3
     *        5
     *       / \
     *      3   6
     *     / \
     *    2   4
     *   /
     *  1
     * Output: 3
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    int count = 0;
    int res = Integer.MIN_VALUE;
    public int r(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    public void traverse(TreeNode root, int k) {
        if (root == null) return;
        traverse(root.left,  k);

        count++;
        if (count == k) res = root.val;

        traverse(root.right, k);
    }


    // 非递归
    public int r2(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        int count = 0;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                if (++count == k) return node.val;
                cur = node.right;
            }
        }
        return Integer.MIN_VALUE;
    }
}
