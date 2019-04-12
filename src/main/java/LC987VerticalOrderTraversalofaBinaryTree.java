import java.util.*;

public class LC987VerticalOrderTraversalofaBinaryTree {
    /**
     * Given a binary tree, return the vertical order traversal of its nodes values.
     *
     * For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
     *
     * Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
     *
     * If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
     *
     * Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.
     *
     *
     *
     * Example 1:
     *
     *
     *
     * Input: [3,9,20,null,null,15,7]
     * Output: [[9],[3,15],[20],[7]]
     * Explanation:
     * Without loss of generality, we can assume the root node is at position (0, 0):
     * Then, the node with value 9 occurs at position (-1, -1);
     * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
     * The node with value 20 occurs at position (1, -1);
     * The node with value 7 occurs at position (2, -2).
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    TreeMap<Integer, TreeSet<int[]>> map = new TreeMap<>();  // key存order(即x)，value是<value, level>数组，key跟value都是按照treemap从小到大排序
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, 0, 0);

        for (int i : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            for (int[] j : map.get(i)) {
                list.add(j[0]);
            }
            res.add(list);
        }
        return res;
    }

    public void dfs(TreeNode root, int order, int level) {
        if (root == null) return;
        if (!map.containsKey(order)) {
            map.put(order, new TreeSet<int[]>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]));
        }
        map.get(order).add(new int[]{root.val, level});
        dfs(root.left, order - 1, level + 1);
        dfs(root.right, order + 1, level + 1);
    }
}
