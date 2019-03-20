import java.util.PriorityQueue;

public class LC21MergeTwoSortedLists {
    /**
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     *
     * Example:
     *
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode (int x) {
            val = x;
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                dummy.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                dummy.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            dummy = dummy.next;
        }

        if (l1 != null) {
            dummy.next = l1;
        }
        if (l2 != null) {
            dummy.next = l2;
        }
        return head.next;
    }

    // LC23: Merge k Sorted Lists
    /**
     * 可以用divide and conquer做跟priority queue做，时间都是O(nlogk)，空间都是O(1)
     * Only the head of each linked list is added to the queue.
     * When you pop the minimum element from PQ, you need to add its next nodes to the PQ to sort them as well.
     * If the next node is not null, you can add it to the PQ.
     */
    public ListNode mergeKLists(ListNode[] lists) {  // merge lists with divide and conquer
        if (lists == null || lists.length == 0) {
            return null;
        }
        return helper(lists, 0, lists.length - 1);
    }

    private ListNode helper(ListNode[] lists, int start, int end) {
        if (start >= end) {
            return lists[start];
        }

        int mid = (end - start) / 2 + start;
        ListNode l1 = helper(lists, start, mid);
        ListNode l2 = helper(lists, mid + 1, end);

        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummy.next;
    }


    // method 2: using heap
    public ListNode mergeKLists2(ListNode[] lists) {
        // O(nlongk) where k is the number of linked lists
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 重写compare函数，按照每个list第一个元素的最小值排序，其实即使一个最小堆， 在java里叫PriorityQueue
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.add(lists[i]);
            }
        }

        while (queue.size() != 0) {
            ListNode node = queue.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                queue.add(node.next);
            }
        }

        return dummy.next;
    }


}
