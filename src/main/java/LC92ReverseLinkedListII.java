public class LC92ReverseLinkedListII {
    /**
     * Reverse a linked list from position m to n. Do it in one-pass.
     *
     * Note: 1 ≤ m ≤ n ≤ length of list.
     *
     * Example:
     *
     * Input: 1->2->3->4->5->NULL, m = 2, n = 4
     * Output: 1->4->3->2->5->NULL
     */

    class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode cur = dummy.next;

        /**      head
         *prev     1 -> 2 -> 3 -> 4 -> 5 -> NULL1
         *dummy   cur
         *        prev
         *             cur
         */
        for (int i = 1; i < m; i++) {  // for循环后cur指向需要翻转的最开始元素， prev指向head
            cur = cur.next;
            prev = prev.next;
        }

        // 翻转链表
        for (int i = 0; i < n - m; i++) {
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }
        return dummy.next;
    }
}
