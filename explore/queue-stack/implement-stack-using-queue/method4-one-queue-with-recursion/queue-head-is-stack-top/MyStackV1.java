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
        // first push val into queue1, then do recursive
        queue1.offer(val);
        // increase queue1Size by one
        queue1Size++;
    }
    
    public int pop() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // do recursively pop to put last enqueued element to the top
        popRecursiveBody(queue1);
        // dequeue top element from queue1
        int top = queue1.poll();
        // do recursively pop again to reverse the order to initial state
        popRecursiveBody(queue1);
        // decrease queue1Size by one
        queue1Size--;
        return top;
    }
    public void popRecursiveBody(Queue<Integer> queue1) {
        // if current queue1 is empty, directly return
        if (queue1.isEmpty()) {
            return;
        }
        // dequeue current element for next iteration
        int top = queue1.poll();
        popRecursiveBody(queue1);
        // enqueue top back to queue in current level
        queue1.offer(top);
    }
    
    public int top() {
        // if queue1 is empty, directly return Integer.MAX_VALUE
        if (queue1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // do recursively pop to put last enqueued element to the top
        topRecursiveBody(queue1);
        // dequeue top element from queue1
        int top = queue1.peek();
        // do recursively pop again to reverse the order to initial state
        topRecursiveBody(queue1);
        return top;
    }
    public void topRecursiveBody(Queue<Integer> queue1) {
        // if current queue1 is empty, directly return
        if (queue1.isEmpty()) {
            return;
        }
        // dequeue current element for next iteration
        int top = queue1.poll();
        popRecursiveBody(queue1);
        // enqueue top back to queue in current level
        queue1.offer(top);
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