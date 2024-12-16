import java.util.LinkedList;
import java.util.Queue;

/*
    VARS:
        windowCapacity(int): the maximum number of integers allowed to add into window
        window(Queue<Integer>): a window with type of queue
        sum(double): the sum of latest k integers
    DESCRIPTION:
        STEP 1
        Initialize windowCapacity to k in constructor
        Initialize window to LinkedList<>() in constructor
        Initialize sum to 0.0 in constructor
        STEP 2
        Add current value to sum
            sum += value;
        STEP 3
        Add current value into queue window
            window.offer(value);
        STEP 4
        If window.size() > windowCapacity, (meaning the number of integers in window already exceed the capacity)
            STEP 5
            Substract front element from sum
                sum -= window.peek();
            STEP 6
            Remove the front element from window
                window.poll();
        STEP 7
        Return average
            return sum / window.size()
    TIME:
        O(k), before adding value, there should be a fixed-size static array created with value of k, the add operation is O(1)
    SPACE:
        O(k), a static array with size of k should be maintained
*/

class MovingAverageFromDataStreamV1 {
    public int windowCapacity;
    public Queue<Integer> window;
    public double sum;
    
    public MovingAverageFromDataStreamV1(int k) {
        // STEP 1
        windowCapacity = k;
        window = new LinkedList<>();
        sum = 0.0;
    }
    
    public double next(int value) {
        // STEP 2
        sum += value;
        // STEP 3
        window.offer(value);
        // STEP 4
        if (window.size() > windowCapacity) {
            // STEP 5
            sum -= window.peek();
            // STEP 6
            window.poll();
        }
        // STEP 7
        return sum / window.size();
    }
    
    public static void main(String[] args) {
        MovingAverageFromDataStreamV1 movingAverage = new MovingAverageFromDataStreamV1(3);
        double avg = movingAverage.next(1);
        System.out.println("after add 1, moving average: " + avg);
        avg = movingAverage.next(10);
        System.out.println("after add 10, moving average: " + avg);
        avg = movingAverage.next(3);
        System.out.println("after add 3, moving average: " + avg);
        avg = movingAverage.next(5);
        System.out.println("after add 5, moving average: " + avg);
    }
}