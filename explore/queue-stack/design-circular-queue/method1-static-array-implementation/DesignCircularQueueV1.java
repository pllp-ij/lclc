class DesignCircularQueueV1 {
    
    public static void main(String[] args) {
        int k = 3;
        CircularQueue myCircularQueue = new CircularQueue(k);
        myCircularQueue.enQueue(1); // return True
        System.out.println("after enqueue 1, queue: " + myCircularQueue.toString());
        myCircularQueue.enQueue(2); // return True
        System.out.println("after enqueue 2, queue: " + myCircularQueue.toString());
        myCircularQueue.enQueue(3); // return True
        System.out.println("after enqueue 3, queue: " + myCircularQueue.toString());
        boolean result = myCircularQueue.enQueue(4); // return False
        System.out.println("enqueue 4 result: " + result);
        System.out.println("after enqueue 4, queue: " + myCircularQueue.toString());
        int val = myCircularQueue.getTail();     // return 3
        System.out.println("tail value: " + val);
        result = myCircularQueue.isFull();   // return True
        System.out.println("queue is full result: " + result);
        result = myCircularQueue.deQueue();  // return True
        System.out.println("dequeue result: " + result);
        System.out.println("after dequeue, queue: " + myCircularQueue.toString());
        myCircularQueue.enQueue(4); // return True
        System.out.println("after enqueue 4, queue: " + myCircularQueue.toString());
        val = myCircularQueue.getTail();     // return 4
        System.out.println("tail value: " + val);
    }
}