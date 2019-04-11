public class LC1008ConstructBinarySearchTreefromPreorderTraversal {
    /**
     * Return the root node of a binary search tree that matches the given preorder traversal.
     *
     * (Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)
     *
     *
     *
     * Example 1:
     *
     * Input: [8,5,1,7,10,12]
     * Output: [8,5,10,1,7,null,12]
     */


    /**
     Give the function a bound the maximum number it will handle.
     The left recursion will take the elements smaller than node.val
     The right recursion will take the remaining elements smaller than bound

     Complexity
     bstFromPreorder is called exactly N times.
     It's same as a preorder traversal.
     Time: O(N)
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }
    int i = 0;
    public TreeNode bstFromPreorder(int[] A) {
        return bstFromPreorder(A, Integer.MAX_VALUE);
    }

    public TreeNode bstFromPreorder(int[] A, int bound) {
        if (i == A.length || A[i] > bound) return null;
        TreeNode root = new TreeNode(A[i++]);
        root.left = bstFromPreorder(A, root.val);
        root.right = bstFromPreorder(A, bound);
        return root;
    }
}
