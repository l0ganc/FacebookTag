package ProductionEngineer;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    /**
     * Convert a BST to a sorted circular doubly-linked list in-place.
     * Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
     *
     *      step1: inorder traversal by recursion to connect the original BST
     *      step2: connect the head and tail to make it circular
     *
     *    time complexity = O(n)
     *    space complexity = O(n)
     */

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };

    Node prev = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node dummy = new Node(0, null, null);
        prev = dummy;

        //step1: inorder
        inorder(root);

        // step2: connect tail and head
        prev.right = dummy.right;
        dummy.right.left = prev;

        return dummy.right;
    }

    public void inorder(Node cur) {
        if (cur == null) {
            return;
        }

        inorder(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        inorder(cur.right);
    }

}
