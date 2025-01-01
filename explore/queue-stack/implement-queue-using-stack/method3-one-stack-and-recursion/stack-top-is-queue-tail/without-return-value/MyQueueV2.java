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
        // initialize top value for fetching the deepest element as peek
        int[] res = {0};
        // now, stack is not empty, then do peek() with recursion without return value
        peekRecursiveBody(stack1, res);
        // return first element of res
        return res[0];
    }
    public void peekRecursiveBody(Stack<Integer> stack1, int[] res) {
        // pop top element of current level for next level
        int top = stack1.pop();
        // [if next state is valid]
        // if stack1 is not empty, then enter into next state
        if (!stack1.isEmpty()) {
            peekRecursiveBody(stack1, res);
        }
        // if stack1 is now empty, meaning the element to be fetched is top, assign top to res for delivering
        if (stack1.isEmpty()) {
            res[0] = top;
        }
        // push back top in current level to return to top of stack
        stack1.push(top);
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
        // pop top element of current level for next level
        int top = stack1.pop();
        // [if next state is valid]
        // if stack1 is not empty, then enter into next state
        if (!stack1.isEmpty()) {
            popRecursiveBody(stack1);
            // push back top in current level(except the deepest level, which is the last element in stack) to return to top of stack
            stack1.push(top);
        }        
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