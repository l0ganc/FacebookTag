import java.util.*;

public class LC314BinaryTreeVerticalOrderTraversal {
    /**
     * Input: [3,9,20,null,null,15,7]
     *
     *    3
     *   /\
     *  /  \
     *  9  20
     *     /\
     *    /  \
     *   15   7
     *
     * Output:
     *
     * [
     *   [9],
     *   [3,15],
     *   [20],
     *   [7]
     * ]
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }


    /**
     *
     *  Corner cases
     * 1. Input maybe zero
     * 2. The leftest child of root maybe not be leftest of the tree, same with right child
     * ### Solution
     * 1. Find the width of the tree, BFS and insert val to ans according to degree: left -> degree - 1, right -> degree + 1
     *     - Time complexity: O(n)
     *     - Space complexity: O(1)
     * ### Bugs
     * 1. DFS may confused the layer or depth
     * 2. The leftest child of root maybe not be leftest of the tree, same with right child
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> weight = new HashMap<>();
        queue.offer(root);
        weight.put(root, 0);
        int min =  0;

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int w = weight.get(cur);
            if (!map.containsKey(w)) {
                map.put(w, new ArrayList<>());
            }
            map.get(w).add(cur.val);

            if (cur.left != null) {
                weight.put(cur.left, w - 1);
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                weight.put(cur.right, w + 1);
                queue.offer(cur.right);
            }

            min = Math.min(min, w);
        }

        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }

        return res;
    }

}
