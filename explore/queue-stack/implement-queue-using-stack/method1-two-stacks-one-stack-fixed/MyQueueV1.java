import java.util.Stack;

class MyQueueV1 {
    public Stack<Integer> stack1;
    public Stack<Integer> stack2;
    
    public MyQueueV1() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int val) {
        // loop while stack1 is not empty and move all from stack1 to stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // push val into stack1 or stack2 are same
        stack1.push(val);
        // stack2.push(val);
        // loop while stack2 is not empty and move all from stack2 to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
    
    public int peek() {
        // if stack1 is empty, return Integer.MAX_VALUE
        if (stack1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // return top of stack1
        return stack1.peek();
    }
    
    public void pop() {
        // if stack1 is empty, return Integer.MAX_VALUE
        if (stack1.isEmpty()) {
            return;
        }
        stack1.pop();
    }
    
    public boolean empty() {
        return stack1.isEmpty();
    }
    
    public String printStackHeadToTailRecursive() {
        StringBuilder str = new StringBuilder();
        str.append("HEAD |  ");
        printStackHeadToTailRecursiveBody(stack1, str);
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
        printStackTailToHeadRecursiveBody(stack1, str);
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