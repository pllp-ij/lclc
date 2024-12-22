import java.util.Arrays;
import java.util.Stack;

/*
    NOTE:
        Compared to V1, V2 combine the condition when stack is empty to the remaining logic after while loop, making the code more neat
    VARS:
        stack(Stack<Integer>): a stack for storing all numbers that yet got next warmer temperature
        result(int[]): a result store the days needed to next warmer day for each iterated temperature
        idxFoundWarmer(int): the indexes in temperatures that has found the next warmer temperature
    DESCRIPTION:
        STEP 1
        Initialize stack to Stack<Integer>
        Initialize result to int array with same size of temperatures
        Initialize idxFoundWarmer to -1
        STEP 2
        Iterate each number in temperatures with idx
            STEP 3
            Loop while !stack.isEmpty() and temperatures[stack.peek()] < temperatures[idx], (meaing when stack is not empty and the value corresponding to the top node of stack as index is smaller than current iterated temperature, pop the top node and update result)
                STEP 4
                Pop the top node from stack and assign it to idxFoundWarmer
                    idxFoundWarmer = stack.pop();
                STEP 5
                Set the value at idxFoundWarmer in result to (idx - idxFoundWarmer), which indicate the days needed to reach next warmer temperature
                    result[idxFoundWarmer] = (idx - idxFoundWarmer);
        
            Now there are two conditions:
                1. stack.isEmpty(), (meaning current stack is empty)
                2. !stack.isEmpty() and temperatures[stack.peek()] >= temperatures[idx], (meaning though current stack is not empty, but the temperature corresponding to top node is no longer larger than temperatures[idx])
            Both above two conditions should do same thing, push temperatures[idx] to stack
            
            STEP 6
            Push idx to stack
                stack.push(idx)
        STEP 7
        Return result
    TIME:
        O(n), one scan for input temperatures array
    SPACE:
        O(n), a size of n stack is needed
*/

class DailyTemperaturesV2 {
    
    public static int[] getNumberOfDays(int[] temperatures) {
        // STEP 1
        Stack<Integer> stack = new Stack<>();
        int idxFoundWarmer = -1;
        int[] result = new int[temperatures.length];
        // STEP 2
        for (int idx = 0; idx < temperatures.length; idx++) {
            // STEP 3
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[idx]) {
                // STEP 4
                idxFoundWarmer = stack.pop();
                // STEP 5
                result[idxFoundWarmer] = (idx - idxFoundWarmer);
            }
            // STEP 6
            stack.push(idx);
        }
        // STEP 7
        return result;
    }
    
    public static void main(String[] args) {
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("array: " + Arrays.toString(temperatures));
        int[] result = getNumberOfDays(temperatures);
        System.out.println("number of days array: " + Arrays.toString(result));
    }
}