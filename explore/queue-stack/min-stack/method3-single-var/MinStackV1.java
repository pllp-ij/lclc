import java.util.Stack;

/*
    NOTE:
      j            0    1    2
    i 
    0    process  -2    0    -3            val
    1    stack    -2    0   temp 
    2    min      -2   -2    -3
                       
    in order to illustrate the principle behind this method, firstly, we will process -2, 0, -3 in sequence, and we set three rows and three columns respectively to conveniently represent each position of these 3x3 grid,
    1 the first element to be processed is -2, since at this time, stack is empty, so directly push -2 into stack, and set min to -2
    2 the second element to be processed is 0, because 0 >= -2
        denote grid(2, 0) as minBefore, grid(0, 1) as val, grid(1, 1) as ele, grid(2, 1) as minCur
            when push:
                    under condition: 
                    val >= minBefore, (1)
                we do:
                    ele = val; (push current val into stack), (2)
                    minCur = minBefore; (min won't be changed), (3)
                
            when pop:
                replace val with ele from (2), replace minBefore with minCur from (3), we can conclude that
                under condition :
                ele >= minCur, (4)
    3 the third element to be processed is -3, because -3 < -2
        denote grid(2, 1) as minBefore, grid(0, 2) as val, grid(1, 2) as ele, grid(2, 2) minCur
            when push:
                    under condition:
                    val < minBefore, (5)
                we must ensure that 
                    ele < minCur, (6)
                always satisfied, so (1) and (5) are mutually exclusively to indicate the only two conditions, one of which should update min, the other should not
                ,in order to get ele representation, we can do in this deduction:
                
                    we will do:
                        minCur = val, (7)
                replace val with minCur from (7) into (5), we get
                    minCur < minBefore, (8)
                so in order get the formula of ele in (8), we add minCur on both side of (7), then got
                    minCur + minCur < minBefore + minCur, (9),
                then we move minBefore in (9) to the right, got
                    minCur + minCur - minBefore < minCur, (10),
                so the right side of (10) is same as (6), then we can just assign the left side of (10) as the left side of (6), we got
                    ele = minCur + minCur - minBefore = 2 * minCur - minBefore, (11)
                according to (7), we replace minCur with val, so we finally got the formula as
                    ele = 2 * val - minBefore, (12)
            when pop:
                under condition (6):
                    ele < minCur,
                according to (11),
                we got the previous min as
                    minBefore = 2 * minCur - ele;
*/

public class MinStackV1 {
    public int capacity;
    public Stack<Long> stack;
    public long min;
    
    public MinStackV1() {
        capacity = 1000;
        stack = new Stack<>();
        min = Long.MAX_VALUE;
    }
    
    public MinStackV1(int capacity) {
        this.capacity = capacity;
        stack = new Stack<>();
        min = Long.MAX_VALUE;
    }
    
    public void push(int val) {
        // convert val to long
        long valLong = (long) val;
        // if stack is empty, directly add val and assign val to min
        if (isEmpty()) {
            stack.push(valLong);
            min = valLong;
            return;
        }
        // if val >= min, meaning min should not be changed and directly push val to stack
        if (val >= min) {
            stack.push(valLong);
        } else {  // if val < min, meaning min should be updated to val, but before that, one should integrate the previous min into current position to restore it in pop operation, so push (val - min) firstly, before update lastest min as val
            stack.push(2 * valLong - min);
            min = valLong;
        }
    }
    
    public void pop() {
        // if stack is empty, return Integer.MIN_VALUE
        if (isEmpty()) {
            return;
        }
        long topLong = stack.peek();
        // if stack.peek() >= min, meaning previous min still within the stack after removing current top node, so directly remove current top node without hestitaion
        if (topLong >= min) {
            min = min;  // first min is minBefore, second min is minCur
            stack.pop();
        } else {  // if stack.peek() < min, meaning the min of the stack before removing current top node is happended to be the current top node, so 
            min = 2 * min - topLong;  // first min is minBefore, second min is minCur
            stack.pop();
        }
    }
    
