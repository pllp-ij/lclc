import java.util.Stack;

/*
    VARS:
        stackChar(Stack<Character>): a stack used when iterating each character in s
        curChar(Character): current iterated char
        curSubStr(String): for storing string between "[...]"
        count(int): var to store the repeat times
        result(String): for final return
        idxS(int): iterating index for s
    DESCRIPTION:
        STPE 1
        Initialize stackChar
        Initialize curChar to null
        Initialize curSubStr to ""
        Initialize count to 0
        Initialize result to ""
        Initialize idxS to 0
        STEP 2
        Loop while idxS < s.length(),
            STEP 3
            Get current char and assign it to curChar
                curChar = s.charAt(idxS);
            STEP 4
            If curChar == ']', (meaning now should do operation for stackChar)
                STEP 5
                Reset curSubStr to "" for storing current sub string
                    curSubStr = "";
                STEP 6
                Loop while !stackChar.isEmpty() and stackChar.peek() != '[', (meaning stackChar is not empty and its top element it not yet reaching '[')
                    STEP 7
                    Pop top element and add it to curSubStr with reverse order to popped order
                        curSubStr = stackChar.pop + curSubStr;
                
                Now there are two conditions after existing above while loops
                    1. stackChar.isEmpty(), (actually this won't be executed at all, because there is at least one '[' character to match current ']' character)
                    2. !stackChar.isEmpty() and stackChar.peek() == '[', (meaning current top element is '[', so it is needed to pop it up)
                
                STEP 8
                If (stackChar.peek() == '['),
                    STEP 9
                    Pop '[' out to remove "[..]" range
                        stackChar.pop();
                STEP 10
                Reset count to 0 to store the repeatation number
                    count = 0;
                STEP 11
                Set var digitPos to denote current position to 0, which will be used in calculation of count in the following
                    int digitPos = 0;
                STEP 12
                Loop while !stackChar.isEmpty()),
                    STEP 13
                    Get top element's corresponding integer value and assign it to topNum
                        int topNum = stackChar.peek() - '0';
                    STEP 14
                    If topNum < 0 or topNum > 9, (meaning current top char is not digit any longer)
                        STEP 15
                        Break the while loop
                            break;
                            
                    Now topNum is digit and within range [0, 9]
                
                    STEP 16
                    Multiply count by 10 and add topNum to it then reassign it to count
                        count += topNum * pow(10, digitPos);
                    STEP 17
                    Pop current char from stackChar
                        stackChar.pop();
                    STEP 18
                    Increase digitPos by one to indicate move to next significant digit
                        digitPos++;
                
                Now the repeat number is stored in count, and the string needed to be repeated stored in curSubStr
                
                STEP 19
                Iterate i from 0 to count - 1
                    STEP 20
                    Iterate each character in curSubStr with idxCurSubStr from 0 to curSubStr.length() - 1
                        STEP 21
                        Push current iterated character into stackChar
                            stackChar.push(curSubStr.charAt(idxCurSubStr));
                
                Now all contents within "[..]" range has been process, then skip current iteration
                
                STEP 22
                Increase idxS by one to next iteration
                    idxS++;
                STEP 23
                continue;
            
            Now all conditions remained (curChar is digit, character, '['), should all pushed into stack
            
            STEP 24
            Increase idxS by one to next iteration
                idxS++;
            STEP 25
            stackChar.push(curChar);
        
        Now all decoded chars are store within stackChar, so pop them one by one
        
        STEP 26
        Loop while !stackChar.isEmpty(),
            STEP 27
            Pop top char and add it to result then reassign it to result
                result = stackChar.pop() + result;
        STEP 28
        Return result;
            
        -FUNC int pow(int base, int exp)
        STEP 1
        If exp == 0, (meaning exponential number is 0)
            STEP 2
            Return 1;
        STEP 3
        If exp == 1, (meaning reaching the base case)
            STEP 4
            Return base;
        STEP 5
        Return pow(base, exp - 1) * base;
           
    TIME:
        O(N), N is the length of s
    SPACE:
        O(N + N + N) ~ O(N), first N is for stackChar, second N is for curChar, third N is for result
*/

class DecodeStringV1 {
    
    public static String decodeStr(String s) {
        // STEP 1
        Stack<Character> stackChar = new Stack<>();
        Character curChar = null;
        String curSubStr = "";
        int count = 0;
        String result = "";
        int idxS = 0;
        // STEP 2
        while (idxS < s.length()) {
            // STEP 3
            curChar = s.charAt(idxS);
            // STPE 4
            if (curChar == ']') {
                // STEP 5
                curSubStr = "";
                // STEP 6
                while (!stackChar.isEmpty() && stackChar.peek() != '[') {
                    // STEP 7
                    curSubStr = stackChar.pop() + curSubStr;
                }
                // STEP 8
                if (stackChar.peek() == '[') {
                    // STEP 9
                    stackChar.pop();
                }
                // STEP 10
                count = 0;
                // STEP 11
                int digitPos = 0;
                // STEP 12
                while (!stackChar.isEmpty()) {
                    // STEP 13
                    int topNum = stackChar.peek() - '0';
                    // STEP 14
                    if (topNum < 0 || topNum > 9) {
                        // STEP 15
                        break;
                    }
                    // STEP 16
                    count += topNum * pow(10, digitPos);
                    // STEP 17
                    stackChar.pop();
                    // STEP 18
                    digitPos++;
                }
                // STEP 19
                for (int i = 0; i < count; i++) {
                    // STEP 20
                    for (int idxCurSubStr = 0; idxCurSubStr < curSubStr.length(); idxCurSubStr++) {
                        // STEP 21
                        stackChar.push(curSubStr.charAt(idxCurSubStr));
                    }
                }
                // STEP 22
                idxS++;
                // STEP 23
                continue;
            }
            // STEP 24
            idxS++;
            // STEP 25
            stackChar.push(curChar);
        }
        // STEP 26
        while (!stackChar.isEmpty()) {
            // STEP 27
            result = stackChar.pop() + result;
        }
        // STEP 28
        return result;
    }
    
    public static int pow(int base, int exp) {
        // STEP 1
        if (exp == 0) {
            // STEP 2
            return 1;
        }
        // STEP 3
        if (exp == 1) {
            // STEP 4
            return base;
        }
        // STEP 5
        return base * pow(base, exp - 1);
    }
    
    public static void main(String[] args) {
        String s = "3[a]2[bc]";
        
        // String s = "3[a2[c]]";
        
        // String s = "2[abc]3[cd]ef";
        
        // String s = "100[apple]";
        
        String decodedStr = decodeStr(s);
        System.out.println("initial string: " + s);
        System.out.println("decoded string: " + decodedStr);
        
    }
}