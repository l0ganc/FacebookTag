public class LC105ConstructBinaryTreefromPreorderandInorderTraversal {
    /**
     * Given preorder and inorder traversal of a tree, construct the binary tree.
     *
     * Note:
     * You may assume that duplicates do not exist in the tree.
     *
     * For example, given
     *
     * preorder = [3,9,20,15,7]
     * inorder = [9,3,15,20,7]
     * Return the following binary tree:
     *
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     *
     */

    /**
     *               3
     *            9    20
     *         15  7
     *     inorder: 15 9 7 3 20   任意节点的下一个节点一定是该节点的右孩子
     *     preorder: 3 9 15 7 20 任意节点的下一个节点一定是该节点的左孩子
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode (int val) {
            this.val = val;
        }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }

        // preorder数组的第一个元素是root
        TreeNode root = new TreeNode(preorder[preStart]);

        // 去inorder数组里找root，root所在元素讲inorder数组一分为二，左半部分是左子树，右半部分是右子树
        int inIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }

        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);  // 构造左子树
        root.right = helper(preStart + (inIndex - inStart) + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}
