import java.util.Stack;

/*
    VARS:
        stackNum(Stack<Integer>): a stack for storing digit before "[" char if there is one
        stackChar(Stack<Character>): a stack for storing "[" or characters
        curChar(Character): current char in each iteration
        count(int): a var to store the number represented by digit characters before "[" in each occursion
        curSubStr(String): a String type var to store current characters between "[" and "]"
        result(String): the final returned string after decoding
    DESCRIPTION:
        STEP 1
        Initialize stackNum to Stack<>();
        Initialize stackChar to Stack<>();
        Initialize curChar to null;
        Initialize count to 0
        Initialize curSubStr to ""
        Initialize result to ""
        Initialize idxS to 0 for iterating s and can be able to skip some steps
        STEP 2
        Iterate each character of s with idxS from 0 to s.length()
            STEP 3
            Assign current character to curChar
                curChar = s.charAt(idxS);
            STEP 4
            Get integer value correponding to current char and assign it to curCharNum
                int curCharNum = curChar - '0';
            STEP 5
            If curCharNum >= 0 and curCharNum <= 9, (meaning current character is digit, it should be pushed into stackNum)
                STEP 6
                Multiply count by 10 and add curCharNum and assign it back to count
                    count = count * 10 + curCharNum;
                STEP 7
                If s.charAt(idxS + 1) == '[', (meaning next character is '[', the reoeatation number represented by continuous digit characters range is over)
                    STEP 8
                    Push count into stackNum for later use
                        stackNum.push(count);
                    STEP 9
                    Remember to set count to 0 for next continuous digit characters range
                        count = 0;
                STEP 10
                Skip current iteration
                    continue;
            STEP 11
            If current character is ']', (meaning there should be pop operations done)
                STEP 12
                Initialize curSubStr to "" to store current sub string between '[' and ']'
                    curSubStr = "";
                STEP 13
                Loop while !stackChar.isEmpty() and stackChar.peek() != '[', (meaning the char stack is not empty and the top element is not yet reaching the '[' character)
                    STEP 14
                    Add each character popped from stackChar to subStr to collect continuous sequence
                        curSubStr = stackChar.pop() + curSubStr;
                
                Now there are two conditions:
                    1. stackChar.isEmpty(), (actually this won't be true, because there is at least one "[" to matching current "]" character)
                    2. !stackChar.isEmpty() and stackChar.peek() == '['
               
                STEO 15 - OPTIONAL, (STEP 7 will be always true in this problem)
                If stackChar.peek() == '[', (meaning current top element of stackChar is '[')
                    STEP 16
                    Pop '[' out to remove the "[..]" range
                        stackChar.pop();
                
                STEP 17
                Get the top element from stackNum and assign it to repeatCount, (note that here cannot reuse the count to receive output, because it will influence next iteration in above if statement)
                    int repeatCount = stackNum.pop();
                STEP 18
                Iterate idx from 0 to repeatCount - 1, to repeat multiple times of curSubStr
                    STEP 19
                    Iterate each character in curSubStr with idxCurSubStr from 0 to curSubStr.length() - 1
                        STEP 20
                        Push curSubStr.charAt(idxCurSubStr) into stackChar
                            stackChar.push(curSubStr.charAt(idxCurSubStr));
                STEP 21
                Skip current iteration
                    continue;
           
            Now only left condition when curChar is character or '[', directly push it into stackChar
            
            STEP 22
            stackChar.push(curChar);
        
        Now all final characters are store in stackChar, so pop them one by one
        
        STEP 23
        Loop while stackChar is not empty,
            STEP 24
            Pop top element from stackChar and add it into result
                result = stackChar.pop() + result;
        STEP 25
        Return result
    TIME:
        O(N), N is the length of s
    SPACE:
        O(N + N + N + N) ~ O(N), first N is for stackNum, second N is for stackChar, third N is for curChar, fourth N is for result
*/

class DecodeStringV1 {
    
    public static String decodeStr(String s) {
        // STEP 1
        Stack<Integer> stackNum = new Stack<>();
        Stack<Character> stackChar = new Stack<>();
        Character curChar = null;
        String curSubStr = "";
        String result = "";
        int count = 0;
        int idxS = 0;
        // STEP 2
        for (; idxS < s.length(); idxS++) {
            // STEP 3
            curChar = s.charAt(idxS);
            // STEP 4
            int curCharNum = curChar - '0';
            // STEP 5
            if (curCharNum >= 0 && curCharNum <= 9) {
                // STEP 6
                count = count * 10 + curCharNum;
                // STEP 7
                if (s.charAt(idxS + 1) == '[') {
                    // STEP 8
                    stackNum.push(count);
                    // STEP 9
                    count = 0;
                }
                // STEP 10
                continue;
            }
            // STEP 11
            if (curChar == ']') {
                // STEP 12
                curSubStr = "";
                // STEP 13
                while (!stackChar.isEmpty() && stackChar.peek() != '[') {
                    // STEP 14
                    curSubStr = stackChar.pop() + curSubStr;
                }
                // STEP 15
                if (stackChar.peek() == '[') {
                    // STEP 16
                    stackChar.pop();
                }
                // STEP 17
                int repeatCount = stackNum.pop();
                // STEP 18
                for (int i = 0; i < repeatCount; i++) {
                    // STEP 19
                    for (int idxCurSubStr = 0; idxCurSubStr < curSubStr.length(); idxCurSubStr++) {
                        // STEP 20
                        stackChar.push(curSubStr.charAt(idxCurSubStr));
                    }
                }
                // STEP 21
                continue;
            }
            // STEP 22
            stackChar.push(curChar);
        }
        // STEP 23
        while (!stackChar.isEmpty()) {
            // STEP 24
            result = stackChar.pop() + result;
        }
        // STEP 25
        return result;
    }
    
    public static void main(String[] args) {
        // String s = "3[a]2[bc]";
        
        // String s = "3[a2[c]]";
        
        String s = "2[abc]3[cd]ef";
        
        // String s = "100[apple]";
        
        String decodedStr = decodeStr(s);
        System.out.println("initial string: " + s);
        System.out.println("decoded string: " + decodedStr);
        
    }
}