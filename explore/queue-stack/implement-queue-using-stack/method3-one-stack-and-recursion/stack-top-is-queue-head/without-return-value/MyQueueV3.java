import java.util.Stack;

class MyQueueV3 {
    public Stack<Integer> stack1;
    
    public MyQueueV3() {
        stack1 = new Stack<>();
    }
    
    public void push(int val) {
        // [if current state is not valid]
        // if current stack1 is empty, directly push val into stack then return
        if (stack1.isEmpty()) {
            stack1.push(val);
            return;
        }
        // now, stack is not empty, then do push with recursion
        pushRecursiveBody(stack1, val);
    }
    public void pushRecursiveBody(Stack<Integer> stack1, int val) {
        // if stack is empty, push val into stack at deepest level and then directly return
        if (stack1.isEmpty()) {
            stack1.push(val);
            return;
        }
        // pop top element from current level for next iteration
        int top = stack1.pop();
        pushRecursiveBody(stack1, val);
        // again push formal popped top element
        stack1.push(top);
    }
    
    public int peek() {
        // if stack1 is empty, return Integer.MAX_VALUE
        if (stack1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // return top element of stack1
        return stack1.peek();
    }
    
    public void pop() {
        // if stack1 is empty, return Integer.MAX_VALUE
        if (stack1.isEmpty()) {
            return;
        }
        // pop top element of stack1
        stack1.pop();
    }
    
    public boolean empty() {
        // return true if stack1 is empty
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