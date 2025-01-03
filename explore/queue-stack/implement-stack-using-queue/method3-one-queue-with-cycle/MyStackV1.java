import java.util.Queue;
import java.util.LinkedList;

class MyStackV1 {
    
    /*
        queue head <-> stack top
    */
    
    public Queue<Integer> queue1;
    public int queue1Size;
    
    public MyStackV1() {
        queue1 = new LinkedList<>();
        queue1Size = 0;
    }
    
    public void push(int val) {
        // first enqueue val into queue1
        queue1.offer(val);
        // iterate queue1Size times to cycle all elements behind val
        for (int i = 0; i < queue1Size; i++) {
            queue1.offer(queue1.poll());
        }
        // now val is at the head of queue
        // increase queue1Size by one
        queue1Size++;
    }
    
    public int pop() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // dequeue front element from queue1
        int top = queue1.poll();
        // decrease queue1Size by one
        queue1Size--;
        return top;
    }
    
    public int top() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // dequeue front element from queue1
        int top = queue1.peek();
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