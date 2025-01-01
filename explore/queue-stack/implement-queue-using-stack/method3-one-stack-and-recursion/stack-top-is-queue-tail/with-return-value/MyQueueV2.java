import java.util.Stack;

class MyQueueV2 {
    public Stack<Integer> stack1;
    
    public MyQueueV2() {
        stack1 = new Stack<>();
    }
    
    public void push(int val) {
        // directly push val into stack1
        stack1.push(val);
    }
    
    public int peek() {
        // if stack1 is empty, directly return Integer.MAX_VALUE
        if (stack1.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        // now, stack is not empty, then do peek() with recursion with return value and assign it to top
        int top = peekRecursiveBody(stack1);
        // return top
        return top;
    }
    public int peekRecursiveBody(Stack<Integer> stack1) {
        // pop top element first to check if stack1 only has one element
        int top = stack1.pop();
        // initialize peek for storing results in different conditions
        int peek = 0;

        // [if next state is valid]
        // if stack1 is not empty, then enter into next state
        if (!stack1.isEmpty()) {
            // receive peek value returned from last level
            peek = peekRecursiveBody(stack1);
        }
        if (stack1.isEmpty()) {
            peek = top;
        }
        // push back the popped top value above into stack to restore the state of current level
        stack1.push(top);
        // return peek
        return peek;
    }
    
    public void pop() {
        // if stack1 is empty, directly return
        if (stack1.isEmpty()) {
            return;
        }
        // now, stack is not empty, then do pop() with recursion without return value
        popRecursiveBody(stack1);
    }
    public void popRecursiveBody(Stack<Integer> stack1) {
        // [if current state is not valid]
        // if stack1 is empty, directly return, actually this won't be executed at all
        if (stack1.isEmpty()) {
            return;
        }
        // pop top element first to check if stack1 only has one element
        int top = stack1.pop();
        if (stack1.isEmpty()) {
            return;
        }
        // push back top to stack to restore to intial state when it is not the last element in stack1
        stack1.push(top);
        // pop top element of current level for next level
        top = stack1.pop();
        popRecursiveBody(stack1);
        // push back top in current level(except the deepest level, which is the last element in stack) to return to top of stack
        stack1.push(top);
    }
    
    public boolean empty() {
        // return true if stack1 is empty
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