    public int top() {
        // if stack is empty, return Integer.MIN_VALUE
        if (isEmpty()) {
            return Integer.MAX_VALUE;
        }
        long topLong = stack.peek();
        // if stack.peek() >= min, meaning previous min won't change after removing current top node in stack, and the true value is just stack.peek()
        if (topLong >= min) {
            return (int) topLong;
        } else {  // if stack.peek() < min, meaning the min of the stack before removing current top node is happened to be the current top node, and the actually initial top value is minCur, so return minCur
            return (int) min;
        }
    }
    
    public int getMin() {
        return (int) min;
    }
    
    public int getLength() {
        return stack.size();
    }
    
    public boolean isFull() {
        return stack.size() >= capacity;
    }
    
    public boolean isEmpty() {
        return stack.size() == 0;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("HEAD | ");
        for (int idx = 0; idx < stack.size(); idx++) {
            str.append(stack.get(idx));
            if (idx < stack.size() - 1) {
                str.append(", ");
            }
        }
        str.append(" | TAIL\n");
        str.append("min: " + min + "\n");
        return str.toString();
    }
    
    public static void main(String[] args) {
        // MinStackV1 minStack = new MinStackV1();
        // System.out.println("initial min stack: \n" + minStack.toString());
        // minStack.push(-2);
        // System.out.println("after push -2, min stack: \n" + minStack.toString());
        // minStack.push(0);
        // System.out.println("after push 0, min stack: \n" + minStack.toString());
        // minStack.push(-3);
        // System.out.println("after push -3, min stack: \n" + minStack.toString());
        // int min = minStack.getMin(); // return -3
        // System.out.println("now min is: " + min);
        // minStack.pop();
        // System.out.println("after pop, min stack: \n" + minStack.toString());
        // int top = minStack.top();    // return 0
        // System.out.println("now top is: " + top);
        // min = minStack.getMin(); // return -2
        // System.out.println("now min is: " + min);
        
        
        MinStackV1 minStack = new MinStackV1();
        System.out.println("initial min stack: \n" + minStack.toString());
        minStack.push(2147483646);
        System.out.println("after push 2147483646, min stack: \n" + minStack.toString());
        minStack.push(2147483646);
        System.out.println("after push 2147483646, min stack: \n" + minStack.toString());
        minStack.push(2147483647);
        System.out.println("after push 2147483647, min stack: \n" + minStack.toString());
        
        int top = minStack.top();    // return 2147483647
        System.out.println("now top is: " + top);
        
        minStack.pop();
        System.out.println("after pop, min stack: \n" + minStack.toString());
        
        int min = minStack.getMin(); // return 2147483646
        System.out.println("now min is: " + min);
        
        minStack.pop();
        System.out.println("after pop, min stack: \n" + minStack.toString());
        
        min = minStack.getMin(); // return 2147483646
        System.out.println("now min is: " + min);
        
        minStack.pop();
        System.out.println("after pop, min stack: \n" + minStack.toString());
        
        minStack.push(2147483647);
        System.out.println("after push 2147483647, min stack: \n" + minStack.toString());
        
        top = minStack.top();    // return 2147483647
        System.out.println("now top is: " + top);
        
        min = minStack.getMin(); // return 2147483647
        System.out.println("now min is: " + min);
        
        minStack.push(-2147483648);
        System.out.println("after push -2147483648, min stack: \n" + minStack.toString());
        
        top = minStack.top();    // return 0
        System.out.println("now top is: " + top);
        
        // min = minStack.getMin(); // return -3
        // System.out.println("now min is: " + min);
        
        // minStack.pop();
        // System.out.println("after pop, min stack: \n" + minStack.toString());
        
        // min = minStack.getMin(); // return -3
        // System.out.println("now min is: " + min);
        
    }
}