import java.util.Stack;

public class LC98ValidateBinarySearchTree {
    /**
     * Given a binary tree, determine if it is a valid binary search tree (BST).
     */

     // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }



    // 常规的recursive做，time = O(n), space = O(h)
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root, null, null);
    }
    public static boolean check(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min || max != null && root.val >= max) {
            return false;
        }
        return check(root.left, min, root.val) && check(root.right, root.val, max);
    }


    // inorder做
    static TreeNode prev;
    public boolean isValidBST2(TreeNode root) {
        return inorder(root);
    }
    private static boolean inorder(TreeNode root)  {
        if (root == null) {
            return true;
        }

        if (!inorder(root.left)) {
            return false;
        }

        if (prev != null && prev.val >= root.val) {
            return false;
        }

        prev = root.right;
        return inorder(root.right);
    }

    // 用stack来做 iterative inorder traversal
    public boolean isValidBST3(TreeNode root) {
        if (root == null) {
            return true;
        }

        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if (prev != null && prev.val >= root.val) {
                return false;
            }

            prev = root;
            root = root.right;
        }
        return false;
    }
}

