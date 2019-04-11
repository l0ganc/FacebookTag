import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC107BinaryTreeLevelOrderTraversalII {
    /**
     * 跟LC102 Binary Tree Level Order Traversal一样的etiquette
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    //Complexity Analysis
    //Time complexity : O(N) since each node is processed exactly once.
    //Space complexity : O(N) to keep the output structure which contains N node values.

    // BFS
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                list.add(cur.val);
            }
            res.add(0, list);
        }
        return res;
    }

    // DFS
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Helper(res, root, 0);
        return res;
    }
    public void Helper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height >= res.size()) {
            res.add(0, new ArrayList<>());
        }
        res.get(res.size() - 1 - height).add(root.val);
        Helper(res, root.left, height + 1);
        Helper(res, root.right, height + 1);
    }
}
