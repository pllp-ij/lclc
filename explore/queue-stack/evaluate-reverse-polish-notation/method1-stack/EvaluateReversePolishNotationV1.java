import java.util.Arrays;
import java.util.Stack;

/*
    NOTE:
        When compare two string type variables, use a.equals(b), not use "=="
    VARS:
        stack(Stack<String>): a stack to store all the operands
        lastOperand(int): the last operand when popping from stack
        lastLastOperand(int): the last of last operand when popping from stack
        calResult(int): the result of calculation when doing current operand with two popped elements
    DESCRIPTION:
        STEP 1
        Initialize stack to Stack<Integer>
        Initialize lastOperand to -1
        Initialize lastLastOperand to -1
        Initialize calResult to -1
        STEP 2
        Iterate each element in tokens with idx
            If "+-*\/".contains(tokens[idx]), meaning current character is operators
                STEP 3
                Pop last operand from stack and convert it to integer then assign it to lastOperand
                    lastOperand = Integer.valueOf(stack.pop());
                STEP 4
                Pop last of last operand from stack and convert it to integer then assign it to lastLastOperand
                    lastLastOperand = Integer.valueOf(stack.pop());
                STEP 5
                If tokens[idx].equals("+"), (the add operator)
                    STEP 6
                    Add two operands and assign the result to calResult
                        calResult = lastOperand + lastLastOperand
                STEP 7
                If tokens[idx].equals("-"), (the substract operator)
                    STEP 8
                    Substract lastOperand from lastLastOperand and assign the result to calResult
                        calResult = lastLastOperand - lastOperand;
                STEP 9
                If tokens[idx].euqls("*"), (the multiply operator)
                    STEP 10
                    Multiply two operands then assign result to calResult
                        calResult = lastOperand * lastLastOperand;
                STEP 11
                If tokens[idx].equals("/"), (the divide operator)
                    STEP 12
                    Divide lastLastOperand by lastOperand and assign the result to calResult
                        calResult = lastLastOperand / lastOperand;
                STEP 13
                Convert calResult back to String and push it into stack again
                    stack.push(String.valueOf(calResult));
            Else, (meaning current character is operands)
                STEP 14
                Push current character into stack
                    stack.push(tokens[idx]);
        STEP 15
        Pop final element stayed within stack and convert it to int then return it
            return Integer.valueof(stack.pop());
    TIME:
        O(n), n is the length of tokens
    SPACE:
        O(n), an extra stack is used to store the operands
*/

class EvaluateReversePolishNotationV1 {
    
    public static int evaluateExpression(String[] tokens) {
        // STEP 1
        Stack<String> stack = new Stack<>();
        int lastNum = 0;
        int lastLastNum = 0;
        int calResult = 0;
        // STEP 2
        for (int idx = 0; idx < tokens.length; idx++) {
            if ("+-*/".contains(tokens[idx])) {
                // STEP 3
                lastNum = Integer.valueOf(stack.pop());
                // STEP 4
                lastLastNum = Integer.valueOf(stack.pop());
                // STEP 5
                if (tokens[idx].equals("+")) {
                    // STEP 6
                    calResult = lastLastNum + lastNum;
                }
                // STEP 7
                if (tokens[idx].equals("-")) {
                    // STEP 8
                    calResult = lastLastNum - lastNum;
                }
                // STEP 9
                if (tokens[idx].equals("*")) {
                    // STEP 10
                    calResult = lastLastNum * lastNum;
                }
                // STEP 11
                if (tokens[idx].equals("/")) {
                    // STEP 12
                    calResult = lastLastNum / lastNum;
                }
                // STEP 13
                stack.push(String.valueOf(calResult));
            } else {
                // STEP 14
                stack.push(tokens[idx]);
            }
        }
        // STEP 15
        return Integer.valueOf(stack.pop());
    }
    
    public static void main(String[] args) {
        String[] tokens = {"18"};  // 18
        // String[] tokens = {"2", "1", "+", "3", "*"};  // 9
        // String[] tokens = {"4", "13", "5", "/", "+"};  // 6
        // String[] tokens = {"10", "6", "9",  "3", "+", "-11", "*", "/",  "*", "17", "+", "5", "+"}; // 22
        
        System.out.println("tokens: " + Arrays.toString(tokens));
        
        int result = evaluateExpression(tokens);
        System.out.println("result: " + result);
    }
}