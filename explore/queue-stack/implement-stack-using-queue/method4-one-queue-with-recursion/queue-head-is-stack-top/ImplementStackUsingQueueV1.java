class ImplementStackUsingQueueV1 {
    
    public static void main(String[] args) {
        MyStackV1 myStack = new MyStackV1();
        System.out.println("initial stack: " + myStack.printStackFromBottomToTopRecursive());
        myStack.push(1);
        System.out.println("after push 1, stack: " + myStack.printStackFromBottomToTopRecursive());
        myStack.push(2);
        System.out.println("after push 2, stack: " + myStack.printStackFromBottomToTopRecursive());
        int top = myStack.top();    // return 2
        System.out.println("after top, top is: " + top);
        top = myStack.pop();    // return 2
        System.out.println("after pop, stack is: " + myStack.printStackFromBottomToTopRecursive());
        boolean res = myStack.empty();  // return False
        System.out.println("current stack is empty: " + res);
    }
}