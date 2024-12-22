import java.util.Arrays;
import java.util.Stack;

/*
    VARS:
        stack(Stack<Integer>): a stack for storing all numbers that yet got next warmer temperature
        result(int[]): a result store the days needed for next warmer day
        idxFoundWarmer(int): the indexes in temperatures that has found the next warmer temperature
    DESCRIPTION:
        STEP 1
        Initialize stack to Stack<Integer>
        Initialize result to integer array with same size of temperatures
        Initialize idxFoundWarmer to -1
        STEP 2
        Iterate each number in temperatures with idx
            If stack.isEmpty(), (meaning the stack is currently empty)
                STEP 3
                Add current number into stack
                    stack.push(idx)
            Else, (meaning stack is not empty)
                STEP 4
                Loop while !stack.isEmpty() and stack.peek() < temperatures[idx], (meaning stack is not empty and the current temperature is high than the top node of stack)
                    STEP 5
                    Pop the top node from stack and assign the popped value to idxFoundWarmer
                        idxFoundWarmer = stack.pop();
                    STEP 6
                    Set the days needed as (idx - idxFoundWarmer) to the position of idx in result
                        result[idxFoundWarmer] = (idx - idxFoundWarmer);

                Now there are two conditions:
                    1. stack.isEmpty(), (meaning current stack is empty)
                    2. !stack.isEmpty() and stack.peek() >= temperatures[idx], (meaning current stack is not empty, but current iterated temperature is no longer large than the top node of stack)
                Both conditions should do the same thing, push temperatures[idx] to stack again

                STEP 7
                Push current temperature into stack
                    stack.push(idx)
        STEP 8
        Return result
    TIME:
        O(n), one scan for input temperatures array
    SPACE:
        O(n), a size of n stack is needed
*/

class DailyTemperaturesV1 {
    
    public static int[] getNumberOfDays(int[] temperatures) {
        // STEP 1
        Stack<Integer> stack = new Stack<>();
        int idxFoundWarmer = -1;
        int[] result = new int[temperatures.length];
        // STEP 2
        for (int idx = 0; idx < temperatures.length; idx++) {
            if (stack.isEmpty()) {
                // STEP 3
                stack.push(idx);
            } else {
                // STEP 4
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[idx]) {
                    // STEP 5
                    idxFoundWarmer = stack.pop();
                    // STEP 6
                    result[idxFoundWarmer] = (idx - idxFoundWarmer);
                }
                // STEP 7
                stack.push(idx);
            }
        }
        // STEP 8
        return result;
    }
    
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("array: " + Arrays.toString(temperatures));
        int[] result = getNumberOfDays(temperatures);
        System.out.println("number of days array: " + Arrays.toString(result));
    }
}