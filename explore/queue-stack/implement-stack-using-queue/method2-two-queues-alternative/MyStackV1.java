import java.util.Queue;
import java.util.LinkedList;

class MyStackV1 {
    
    /*
        queue head <-> stack top
    */
    
    public Queue<Integer> queue1;
    public Queue<Integer> queue2;
    
    public int queue1Size;
    
    public MyStackV1() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        queue1Size = 0;
    }
    
    public void push(int val) {
        Queue<Integer> queueTemp = null;
        // set queue1 always point to non-empty queue, while queue2 always point to empty queue
        if (queue1.isEmpty()) {
            queueTemp = queue1;
            queue1 = queue2;
            queue2 = queueTemp;
        }
        // push val into queue2 first
        queue2.offer(val);
        // loop while queue1 is not empty, move all elements from queue1 to queue2
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        // switch queue1 and queue2, always make queue1 point to non-empty one, queue2 point to empty one
        queueTemp = queue1;
        queue1 = queue2;
        queue2 = queueTemp;
        // increase queue1Size by one
        queue1Size++;
    }
    
    public int pop() {
        // because queue1 always point to non-empty queue, so there only to check queue1
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // pop top element from queue1
        int top = queue1.peek();
        // decrease queue1Size by one
        queue1Size--;
        return top;
    }
    
    public int top() {
        // because queue1 always point to non-empty queue, so there only to check queue1
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // pop top element from queue1
        int top = queue1.poll();
        return top;
    }
    
    public boolean empty() {
        return queue1.isEmpty();
    }
    
    public String printStackFromBottomToTopRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("TOP |  ");
        printStackFromBottomToTopCyclicBody(queue1, str);
        str.append("| BOTTOM");
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