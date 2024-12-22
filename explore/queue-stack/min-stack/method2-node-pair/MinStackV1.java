import java.util.Stack;

/*
    NOTE:
        Compared to method1, method2 package each pair under same index from data array and min array to a single Node object, the main logic is same
*/

class Node {
    public int val;
    public int min;
    
    public Node(int val, int min) {
        this.val = val;
        this.min = min;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("(" + val + ", " + min + ")");
        return str.toString();
    }
}

public class MinStackV1 {
    public int capacity;
    // a stack whose element is Node type
    public Stack<Node> stack;
    
    public MinStackV1() {
        this.capacity = 1000;
        stack = new Stack<>();
    }
    
    public MinStackV1(int capacity) {
        this.capacity = capacity;
        stack = new Stack<>();
    }
    
    public void push(int val) {
        // initialize newNode to null
        Node newNode = null;
        // if stack is empty, directly create a Node then add it to stack
        if (isEmpty()) {
            newNode = new Node(val, val);
        } else {
            newNode = new Node(val, Math.min(stack.peek().min, val));
        }
        stack.push(newNode);
    }
    
    public void pop() {
        // if stack is empty, directly return
        if (isEmpty()) {
            return;
        }
        stack.pop();
    }
    
    public int top() {
        // if stack is empty, return Integer.MIN_VALUE
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return stack.peek().val;
    }
    
    public int getMin() {
        // if stack is empty, return Integer.MIN_VALUE
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return stack.peek().min;
    }
    
    public int getLength() {
        return stack.size();
    }
    
    public boolean isFull() {
        return stack.size() >= capacity;
    }
    
    public boolean isEmpty() {
        return stack.isEmpty();
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("HEAD | ");
        for (int idx = 0; idx < stack.size(); idx++) {
            str.append(stack.get(idx).toString());
            if (idx < stack.size() - 1) {
                str.append(", ");
            }
        }
        str.append(" | TAIL\n");
        return str.toString();
    }
    
    public static void main(String[] args) {
        MinStackV1 minStack = new MinStackV1();
        System.out.println("initial min stack: \n" + minStack.toString());
        minStack.push(-2);
        System.out.println("after push -2, min stack: \n" + minStack.toString());
        minStack.push(0);
        System.out.println("after push 0, min stack: \n" + minStack.toString());
        minStack.push(-3);
        System.out.println("after push -3, min stack: \n" + minStack.toString());
        int min = minStack.getMin(); // return -3
        System.out.println("now min is: " + min);
        minStack.pop();
        System.out.println("after pop, min stack: \n" + minStack.toString());
        int top = minStack.top();    // return 0
        System.out.println("now top is: " + top);
        min = minStack.getMin(); // return -2
        System.out.println("now min is: " + min);
    }
}