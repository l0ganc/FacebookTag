import java.util.*;

public class LC236LowestCommonAncestorOfaBinaryTree {
    /**
     * 两种方法，递归跟map
     */

     static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
     }

    // 递归 time: O(N) space: O(N)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left == null ? right : left;
    }


    // Iterative using parent pointers : O(N) space: O(N)
    /**
     * Start from the root node and traverse the tree.
     * Until we find p and q both, keep storing the parent pointers in a dictionary.
     * Once we have found both p and q, we get all the ancestors for p using the parent dictionary and add to a set called ancestors.
     * Similarly, we traverse through ancestors for node q.
     *      If the ancestor is present in the ancestors set for p,
     *      this means this is the first ancestor common between p and q (while traversing upwards) and
     *      hence this is the LCA node.
     */

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        // Stack for tree traversal
        Deque<TreeNode> stack = new ArrayDeque<>();

        // HashMap for parent pointers
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        stack.push(root);

        // Iterate until we find both the nodes p and q
        while (!parent.containsKey(p) || !parent.containsKey(q)) {
            TreeNode cur = stack.pop();

            // While traversing the tree, keep saving the parent pointers.
            if (cur.left != null) {
                parent.put(cur.left, cur);
                stack.push(cur.left);
            }
            if (cur.right != null) {
                parent.put(cur.right, cur);
                stack.push(cur.right);
            }
        }

        // Ancestors set() for node p.
        Set<TreeNode> ancestors = new HashSet<>();

        // Process all ancestors for node p using parent pointers.
        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        // The first ancestor of q which appears in
        // p's ancestor set() is their lowest common ancestor.
        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }
        return q;
    }

}
