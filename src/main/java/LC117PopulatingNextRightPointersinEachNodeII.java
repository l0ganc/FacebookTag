public class LC117PopulatingNextRightPointersinEachNodeII {
    // 作为LC116的follow up，主要区别是给的数是一个普通树，不一定是满二叉树

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val,Node _left,Node _right,Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    // 非满二叉树 需要加一个pre节点
    public Node connect(Node root) {
        Node head = null;  // head 用来指向每一次的头结点
        Node pre = null;   // pre指向当前节点的前一个节点，专治非满二叉树
        Node cur = root;   // cur用来遍历每一层节点
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            pre = null;
            head = null;
        }
        return root;
    }
}
