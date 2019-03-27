import java.util.HashMap;
import java.util.Map;

public class LC138CopyListwithRandomPointer {
    // Definition for a Node.
    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Map<Node, Node> map = new HashMap<>();
        Node cur = head;

        while (cur != null) {
            Node copy = new Node(cur.val, null, null);
            cur = cur.next;
        }

        cur = head;

        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }
}
