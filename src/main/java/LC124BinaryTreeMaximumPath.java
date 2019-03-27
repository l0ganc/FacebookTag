public class LC124BinaryTreeMaximumPath {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode (int val) {
            this.val = val;
        }

        static int res = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root)  {
            max_gain(root);
            return res;
        }

        private static int max_gain(TreeNode root) {
            if (root == null) return 0;

            // max sum on the left and right sub_trees of node
            int left_gain = Math.max(0, max_gain(root.left));
            int right_gain = Math.max(0, max_gain(root.right));

            // the price to start a new path where `node` is the highest node
            int newPath =  root.val + left_gain + right_gain;

            // update max_sum if it's better to start a new path
            res = Math.max(res, newPath);

            // for recursion: return the max gain if continue the same path
            return root.val + Math.max(left_gain, right_gain);
        }

    }
}
