public class LC25ReverseNodesInKGroup {
    /**
     *Given this linked list: 1->2->3->4->5
     *
     * For k = 2, you should return: 2->1->4->3->5
     *
     * For k = 3, you should return: 3->2->1->4->5
     */


    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;

        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }

        if (count == k) {
            cur = reverseKGroup(cur, k);
            while (count-- > 0) {
                ListNode next = head.next;
                head.next = cur;
                cur = head;
                head = next;
            }
            head = cur;
        }
        return head;
    }


    // new
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode cur = head;
        int count = 0;

        while (cur != null && count != k) {
            cur = cur.next;
            count++;
        }

        if (count == k) {
            cur = reverseKGroup2(cur, k);
            while (count-- > 0) {
                ListNode next = head.next;
                head.next = cur;
                cur = head;
                head = next;
            }
            head = cur;
        }
        return head;
    }
}
