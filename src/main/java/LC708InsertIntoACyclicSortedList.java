public class LC708InsertIntoACyclicSortedList {
    /**
     * Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.
     *
     * If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.
     *
     * If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.
     *
     * The following example may help you understand the problem better:
     */

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    };

    public Node insert(Node head, int insertVal) {
        // case 1: head is empty
        if (head == null) {
            Node node = new Node(insertVal, null);
            node.next = node;
            return node;
        }

        Node cur = head;
        while (true) {
            // case 2:   1->2->4->5->1   and insertVal is 3
            if (cur.val < cur.next.val) {
                if (cur.val <= insertVal && insertVal <= cur.next.val) {
                    insertAfter(cur, insertVal);
                    break;
                }
            } else if (cur.val > cur.next.val) {
                // case 3:   1->2->4->5->1   and insertVal is 6
                // case 4:   2->3->4->2      and insertVal is 1
                if (cur.val <= insertVal || insertVal <= cur.next.val) {
                    insertAfter(cur, insertVal);
                    break;
                }
            } else {
                // case 5: all the value is the same
                if (cur.next == head) {
                    insertAfter(cur, insertVal);
                    break;
                }
            }
            cur = cur.next;
        }
        return head;
    }

    public void insertAfter(Node cur, int val) {
        cur.next = new Node(val, cur.next);
    }
}
