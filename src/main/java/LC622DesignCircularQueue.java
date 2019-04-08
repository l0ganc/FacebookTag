public class LC622DesignCircularQueue {
    /**
     * 循环队列
     * Your implementation should support following operations:
     *
     * MyCircularQueue(k): Constructor, set the size of the queue to be k.
     * Front: Get the front item from the queue. If the queue is empty, return -1.
     * Rear: Get the last item from the queue. If the queue is empty, return -1.
     * enQueue(value): Insert an element into the circular queue. Return true if the operation is successful.
     * deQueue(): Delete an element from the circular queue. Return true if the operation is successful.
     * isEmpty(): Checks whether the circular queue is empty or not.
     * isFull(): Checks whether the circular queue is full or not.
     *
     *
     * Example:
     *
     * MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
     * circularQueue.enQueue(1);  // return true
     * circularQueue.enQueue(2);  // return true
     * circularQueue.enQueue(3);  // return true
     * circularQueue.enQueue(4);  // return false, the queue is full
     * circularQueue.Rear();  // return 3
     * circularQueue.isFull();  // return true
     * circularQueue.deQueue();  // return true
     * circularQueue.enQueue(4);  // return true
     * circularQueue.Rear();  // return 4
     */

    int[] arr;
    int rear = -1;
    int front = 0;
    int len = 0;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public LC622DesignCircularQueue(int k) {
        arr = new int[k];

    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()) {
            rear = (rear + 1) % arr.length;
            arr[rear] = value;
            len++;
            return true;
        } else {
            return false;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % arr.length;
        len--;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        return arr[front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        return arr[rear];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return len == 0;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len == arr.length;
    }

    public static void main(String[] args) {
        LC622DesignCircularQueue obj = new LC622DesignCircularQueue(3);
        boolean param_1 = obj.enQueue(1);
        boolean param_2 = obj.enQueue(2);
        boolean param_3 = obj.enQueue(3);
        boolean param_4 = obj.enQueue(4);
        int param_5 = obj.Rear();
        boolean param_6 = obj.isFull();
        boolean param_8 = obj.deQueue();
        boolean param_9 = obj.enQueue(4);
        int param_10 = obj.Rear();
        System.out.println(param_1);
        System.out.println(param_2);
        System.out.println(param_3);
        System.out.println(param_4);
        System.out.println(param_5);
        System.out.println(param_6);
        System.out.println(param_8);
        System.out.println(param_9);
        System.out.println(param_10);
    }
}
