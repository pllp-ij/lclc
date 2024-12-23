import java.util.Arrays;

/*
    NOTE:
        Compared to method1, method2 update each result of operator to the position of lastLastOperand, so after the operation is done, the result will become a single number, just like
        (2, 3) +  will replace 2 with 6, then the section of "(2, 3) +" seems doesn't exist at all
    VARS:
        idxLastNum(int): a pointer always point at the last number when iterating
        lastNum(int): to temporarily store the last number when iterate
        lastLastNum(int): to temporarily store the last of last number when iterate
        idxCurChar(int): a pointer for iterating each character in tokens
        calResult(int): the result of the expression, also for final return
    DESCRIPTION:
        STEP 1
        Initialize idxLastNum to -1
        Initialize lastNum to 0
        Initialize lastLastNum to 0
        Initialize idxCurChar to 0
        Initialize calResult to 0
        STEP 2
        Loop while idxCurChar < tokens.length, (iterate each char in tokens)
            If "+-*\/".contains(tokens[idxCurChar]), (meaning current number is operators)
                STEP 3
                    Get last number and convert it to int then assign it to lastNum
                        lastNum = Integer.valueOf(tokens[idxLastNum])
                STEP 4
                Get last of last number and convert it to int then assign it to lastLastNum
                    lastLastNum = Integer.valueOf(tokens[idxLastNum - 1]);
                STEP 5
                If tokens[idxCurChar] == "+", (the add operator)
                    STEP 6
                    Calculate add operation and assign the result to calResult
                        calResult = lastLastNum + lastNum
                STEP 7
                If tokens[idxCurChar] == "-", (the substract operator)
                    STEP 8
                    Calculate add operation and assign the result to calResult
                        calResult = lastLastNum - lastNum
                STEP 9
                If tokens[idxCurChar] == "*", (the multiply operator)
                    STEP 10
                    Calculate substract operation and assign the result to calResult
                        calResult = lastLastNum * lastNum;
                STEP 11
                If tokens[idxCurChar] == "/", (the divide operator)
                    STEP 12
                    Calculate divide operation and assign the result to calResult
                        calResult = lastLastNum / lastNum
                STEP 13
                Replace the value at (idxLastNum - 1) with calResult to remove current operation
                    tokens[idxLastNum - 1] = String.valueOf(calResult);
                STEP 14
                Decrease idxLastNum by one to make it point to the newly number so the the operation above are finished and can be neglected
                    idxLastNum--;
            Else, (meaning current number is operands)
                STEP 15
                Increase lastNum by one to point to the position to insert operand at the beginning or after one operation finished
                    lastNum++;
                STEP 16
                Copy value at idxCurChar to position idxLastNum
                    tokens[idxLastNum] = tokens[idxCurChar];
            STEP 17
            No matter current char is operand or operator, idxCurChar will increase by one to next position
                idxCurChar++;
        STEP 18
        Return final result at position idxLastNum, (do not return calResult, for a input with only one element, it will incorrect)
            return Integer.valueOf(tokens[idxLastNum]);
    TIME:
        O(n), n is the length of tokens
    SPACE:
        O(1), no extra space is needed
*/

class EvaluateReversePolishNotationV1 {
    
    public static int evaluateExpression(String[] tokens) {
        // STEP 1
        int idxCurChar = 0;
        int idxLastNum = -1;
        int lastNum = 0;
        int lastLastNum = 0;
        int calResult = 0;
        // STEP 2
        while (idxCurChar < tokens.length) {
            if ("+-*/".contains(tokens[idxCurChar])) {
                // STEP 3
                lastNum = Integer.valueOf(tokens[idxLastNum]);
                // STEP 4
                lastLastNum = Integer.valueOf(tokens[idxLastNum - 1]);
                // STEP 5
                if (tokens[idxCurChar] == "+") {
                    // STEP 6
                    calResult = lastLastNum + lastNum;
                }
                // STEP 7
                if (tokens[idxCurChar] == "-") {
                    // STEP 8
                    calResult = lastLastNum - lastNum;
                }
                // STEP 9
                if (tokens[idxCurChar] == "*") {
                    // STEP 10
                    calResult = lastLastNum * lastNum;
                }
                // STEP 11
                if (tokens[idxCurChar] == "/") {
                    // STEP 12
                    calResult = lastLastNum / lastNum;
                }
                // STEP 13
                tokens[idxLastNum - 1] = String.valueOf(calResult);
                // STPE 14
                idxLastNum--;
            } else {
                // STEP 15
                idxLastNum++;
                // STEP 16
                tokens[idxLastNum] = tokens[idxCurChar];
            }
            // STEP 17
            idxCurChar++;
        }
        // STEP 18
        return Integer.valueOf(tokens[idxLastNum]);
    }
    
    public static void main(String[] args) {
        String[] tokens = {"18"};
        // String[] tokens = {"2", "1", "+", "3", "*"};  // 9
        // String[] tokens = {"4", "13", "5", "/", "+"};  // 6
        // String[] tokens = {"10", "6", "9",  "3", "+", "-11", "*", "/",  "*", "17", "+", "5", "+"}; // 22
        
        System.out.println("tokens: " + Arrays.toString(tokens));
        
        int result = evaluateExpression(tokens);
        System.out.println("result: " + result);
    }
}