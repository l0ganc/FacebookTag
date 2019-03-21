import com.sun.xml.internal.bind.v2.TODO;

public class LC426ConvertBinarySearchTreeToSortedDoublyLinkedList {
    /**
     * Convert a BST to a sorted circular doubly-linked list in-place.
     * Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.
     *
     *      step1: inorder traversal by recursion to connect the original BST
     *      step2: connect the head and tail to make it circular
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

    static Node prev = null;
    public Node c(Node root) {
        if (root == null) {
            return null;
        }
        Node dummy = new Node(0, null, null);
        prev = dummy;

        // step1: inorder traversal
        inorder(root);

        // step2: connect head and tail
        prev.right = dummy.right;
        dummy.right.left = prev;

        return dummy.right;
    }

    private static void inorder(Node cur) {
        if (cur == null) {
            return;
        }

        inorder(cur.left);
        prev.right = cur;
        cur.left = prev;
        prev = cur;
        inorder(cur.right);
    }


    // follow up: inplace linked list to bst要求depth最小
    // https://www.geeksforgeeks.org/in-place-conversion-of-sorted-dll-to-balanced-bst/
    // TODO: 待完善

}
