import java.util.*;

public class LC199BinaryTreeRightSideView {
    /**
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
     *
     * Example:
     *
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     * Explanation:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode (int val) {
            this.val = val;
        }
    }

    // DFS way, time = O(n), space = O(n)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Helper(root, res, 0);
        return res;
    }
    private void Helper(TreeNode root, List<Integer> res, int depth) {
        if (root == null) {
            return;
        }

        if (depth == res.size()) {
            res.add(root.val);
        }

        Helper(root.right, res, depth + 1);
        Helper(root.left, res, depth + 1);
    }

    // bfs, iterative way. time = O(n), space = O(n)
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == size - 1) {
                    res.add(cur.val);
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }


}
