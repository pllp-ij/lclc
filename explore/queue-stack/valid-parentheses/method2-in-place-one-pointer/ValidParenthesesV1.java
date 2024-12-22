/*
    VARS:
        ss(StringBuilder): a StringBuilder type of s to edit string s
        top(int): the top pointer of stack
        idx(int): index to iterate each character in s from 0 to s.length() - 1
        curChar(char): current iterated char
    DESCRIPTION:
        STEP 1
        Initialize ss to new StringBuilder(s) from s
        Initialize top to -1
        Initialize curChar to '0' as placeholder
        STEP 2
        Iterate each char of ss from 0 to ss.length() - 1 with idxSS
            STEP 3
            Get current char and assign it to curChar
                curChar = ss.charAt(idxSS);
            If curChar == '(' || curChar == '{' || curChar == '[', (meaning current is openning bracket)
                STEP 4
                Increase top by one and insert curChar at position top
                    ss.setCharAt(++top, curChar);
            Else, (meaning curChar is closing bracket)
                If top != -1 && 
                    curChar == ')' && ss.charAt(top) == '(' ||
                    curChar == '}' && ss.charAt(top) == '{' ||
                    curChar == ']' && ss.charAt(top) == '[', (meaning openning and closing are matching now)
                    STEP 5
                    Decrease top by one to simulate the pop operation
                        top--;
                    STEP 6
                    Skip to next iteration
                        continue;
                        
                Now there are two conditions:
                    1. top == -1, (meaning the virtual stack is empty, so though curChar is closing bracket, but there is nothing to compare, so return false)
                    2. top != -1 and curChar is not matching with the top node of virtual stack, which also should return false
                    
                STEP 7
                Return false;
        STEP 8
        Return true if top is finally pointing at -1, which simulate the empty state of stack
            return top == -1;
    TIME:
        O(n + n) ~ O(n), first n for creating ss, second n for iterating each character in ss
    SPACE:
        O(1), use no extra space if s is editable, here use a StringBuilder type because in java, String type is not editable originally
*/

class ValidParenthesesV1 {
    
    public static boolean isValid(String s) {
        // STEP 1
        StringBuilder ss = new StringBuilder(s);
        char curChar = '0';
        int top = -1;
        // STEP 2
        for (int idxSS = 0; idxSS < ss.length(); idxSS++) {
            // STEP 3
            curChar = ss.charAt(idxSS);
            if (curChar == '(' || curChar == '{' || curChar == '[') {
                // STEP 4
                ss.setCharAt(++top, curChar);
            } else {
                if (top != -1 &&
                    (curChar == ')' && ss.charAt(top) == '(' ||
                     curChar == '}' && ss.charAt(top) == '{' ||
                     curChar == ']' && ss.charAt(top) == '[')) {
                    // STEP 5
                    top--;
                    // STEP 6
                    continue;
                }
                // STEP 7
                return false;
            }
        }
        // STEP 8
        return top == -1;
    }
    
    public static void main(String[] args) {
        String s = "()";
        // String s = "()[]{}";
        // String s = "(]";
        // String s = "([])";
        
        boolean result = isValid(s);
        if (result) {
            System.out.println("string \"" + s + "\" is valid parentheses");
        } else {
            System.out.println("string \"" + s + "\" is not valid parentheses");
        }
        
    }
}