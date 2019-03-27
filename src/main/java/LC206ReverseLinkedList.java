public class LC206ReverseLinkedList {
    /**
     * Reverse Linked List
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     */

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    // recursive way

    /**
     * The recursive version is slightly trickier and the key is to work backwards.
     * Assume that the rest of the list had already been reversed,
     * now how do I reverse the front part? Let's assume the list is: n1 → … → nk-1 → nk → nk+1 → … → nm → Ø
     *
     * Assume from node nk+1 to nm had been reversed and you are at node nk.
     *
     * n1 → … → nk-1 → nk → nk+1 ← … ← nm
     *
     * We want nk+1’s next node to point to nk.
     *
     * So,
     *
     * nk.next.next = nk;
     *
     * Be very careful that n1's next must point to Ø.
     * If you forget about this, your linked list has a cycle in it.
     * This bug could be caught if you test your code with a linked list of size 2.
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode p =  reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    // Iterative
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

}
