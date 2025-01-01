class ImplementQueueUsingStackV3 {
    
    public static void main(String[] args) {
        MyQueueV3 myQueue = new MyQueueV3();
        
        System.out.println("myQueue: " + myQueue.printStackTailToHeadRecursive());
        myQueue.push(1); // queue is: [1]
        System.out.println("after push 1, myQueue: " + myQueue.printStackTailToHeadRecursive());
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println("after push 2, myQueue: " + myQueue.printStackTailToHeadRecursive());
        int result = myQueue.peek(); // return 1
        System.out.println("the peek value: " + result);
        myQueue.pop(); // return 1, queue is [2]
        System.out.println("after pop, myQueue: " + myQueue.printStackTailToHeadRecursive());
        boolean res = myQueue.empty(); // return false
        System.out.println("queue is empty: " + res);
    }
}