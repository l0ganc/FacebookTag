public class LC543DiameterofBinaryTree {
    /**
     * Given a binary tree
     *           1
     *          / \
     *         2   3
     *        / \
     *       4   5
     * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
     *
     * For every node, length of longest path which pass it
     *          = MaxDepth of its left subtree + MaxDepth of its right subtree.
     *
     *  time = O(n), space = O(h)
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

    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        max = Math.max(max, left + right);

        return 1 + Math.max(left, right);
    }



    // follow up: what if the tree is a general tree
    static class Node {
        int val;
        Node[] children;
        public Node(int x, int n) {
            val = x;
            children = new Node[n];
        }
    }

    static int res = 0;

    public int diameterOfBinaryTree2(Node root) {
        if (root == null) {
            return 0;
        }
        maxDepth2(root);
        return res;
    }

    private static int maxDepth2(Node root) {
        if (root == null) {
            return 0;
        }

        int max1 = 0;   // max1是较大值
        int max2 = 0;   // max2是较小值

        // Find top two highest children
        for (Node child : root.children) {
            int h = depthOfTree(child);
            if (h > max1) {
                max2 = max1;
                max1 = h;
            } else if (h > max2) {
                max2 = h;
            }
        }

        // Iterate over each child for diameter
        int maxChildDia = 0;
        for (Node child : root.children) {
            maxChildDia = Math.max(maxChildDia, maxDepth2(child));
        }

        /**
         * The diameter of a tree (sometimes called the width) is the number of nodes on the longest path between two end nodes.
         *
         * The path can either start from one of the node and goes up to one of the LCAs of these nodes
         * and again come down to the deepest node of some other subtree
         *      or
         * can exist as a diameter of one of the child of the current node.
         * The solution will exist in any one of these:
         * I] Diameter of one of the children of the current node
         * II] Sum of Height of highest two subtree + 1
         */
        return Math.max(maxChildDia, 1 + max1 + max2);
    }

    // n-ary树的最大深度
    public static int depthOfTree(Node root) {
        if (root == null) {
            return 0;
        }

        int maxdepth = 0;
        for (Node node : root.children) {
            maxdepth = Math.max(maxdepth, depthOfTree(node));
        }

        return 1 + maxdepth;
    }

}
