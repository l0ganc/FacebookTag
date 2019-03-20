import java.util.Stack;

public class LC173BinarySearchTreeIterator {
    /**
     * 用栈来实现，
     * next() and hasNext() should run in average O(1) time and uses O(h) memory,
     * where h is the height of the tree.
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    Stack<TreeNode> stack;
    public LC173BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        for (TreeNode tmp = root; tmp != null; tmp = tmp.left) {
            stack.push(tmp);
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = stack.pop();
        for (TreeNode tmp = cur.right; tmp != null; tmp = tmp.left) {
            stack.push(tmp);
        }
        return cur.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
