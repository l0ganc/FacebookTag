public class LC143ReorderList {
    /**
     * Example 1:
     *
     * Given 1->2->3->4, reorder it to 1->4->2->3.
     * Example 2:
     *
     * Given 1->2->3->4->5, reorder it to 1->5->2->4->3.
     *
     * 本题是多个easy题的结合体，reverse list, get middle of list and merge list
     */

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    // 找中点，reverse后半部分，two pointers最后merge一下
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        ListNode l1 = head;

        // 快慢指针找中点
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;   // 断开后半部分与前半部分的联系

        // reverse 后半部分
        ListNode l2 = reverse(slow);
        merge(l1, l2);
    }

    public ListNode reverse(ListNode head) {
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

    public void merge(ListNode l1, ListNode l2) {
        while (l2 != null) {
            ListNode next = l1.next;
            l1.next = l2;
            l1 = l2;
            l2 = next;
        }
    }
}
