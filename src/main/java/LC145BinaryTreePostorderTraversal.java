import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC145BinaryTreePostorderTraversal {
    /**
     * /*
     *     二叉树
     *     后序遍历
     *     左节点->右节点->根节点
     *
     *
     *
     *     O（n）
     *     preorder(root):
     *        if not root : return
     *        print(root->val);
     *        preorder(root->left);
     *        preorder(root->right);
     *
     *     O（n）
     *     inorder(root):
     *        if not root : return
     *        inorder(root->left);
     *        print(root->val);
     *        inorder(root->right);
     *
     *     O（n）
     *     postorder(root):
     *        if not root : return
     *        postorder(root->left);
     *        postorder(root->right);
     *        print(root->val);
     *
     *
     *
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    // 递归写法
    public List<Integer> postorderTraversal(TreeNode root) {
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
        helper(res, root.right);
        res.add(root.val);
    }

    // 非递归写法
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.add(0, cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        return res;
    }
}
