import java.util.ArrayList;
import java.util.List;

public class LC872LeafSimilarTrees {
    /**
     * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
     *
     *
     *
     * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
     *
     * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
     *
     * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
     */
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }


    // time = O(T1 + T2) where T1 is the number of leaf nodes of root1
    // space = O(T1 + T2)
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        dfs(root1, l1);
        dfs(root2, l2);

        return l1.equals(l2);
    }

    public void dfs(TreeNode root, List<Integer> list) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                list.add(root.val);
            }
            dfs(root.left, list);
            dfs(root.right, list);
        }
    }
}
