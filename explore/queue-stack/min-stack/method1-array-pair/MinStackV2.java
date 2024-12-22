import java.util.List;
import java.util.LinkedList;

/*
    NOTE:
        Compared to V1, V2 use List<Integer> type to replace int[]
*/

class MinStackV2 {
    public List<Integer> dataArr;
    public List<Integer> minArr;
    
    public MinStackV2() {
        dataArr = new LinkedList<>();
        minArr = new LinkedList<>();
    }
    
    public void push(int val) {
        // if stack are empty, directly add to minArr, otherwise, add the minimum between dataArr[dataArr.size() - 1] and val to minArr
        if (isEmpty()) {
            // no matter stack is empty or not, directly add val to dataArr
            dataArr.add(val);
            minArr.add(val);
        } else {
            dataArr.add(val);
            int lastMin = minArr.get(minArr.size() - 1);
            minArr.add( Math.min( lastMin, val ) );
        }
    }
    
    public void pop() {
        // if stack are empty, nothing returned
        if (isEmpty()) {
            return;
        }
        dataArr.remove(dataArr.size() - 1);
        minArr.remove(minArr.size() - 1);
    }
    
    public int top() {
        // if stack are empty, nothing returned
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return dataArr.get(dataArr.size() - 1);
    }
    
    public int getMin() {
        // if stack are empty, nothing returned
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        return minArr.get(minArr.size() - 1);
    }
    
    public int getLength() {
        return dataArr.size();
    }
    
    public boolean isFull() {
        return dataArr.size() >= Integer.MAX_VALUE;
    }
    
    public boolean isEmpty() {
        return dataArr.size() == 0;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        // print data array
        toStringHelper(str, dataArr);
        // print min array
        toStringHelper(str, minArr);
        return str.toString();
    }
    
    public void toStringHelper(StringBuilder str, List<Integer> list) {
        str.append("HEAD | ");
        for (int idx = 0; idx < list.size(); idx++) {
            str.append(list.get(idx));
            if (idx < list.size() - 1) {
                str.append(", ");
            }
        }
        str.append(" | TAIL\n");
    }
    
    public static void main(String[] args) {
        MinStackV2 minStack = new MinStackV2();
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