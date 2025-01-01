import java.util.Stack;

class MyQueueV2 {
    public Stack<Integer> stack1;
    public Stack<Integer> stack2;
    
    public MyQueueV2() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int val) {
        // push val into stack1
        stack1.push(val);
    }
    
    public int peek() {
        // if both stacks are empty, directly return Integer.MAX_VALUE
        if (stack1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // loop while stack1 is not empty, move all elements from stack1 to stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // store the top element of stack2 for return
        int top = stack2.peek();
        // loop while stack2 is not empty, move all elements from stack2 to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        // return top
        return top;
    }
    
    public void pop() {
        // if both stacks are empty, directly return Integer.MAX_VALUE
        if (stack1.isEmpty()) {
            return;
        }
        // loop while stack1 is not empty, move all elements from stack1 to stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // pop top element of stack2
        stack2.pop();
        // loop while stack2 is not empty, move all elements from stack2 to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
    
    public boolean empty() {
        return stack1.isEmpty();
    }
    
    public String printStackHeadToTailRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("HEAD |  ");
        printStackTailToHeadRecursiveBody(stack1, str);
        str.append("| TAIL");
        return str.toString();
    }
    public void printStackHeadToTailRecursiveBody(Stack<Integer> stack1, StringBuilder str) {
        // check if current stack1 is empty, directly return
        if (stack1.isEmpty()) {
            return;
        }
        // add top element from stack1 to str
        int top = stack1.pop();
        str.append(top + "  ");
        // do recursion for next state of stack1 after popping above top element
        printStackHeadToTailRecursiveBody(stack1, str);
        // push top value back to stack
        stack1.push(top);
    }
    
    public String printStackTailToHeadRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("TAIL |  ");
        printStackHeadToTailRecursiveBody(stack1, str);
        str.append("| HEAD");
        return str.toString();
    }
    public void printStackTailToHeadRecursiveBody(Stack<Integer> stack1, StringBuilder str) {
        // check if current stack1 is empty, directly return
        if (stack1.isEmpty()) {
            return;
        }
        // pop top element for next iteration
        int top = stack1.pop();
        // do recursion for next state of stack1 after popping above top element
        printStackTailToHeadRecursiveBody(stack1, str);
        // add top element from stack1 to str
        str.append(top + "  ");
        // push top value back to stack
        stack1.push(top);
    }
    
    
}