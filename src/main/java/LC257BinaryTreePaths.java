import java.util.ArrayList;
import java.util.List;

public class LC257BinaryTreePaths {
    /**
     * Given a binary tree, return all root-to-leaf paths.
     *
     * Note: A leaf is a node with no children.
     *
     * Example:
     *
     * Input:
     *
     *    1
     *  /   \
     * 2     3
     *  \
     *   5
     *
     * Output: ["1->2->5", "1->3"]
     *
     * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
     *
     * time = O(N), space = O(N)
     *
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode (int val) {
            this.val = val;
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        helper(root, res, "");
        return res;
    }

    private static void helper(TreeNode node, List<String> res, String path) {
        if (node.left == null &&  node.right == null) {
            res.add(path + node.val);
            return;
        }

        if (node.left != null) {
            helper(node.left, res, path + node.val + "->");
        }

        if (node.right != null) {
            helper(node.right, res, path + node.val + "->");
        }
    }

    public static void main(String[] args) {
        System.out.println("" + 1 + "->");
    }
}
