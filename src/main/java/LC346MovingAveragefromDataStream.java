import java.util.LinkedList;
import java.util.Queue;

public class LC346MovingAveragefromDataStream {
    /**
     *  Moving Average from Data Stream
     *
     *  Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
     *
     * Example:
     *
     * MovingAverage m = new MovingAverage(3);
     * m.next(1) = 1
     * m.next(10) = (1 + 10) / 2
     * m.next(3) = (1 + 10 + 3) / 3
     * m.next(5) = (10 + 3 + 5) / 3
     */

    // 用队列做 先进先出
    private Queue<Integer> queue;
    private double sum = 0;
    private int size;
    /** Initialize your data structure here. */
    public LC346MovingAveragefromDataStream(int size) {
        queue = new LinkedList<>();
        this.size = size;
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.remove();
        }
        sum += val;
        queue.offer(val);
        return sum / queue.size();
    }
}
