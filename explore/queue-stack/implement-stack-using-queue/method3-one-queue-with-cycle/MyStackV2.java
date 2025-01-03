import java.util.Queue;
import java.util.LinkedList;

class MyStackV2 {
    
    /*
        queue tail <-> stack top
    */
    
    public Queue<Integer> queue1;
    public int queue1Size;
    
    public MyStackV2() {
        queue1 = new LinkedList<>();
        queue1Size = 0;
    }
    
    public void push(int val) {
        // directly enqueue val into queue1
        queue1.offer(val);
        // increase queue1Size by one
        queue1Size++;
    }
    
    public int pop() {
        // if queue1 is empty, directly Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // iterate (queue1Size - 1) times to cycle elements behind top element
        for (int i = 0; i < queue1Size - 1; i++) {
            queue1.offer(queue1.poll());
        }
        // dequeue top element from queue1
        int top = queue1.poll();
        // decrease queue1Size by one
        queue1Size--;
        return top;
    }
    
    public int top() {
        // if queue1 is empty, directly Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int top = 0;
        // iterate queue1Size times to cycle elements behind top element
        for (int i = 0; i < queue1Size; i++) {
            top = queue1.poll();
            queue1.offer(top);
        }
        return top;
    }
    
    public boolean empty() {
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