public class BinarySearchTreeIteratorWithParentNode {
    /**
     * LC173变种，binary search tree without stack but can use parent node
     */
    static class Node {
        int val;
        Node left;
        Node right;
        Node parent;
        public Node(int val) {
            this.val = val;
            left = null;
            right = null;
            parent = null;
        }
    }

    Node last = null;  // last is mark the most recent visited node
    public BinarySearchTreeIteratorWithParentNode(Node root) {
        if (root == null) {
            return;
        }
        last = root;
        while (last != null) {
            last = last.left;
        }

    }

    /** @return the next smallest number */
    public int next() {
        Node cur = last;
        last = findSuccessor(last);
        return cur.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return last != null;
    }

    /**
     * 1) If right subtree of node is not NULL, then succ lies in right subtree. Do following.
     *      Go to right subtree and return the node with minimum key value in right subtree.
     * 2) If right sbtree of node is NULL, then succ is one of the ancestors. Do following.
     *      Travel up using the parent pointer until you see a node which is left child of it’s parent.
     *      The parent of such a node is the succ.
     * Time = O(h)
     */
    private static Node findSuccessor(Node n) {
        if (n == null) {
            return null;
        }
        if (n.right != null) {
            // 找最小就是找右子树的最左节点，因为是BST
            Node cur = n;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }

        Node p = n.parent;
        while (p != null && n == p.right) {
            n = p;
            p = p.parent;
        }
        return p;
    }


    /**
     * Method 2 (Search from root)
     * Parent pointer is NOT needed in this algorithm.
     * The Algorithm is divided into two cases on the basis of right subtree of the input node being empty or not.
     *
     * Input: node, root // node is the node whose Inorder successor is needed.
     * output: succ // succ is Inorder successor of node.
     *
     * 1) If right subtree of node is not NULL, then succ lies in right subtree. Do following.
     *      Go to right subtree and return the node with minimum key value in right subtree.
     * 2) If right sbtree of node is NULL, then start from root and us search like technique. Do following.
     *      Travel down the tree, if a node’s data is greater than root’s data then go right side, otherwise go to left side.
     */
    private static Node findSuccessor2(Node root, Node n) {
        Node succ = null;
        if (n.right != null) {
            // 找最小就是找右子树的最左节点，因为是BST
            Node cur = n;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        }

        while (root != null) {
            if (n.val < root.val) {
                succ = root;
                root = root.left;
            } else if (n.val > root.val){
                root = root.right;
            } else {
                break;
            }
        }
        return succ;
    }


}
