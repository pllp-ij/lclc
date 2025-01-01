import java.util.Stack;

class MyQueueV1 {
    public Stack<Integer> stack1;
    public Stack<Integer> stack2;
    
    public MyQueueV1() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int val) {
        // loop while stack1 is not empty, move all elements from stack1 to stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // push val into stack2
        stack2.push(val);
    }
    
    public int peek() {
        // if both stacks are empty
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // loop while stack2 is not empty, move all elements from stack2 to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        // return the top element of stack1
        return stack1.peek();
    }
    
    public void pop() {
        // if both stacks are empty
        if (stack1.isEmpty() && stack2.isEmpty()) {
            return;
        }
        // loop while stack2 is not empty, move all elements from stack2 to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        // pop top element from stack1, note that when above stack2 is not empty, after movements, stack1 won't be empty either
        stack1.pop();
    }
    
    public boolean empty() {
        // return true when stack1 and stack2 are both empty
        return stack1.isEmpty() && stack2.isEmpty();
    }
    
    public String printStackHeadToTailRecursive() {
        StringBuilder str = new StringBuilder();
        Stack<Integer> stackSelected = stack1.isEmpty() ? stack2 : stack1;
        if (!stack1.isEmpty()) {
            str.append("HEAD |  ");
            printStackHeadToTailRecursiveBody(stackSelected, str);
            str.append("| TAIL");
        }
        if (!stack2.isEmpty()) {
            str.append("HEAD |  ");
            printStackTailToHeadRecursiveBody(stackSelected, str);
            str.append("| TAIL");
        }
        return str.toString();
    }
    public void printStackHeadToTailRecursiveBody(Stack<Integer> stackSelected, StringBuilder str) {
        // check if current stackSelected is empty, directly return
        if (stackSelected.isEmpty()) {
            return;
        }
        // add top element from stackSelected to str
        int top = stackSelected.pop();
        str.append(top + "  ");
        // do recursion for next state of stackSelected after popping above top element
        printStackHeadToTailRecursiveBody(stackSelected, str);
        // push top value back to stack
        stackSelected.push(top);
    }
    
    public String printStackTailToHeadRecursive() {
        StringBuilder str = new StringBuilder();
        Stack<Integer> stackSelected = stack1.isEmpty() ? stack2 : stack1;
        if (!stack1.isEmpty()) {
            str.append("TAIL |  ");
            printStackTailToHeadRecursiveBody(stackSelected, str);
            str.append("| HEAD");
        }
        if (!stack2.isEmpty()) {
            str.append("TAIL |  ");
            printStackHeadToTailRecursiveBody(stackSelected, str);
            str.append("| HEAD");
        }
        return str.toString();
    }
    public void printStackTailToHeadRecursiveBody(Stack<Integer> stackSelected, StringBuilder str) {
        // check if current stackSelected is empty, directly return
        if (stackSelected.isEmpty()) {
            return;
        }
        // pop top element for next iteration
        int top = stackSelected.pop();
        // do recursion for next state of stackSelected after popping above top element
        printStackTailToHeadRecursiveBody(stackSelected, str);
        // add top element from stackSelected to str
        str.append(top + "  ");
        // push top value back to stack
        stackSelected.push(top);
    }
}