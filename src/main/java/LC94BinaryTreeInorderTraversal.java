import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC94BinaryTreeInorderTraversal {
    /**
     * 二叉树中序遍历
     * Complexity Analysis
     *
     * Time complexity : O(n). because the recursive function is T(n) = 2T(n/2)+1
     *
     * Space complexity : The worst case space required is O(n),
     * and in the average case it's O(logn) where n is number of nodes.
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 中序遍历recursive way
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        helper(res, root);
        return res;
    }

    public void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }


    // 非递归写法
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
