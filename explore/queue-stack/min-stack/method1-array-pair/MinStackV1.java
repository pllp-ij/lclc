/*
    NOTE:
        head, tail point is the same for both dataArr and minArr
    DESCRIPTION:
    STEP 1
    Initialize capacity for size of generating array in constructor, if capacity not provided, default to 100
    Initialize dataArr with size of capacity in constructor
    Initialize minArr with size of capacity in constructor
    Initialize head and tail to 0, tail is pointing at the next position to insert element both for dataArr and minArr
    
    -FUNC push(int val)
    STEP 1
    If both dataArr and minArr are full,
        STEP 2
        Directly return
    STEP 3
    Add val to dataArr
        dataArr[tail] = val;
    If newArr is empty,
        STEP 4
        Assign val to minArr[tail]
    Else, (meaning newArr is not empty)
        STEP 5
        Assign minimum between minArr[tail - 1] and val to minArr[tail]
            minArr[tail] = Math.min(minArr[tail - 1], val);
    STEP 6
    Move tail pointer to next position
        tail++;
*/

class MinStackV1 {
    public int capacity;
    public int[] dataArr;
    public int[] minArr;
    public int head;
    public int tail;
    
    public MinStackV1() {
        this.capacity = 1000;
        dataArr = new int[capacity];
        minArr = new int[capacity];
        int head = 0;
        int tail = 0;
    }
    
    public MinStackV1(int capacity) {
        this.capacity = capacity;
        dataArr = new int[capacity];
        minArr = new int[capacity];
        int head = 0;
        int tail = 0;
    }
    
    public void push(int val) {
        // check if stack is full
        if (isFull()) {
            return;
        }
        // update data array
        dataArr[tail] = val;
        // update minArr
        if (isEmpty()) {
            minArr[tail] = val;
        } else {
            minArr[tail] = Math.min(minArr[tail - 1], val);
        }
        tail++;
    }
    
    public void pop() {
        // check if stack is empty
        if (isEmpty()) {
            return;
        }
        // update data array and min array at the same time by decreasing tail pointer one
        tail--;
    }
    
    public int top() {
        // check if stack is empty
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return dataArr[tail - 1];
    }
    
    public int getMin() {
        // check if stack is empty
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return minArr[tail - 1];
    }
    
    public int getLength() {
        return tail - head;
    }
    
    public boolean isFull() {
        return tail >= capacity - 1;
    }
    
    public boolean isEmpty() {
        return head == tail;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        // print data array
        toStringHelper(str, dataArr);
        // print min array
        toStringHelper(str, minArr);
        
        return str.toString();
    }
    
    public void toStringHelper(StringBuilder str, int[] arr) {
        str.append("HEAD | ");
        for (int idx = head; idx < this.getLength(); idx++) {
            str.append(arr[idx]);
            if (idx < this.getLength() - 1) {
                str.append(", ");
            }
        }
        str.append(" | TAIL\n");
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