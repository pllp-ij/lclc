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
        queue1.offer(val);
        queue1Size++;
    }
    
    public int pop() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // poll all elements in queue1, move them from queue1 to queue2, so only the last element will remain in queue1
        for (int i = 0; i < queue1Size - 1; i++) {
            queue2.offer(queue1.poll());
        }
        // pop front element of queue1 to pop it
        int top = queue1.poll();
        // loop while queue2 is not empty, move all elements from queue2 to queue1
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        // decrease queue1Size by one
        queue1Size--;
        // return top
        return top;
    }
    
    public int top() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // poll all elements in queue1, move them from queue1 to queue2, so only the last element will remain in queue1
        for (int i = 0; i < queue1Size - 1; i++) {
            queue2.offer(queue1.poll());
        }
        // pop front element of queue1 to pop it
        int top = queue1.poll();
        // push top back to queue2 after getting its value
        queue2.offer(top);
        // loop while queue2 is not empty, move all elements from queue2 to queue1
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
        // return top
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