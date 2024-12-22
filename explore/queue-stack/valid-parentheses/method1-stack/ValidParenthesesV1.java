import java.util.Stack;

/*
    VARS:
        stack(Stack<Character>): a stack to store all opening brackets
        idx(int): the iterating index to scan each chracter in string s
        curChar(char): the current iterated character
        ss(StringBuilder): a StringBuilder type of s to edit the string
        
    DESCRIPTION:
        STEP 1
        Initialize stack to Stack<Character>
        Initialize curChar to null with char type
            char curChar = null;
        STEP 2
        Iterate each chracter in string s from 0 to s.length() - 1 with idx
            STEP 3
            Get current character and assign it to curChar
                curChar = s.charAt(idx)
            If curChar == '(' or curChar == '{' or curChar == '[', (meaning current character is an opening bracket)
                STEP 4
                Push current character into stack
                    stack.push(curChar)
            Else, (meaning curChar is a closing bracket)
                If !stack.isEmpty() && curChar == ')' and stack.peek() == '(' or 
                   curChar == '}' and stack.peek() == '{' or
                   curChar == ']' and stack.peek() == '[', (meaning the stakc is not empty and its top node in stack is matching with current char)
                    STEP 5
                    Pop top node from stack
                        stack.pop();
                    STEP 6
                    Skip to next iteration
                        continue;
                
                Now there are two main conditions:
                    1. stack.isEmpty(), (meaning current char is a closing bracket, but stack is empty so there is nothing to compare, so return false)
                    2. !stack.isEmpty() and curChar is not matching with the top node of stack, so also return false
                    
                STEP 7
                Return false;
        STEP 8
        Return true if stack is empty after iterating all characters in ss
            return stack.isEmpty();
    TIME:
        O(n + n) ~ O(n), first n for creating ss, second n for iterating each character in ss
    SPACE:
        O(n + n) ~ O(n), first n is space for ss, second n is for stack
*/

class ValidParenthesesV1 {
    
    public static boolean isValid(String s) {
        // STEP 1
        Stack<Character> stack = new Stack<>();
        char curChar = '0';
        // STEP 2
        for (int idx = 0; idx < s.length(); idx++) {
            // STEP 3
            curChar = s.charAt(idx);
            if (curChar == '(' || curChar == '{' || curChar == '[') {
                // STEP 4
                stack.push(curChar);
            } else {
                if (!stack.isEmpty() && 
                    (curChar == ')' && stack.peek() == '(' ||
                     curChar == '}' && stack.peek() == '{' ||
                     curChar == ']' && stack.peek() == '[')) {
                    // STEP 5    
                    stack.pop();
                    // STEP 6
                    continue;
                }
                // STEP 7
                return false;
            }
        }
        // STEP 8
        return stack.isEmpty();
    }
    
    public static void main(String[] args) {
        // String s = "()";
        // String s = "()[]{}";
        // String s = "(]";
        String s = "([])";
        // String s = "]";
        
        boolean result = isValid(s);
        if (result) {
            System.out.println("string \"" + s + "\" is valid parentheses");
        } else {
            System.out.println("string \"" + s + "\" is not valid parentheses");
        }
        
    }
}