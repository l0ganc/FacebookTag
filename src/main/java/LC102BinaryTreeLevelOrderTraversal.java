import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC102BinaryTreeLevelOrderTraversal {
    /**
     *
     Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

     For example:
     Given binary tree [3,9,20,null,null,15,7],
     3
     / \
     9  20
     /  \
     15   7
     return its level order traversal as:
     [
     [3],
     [9,20],
     [15,7]
     ]
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
            res.add(list);
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
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        Helper(res, root.left, height + 1);
        Helper(res, root.right, height + 1);
    }



}
