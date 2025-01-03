import java.util.Queue;
import java.util.LinkedList;

class MyStackV2 {
    
    /*
        queue tail <-> stack top
    */
    
    public Queue<Integer> queue1;
    public Queue<Integer> queue2;
    
    public int queue1Size;
    
    public MyStackV2() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        queue1Size = 0;
    }
    
    public void push(int val) {
        // always make queue1 point to non-empty queue, make queue2 point to empty queue
        Queue<Integer> queueTemp = null;
        // push val into queue1
        queue1.offer(val);
        // increase queue1Size by one
        queue1Size++;
    }
    
    public int pop() {
        // because queue1 always point to non-empty queue, so only to check whther queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // initialize a temp queue for switching queue1 and queue2
        Queue<Integer> queueTemp = null;
        // iterate (queue1Size - 1) times to dequeue all eleemnts before top element to queue2
        for (int i = 0; i < queue1Size - 1; i++) {
            queue2.offer(queue1.poll());
        }
        // get top element and dequeue it
        int top = queue1.poll();
        // switch queue1 and queue2 to make queue1 always point to non-empty queue
        queueTemp = queue1;
        queue1 = queue2;
        queue2 = queueTemp;
        // decrease queue1Size by one
        queue1Size--;
        return top;
    }
    
    public int top() {
        // because queue1 always point to non-empty queue, so only to check whther queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // initialize a temp queue for switching queue1 and queue2
        Queue<Integer> queueTemp = null;
        // iterate (queue1Size - 1) times to dequeue all eleemnts before top element to queue2
        for (int i = 0; i < queue1Size - 1; i++) {
            queue2.offer(queue1.poll());
        }
        // get top element and dequeue it
        int top = queue1.poll();
        // enqueue top back to queue2
        queue2.offer(top);
        // switch queue1 and queue2 to make queue1 always point to non-empty queue
        queueTemp = queue1;
        queue1 = queue2;
        queue2 = queueTemp;
        return top;
    }
    
    public boolean empty() {
        // because queue1 always point to queue1, so only check that queue
        return queue1.isEmpty();
    }
    
    public String printStackFromBottomToTopRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("BOTTOM |  ");
        printStackFromBottomToTopCyclicBody(queue1, str);
        str.append("| TOP");
        return str.toString();
    }
    public void printStackFromBottomToTopCyclicBody(Queue<Integer> queue1, StringBuilder str) {
        // iterate queue1Size times
        for (int i = 0; i < queue1Size; i++) {
            // dequeue element from queue head
            int head = queue1.poll();
            // add head to str
            str.append(head + "  ");
            // enqueue head back to queue
            queue1.offer(head);
        }
    }
}