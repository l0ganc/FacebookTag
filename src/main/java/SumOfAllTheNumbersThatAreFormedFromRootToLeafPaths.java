public class SumOfAllTheNumbersThatAreFormedFromRootToLeafPaths {
    /**
     * Given a binary tree, where every node value is a Digit from 1-9
     * Find the sum of all the numbers which are formed from root to leaf paths.
     *
     *           6
     *        /      \
     *      3          5
     *    /   \          \
     *   2     5          4
     *       /   \
     *      7     4
     *   There are 4 leaves, hence 4 root to leaf paths:
     *    Path                    Number
     *   6->3->2                   632
     *   6->3->5->7               6357
     *   6->3->5->4               6354
     *   6->5>4                    654
     * Answer = 632 + 6357 + 6354 + 654 = 13997
     *
     * The idea is to do a preorder traversal of the tree.
     * In the preorder traversal,
     * keep track of the value calculated till the current node,
     * let this value be val.
     * For every node, we update the val as val*10 plus node’s data.
     */

    static class Node
    {
        int data;
        Node left, right;

        Node(int item)
        {
            data = item;
            left = right = null;
        }
    }

    // time = O(n), space = O(h)
    // A wrapper function over treePathsSumUtil()
    public int treePathsSum(Node root)
    {
        // Pass the initial value as 0 as there is nothing above root
        return treePathsSumUtil(root, 0);
    }

    public int treePathsSumUtil(Node node, int val) {
        // base case
        if (node == null) {
            return 0;
        }

        // update val
        val = val * 10 + node.data;

        // if current node is leaf, return the current value of val
        if (node.left == null && node.right == null) {
            return val;
        }

        return treePathsSumUtil(node.left, val) + treePathsSumUtil(node.right, val);
    }


}
