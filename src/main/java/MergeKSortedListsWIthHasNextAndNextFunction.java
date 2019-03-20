import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedListsWIthHasNextAndNextFunction {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    PriorityQueue<ListNode> pq;

    public MergeKSortedListsWIthHasNextAndNextFunction(ListNode[] lists) {
        pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));

        for (ListNode list : lists) {
            // 一定要先判断list是否为空
            if (list != null) {
                pq.offer(list);
            }
        }
    }

    public boolean hasNext() {
        return !pq.isEmpty();
    }

    public int next() {
        ListNode cur = pq.poll();
        if (cur.next != null) {
            pq.offer(cur.next);
        }
        return cur.val;
    }


    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(5);
        ListNode n2 = new ListNode(1);
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);
        ListNode n3 = new ListNode(2);
        n3.next = new ListNode(6);
        ListNode[] lists = {n1, n2, n3};

        MergeKSortedListsWIthHasNextAndNextFunction iterator = new MergeKSortedListsWIthHasNextAndNextFunction(lists);
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();
    }
}
