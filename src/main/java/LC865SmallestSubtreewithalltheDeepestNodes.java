public class LC865SmallestSubtreewithalltheDeepestNodes {
    /**
     * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
     *
     * A node is deepest if it has the largest depth possible among any node in the entire tree.
     *
     * The subtree of a node is that node, plus the set of all descendants of that node.
     *
     * Return the node with the largest depth such that it contains all the deepest nodes in its subtree.
     *
     *
     *
     * Example 1:
     *
     * Input: [3,5,1,6,2,0,8,null,null,7,4]
     * Output: [2,7,4]
     * Explanation:
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    class Pair {  // Pair中depth表示深度，node表示需要返回的节点
        int depth;
        TreeNode node;
        public Pair(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return deep(root).node;
    }

    public Pair deep(TreeNode root) {
        if (root == null) return new Pair(0, null);

        Pair left = deep(root.left);
        Pair right = deep(root.right);

        if (left.depth == right.depth) return new Pair(left.depth + 1, root);   // 加1是因为此刻左子树深度大于右，说明结果在左边，又因为最后需要得出的是root节点，因此加1
        if (left.depth > right.depth) return new Pair(left.depth + 1, left.node);

        return new Pair(right.depth + 1, right.node);
    }


}
