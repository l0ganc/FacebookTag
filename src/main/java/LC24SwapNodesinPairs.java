public class LC24SwapNodesinPairs {
    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * You may not modify the values in the list's nodes, only nodes itself may be changed.
     *
     *
     *
     * Example:
     *
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     *
     */

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    // recursive way, time = O(n), space = O(n)
    public ListNode swapPairs(ListNode head) {
        if ((head == null)||(head.next == null))
            return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }

    // iterative way, time = O(n), space = O(1)
    public ListNode swapPairs2(ListNode head) {
        if(head == null)
            return head;
        ListNode newNode = new ListNode(-1);
        ListNode node = newNode;
        while(head != null && head.next != null){
            ListNode second = head.next;
            ListNode newhead = second.next;
            second.next = head;
            node.next = second;
            node = head;
            head = newhead;
        }
        node.next = head;
        return newNode.next;
    }
}
