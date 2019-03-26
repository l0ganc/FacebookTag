public class LC270ClosestBinarySearchTreeValue {
    /**
     * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
     *
     *   Input: root = [4,2,5,1,3], target = 3.714286
     *
     *     4
     *    / \
     *   2   5
     *  / \
     * 1   3
     *
     * Output: 4
     *
     */

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static int closestValue(TreeNode root, double target) {
        int res =  root.val;
        while (root != null) {
            if (Math.abs(target - root.val) < Math.abs(target - res)) {
                res =  root.val;
            }
            root = root.val > target ? root.left : root.right;
        }
        return res;
    }
}
