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
        // loop while queue1 is not empty, move all elements from queue1 to queue2 to empty queue1 for pushed elements to be inserted
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        // push x into queue1
        queue1.offer(val);
        // loop while queue2 is not empty, move all elements from queue2 to queue1
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        // increase queue1 size by one
        queue1Size++;
    }
    
    public int pop() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // dequeue element from head of queue
        int head = queue1.poll();
        // decrease queue1 size by one
        queue1Size--;
        return head;
    }
    
    public int top() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // dequeue element from head of queue
        int head = queue1.peek();
        return head;
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